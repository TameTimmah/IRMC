package com.notoriousdev.irmc;

import com.notoriousdev.irmc.events.irc.ServerListener;
import com.notoriousdev.irmc.events.irc.UserListener;
import org.bukkit.entity.Player;
import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.UtilSSLSocketFactory;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectEvent;

public class Bot extends ListenerAdapter implements Runnable
{
    
    private final IRMC plugin;
    private final Thread thread;
    private PircBotX bot;
    private Configuration conf;
    private boolean debug;
    
    public Bot(IRMC plugin, Configuration conf)
    {
        this.plugin = plugin;
        this.conf = conf;
        thread = new Thread(this);
        thread.start();
    }
    
    @Override
    public synchronized void run()
    {
        conf.loadConfig();
        debug = conf.isDebug();
        try {
            this.start();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
    
    void start()
    {
        if (debug) {
            plugin.getLogger().info(String.format("Initialising IRMC..."));
        }
        bot = new PircBotX();
        if (debug) {
            plugin.getLogger().info(String.format("Loading configuration..."));
        }
        loadOptions();
        if (debug) {
            plugin.getLogger().info(String.format("Adding bot listeners..."));
        }
        addListeners();
        if (debug) {
            plugin.getLogger().info(String.format("Connecting to server..."));
        }
        connect();
    }
    
    private void addListeners()
    {
        bot.getListenerManager().addListener(this);
        bot.getListenerManager().addListener(new UserListener(plugin, this));
        bot.getListenerManager().addListener(new ServerListener(plugin, this));
    }
    
    private void loadOptions()
    {
        bot.setVerbose(conf.isVerbose());
        bot.setMessageDelay(0);
        bot.setAutoNickChange(conf.isAutoNick());
        bot.setAutoReconnect(conf.isAutoReconnect());
        bot.setAutoReconnectChannels(conf.isAutoRejoin());
        bot.setAutoSplitMessage(conf.isAutoSplit());
        bot.setName(conf.getNickname());
        bot.setLogin(conf.getIdent());
        bot.setVersion(String.format("IRMC Version %s - A PircBotX Minecraft-IRC Bridge.", plugin.getDescription().getVersion()));
    }
    
    private void connect()
    {
        String host = conf.getAddress();
        int port = conf.getPort();
        String pass = conf.getServerPass();
        boolean usessl = conf.isUseSSL();
        boolean verifyssl = conf.isVerifySSL();
        try {
            if (usessl && verifyssl) {
                bot.connect(host, port, pass, new UtilSSLSocketFactory());
            } else if (usessl && !verifyssl) {
                bot.connect(host, port, pass, new UtilSSLSocketFactory().trustAllCertificates());
            } else {
                bot.connect(host, port, pass);
            }
        } catch (Exception e) {
            if (debug) {
                throw new RuntimeException(e);
            }
        }
    }
    
    private void joinChannels()
    {
        for (String channel : conf.getChannels()) {
            bot.joinChannel(channel);
        }
    }
    
    @Override
    public void onConnect(ConnectEvent event)
    {
        joinChannels();
        //bot.identify(conf.getPassword());
    }
    
    public synchronized void kill()
    {
        if (debug) {
            plugin.getLogger().info(String.format("Killing IRMC..."));
        }
        if (bot.isConnected()) {
            for (Channel channel : bot.getChannels()) {
                bot.partChannel(channel);
                //bot.partChannel(channel, "Killing IRMC...");
            }
            bot.disconnect();
        }
        if (thread.isAlive()) {
            try {
                thread.join();
            } catch (Exception e) {
                if (debug) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    
    public synchronized void chatToIrc(Player player, String message)
    {
        for (Channel channel : bot.getChannels()) {
            bot.sendMessage(channel, String.format("{%s} %s", player.getName(), message));
        }
    }
    
    public synchronized void relayServerMessage(String message)
    {
        for (Channel channel : bot.getChannels()) {
            bot.sendMessage(channel, String.format("{%s}", message));
        }
    }
}

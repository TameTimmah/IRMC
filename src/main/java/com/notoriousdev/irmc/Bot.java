package com.notoriousdev.irmc;

import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.UtilSSLSocketFactory;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectEvent;
import lombok.Getter;

public class Bot extends ListenerAdapter implements Runnable
{

    private final IRMC plugin;
    private final Thread thread;
    private PircBotX bot;
    private Config conf;

    

    public Bot(IRMC plugin, Config conf)
    {
        this.plugin = plugin;
        this.conf = conf;
        thread = new Thread(this);
        thread.start();
    }
    
    private boolean debug = conf.isDebug();
    private boolean verbose = conf.isVerbose();

    @Override
    public synchronized void run()
    {
        if (debug) {
            plugin.getLogger().info(String.format("Initialising IRMC..."));
        }
        bot = new PircBotX();
        if (debug) {
            plugin.getLogger().info(String.format("Loading configuration..."));
        }
        loadConfig();
        if (debug) {
            plugin.getLogger().info(String.format("Connecting to server..."));
        }
        connect();
        addListeners();
    }

    private void loadConfig()
    {
        bot.setVerbose(debug && verbose);
        bot.setAutoNickChange(true);
        plugin.getLogger().warning(String.format("Configuration not fully implemented!"));
    }

    private void connect()
    {
        try {
            if (conf.isSsl() && conf.isVerifySsl()) {
                bot.connect(conf.getServer(), conf.getPort(), conf.getServerPass(), new UtilSSLSocketFactory());
            } else if (conf.isSsl() && !conf.isVerifySsl()) {
                bot.connect(conf.getServer(), conf.getPort(), conf.getServerPass(), new UtilSSLSocketFactory().trustAllCertificates());
            } else {
                bot.connect(conf.getServer(), conf.getPort(), conf.getServerPass());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void joinChannels()
    {
        if (debug) {
            plugin.getLogger().info(String.format("Joining channels..."));
        }
        for (String str : conf.getChannels()) {
            if (str.contains(" ")) {
                if (debug) {
                    plugin.getLogger().info(String.format("Joining %s...", str.split(" ")[0]));
                }
                bot.joinChannel(str.split(" ")[0], str.split(" ")[1]);
            } else {
                if (debug) {
                    plugin.getLogger().info(String.format("Joining %s...", str));
                }
                bot.joinChannel(str);
            }
        }
    }

    private void addListeners()
    {
        bot.getListenerManager().addListener(this);
    }

    @Override
    public void onConnect(ConnectEvent event)
    {
        joinChannels();
    }

    public synchronized void kill()
    {
        if (bot.isConnected()) {
            for (Channel channel : bot.getChannels()) {
                bot.partChannel(channel);
            }
            bot.disconnect();
        }
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
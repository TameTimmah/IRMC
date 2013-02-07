package com.notoriousdev.irmc;

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
    //
    private boolean debug = false;

    public Bot(IRMC plugin)
    {
        this.plugin = plugin;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public synchronized void run()
    {
        debug = plugin.getConfig().getBoolean("irc.debug");
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
        bot.setVerbose(debug && plugin.getConfig().getBoolean("irc.verbose"));
        bot.setAutoNickChange(true);
        plugin.getLogger().warning(String.format("Configuration not fully implemented!"));
    }

    private void connect()
    {
        try {
            if (plugin.getConfig().getBoolean("irc.use-ssl") && plugin.getConfig().getBoolean("irc.verify-ssl")) {
                bot.connect(plugin.getConfig().getString("irc.server.address"), plugin.getConfig().getInt("irc.server.port"), plugin.getConfig().getString("irc.server.password"), new UtilSSLSocketFactory());
            } else if (plugin.getConfig().getBoolean("irc.use-ssl") && !plugin.getConfig().getBoolean("irc.verify-ssl")) {
                bot.connect(plugin.getConfig().getString("irc.server.address"), plugin.getConfig().getInt("irc.server.port"), plugin.getConfig().getString("irc.server.password"), new UtilSSLSocketFactory().trustAllCertificates());
            } else {
                bot.connect(plugin.getConfig().getString("irc.server.address"), plugin.getConfig().getInt("irc.server.port"), plugin.getConfig().getString("irc.server.password"));
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
        for (String str : plugin.getConfig().getStringList("irc.channels")) {
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
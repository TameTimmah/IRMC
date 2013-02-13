package com.notoriousdev.irmc;

import com.notoriousdev.irmc.events.irc.ServerListener;
import com.notoriousdev.irmc.events.irc.UserListener;
import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectEvent;

public class Bot extends ListenerAdapter implements Runnable
{

    private final IRMC plugin;
    private final Thread thread;
    private PircBotX bot;
    private Configuration conf;
    private boolean debug;

    public Bot(IRMC plugin)
    {
        this.plugin = plugin;
        conf = new Configuration(plugin, this);
        conf.loadConfig();
        debug = conf.isDebug();
        this.thread = new Thread(this, "IRMC");
        this.thread.start();
    }

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
        bot.getListenerManager().addListener(new UserListener());
        bot.getListenerManager().addListener(new ServerListener());
    }

    private void loadConfig()
    {
    }

    private void connect()
    {
    }

    private void joinChannels()
    {
    }

    @Override
    public void onConnect(ConnectEvent event)
    {
        joinChannels();
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
}

package com.notoriousdev.irmc;

import java.io.IOException;
import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.UtilSSLSocketFactory;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectEvent;

public class Bot extends ListenerAdapter implements Runnable
{

    private final IRMC plugin;
    private final Thread thread;
    private PircBotX bot;
    private Conf conf;
    private boolean debug;
    private boolean verbose;
    

    public Bot(IRMC plugin, Conf conf)
    {
        this.plugin = plugin;
        this.conf = conf;
        conf.loadConfig();
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public synchronized void run()
    {
        
        if (debug) {
            plugin.getLogger().info(String.format("Initialising IRMC..."));
        }       
        if (debug) {
            plugin.getLogger().info(String.format("Loading configuration..."));
        }
        if (debug) {
            plugin.getLogger().info(String.format("Connecting to server..."));
        }
        
        try {
            this.start();
        } catch (final Exception e) {
        }
    }

    void start()
    {
        bot = new PircBotX();
        loadConfig();
        addListeners();
        connect();
    }
    
    private void loadConfig()
    {
        this.debug = true;
        this.verbose = true;
        bot.setVerbose(debug && verbose);
        bot.setAutoNickChange(conf.isAutoNick());
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
                if(conf.getServerPass().isEmpty())
                {
                    bot.connect(conf.getServer(), conf.getPort());
                }
                else
                {
                    bot.connect(conf.getServer(), conf.getPort(), conf.getServerPass());
                }
            }
        } catch (final IOException e) {
        } catch (final IrcException e) {
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
    
    public void sendChannels(String msg) //TODO: this doesn't work
    {
        for(Channel chan : bot.getChannels())
        {
            chan.sendMessage(msg);
        }
    }
}
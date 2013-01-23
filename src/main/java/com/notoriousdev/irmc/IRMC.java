package com.notoriousdev.irmc;

import com.notoriousdev.irmc.docs.IrcConfig;
import com.notoriousdev.irmc.irc.Bot;
import org.bukkit.plugin.java.JavaPlugin;

public class IRMC extends JavaPlugin
{

    private Bot bot;
    public static IrcConfig ircConfig;

    @Override
    public void onDisable()
    {
        getLogger().info("IRMC bot is disconnecting...");
        bot.disconnect();
        getLogger().info("IRMC Successfully disabled");
    }

    @Override
    public void onEnable()
    {
        ircConfig = new IrcConfig(this);
        saveDefaultConfig();
        ircConfig.saveDefaultConfig();
        getLogger().info("IRMC bot is connecting...");
        bot = new Bot(this);
        bot.connect();
        getLogger().info("IRMC Successfully enabled");
    }
}

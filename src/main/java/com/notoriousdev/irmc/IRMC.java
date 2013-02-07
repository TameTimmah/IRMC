package com.notoriousdev.irmc;

import java.io.File;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class IRMC extends JavaPlugin
{

    private Bot bot;
    Config config;

    @Override
    public void onDisable()
    {
        if (bot != null) {
            bot.kill();
            bot = null;
        }
    }

    @Override
    public void onEnable()
    {
        if (!new File(getDataFolder(), "config.yml").exists()) {
            getLogger().warning(String.format("[IRMC] Config file not found. Saving default config and disabling plugin."));
            getLogger().warning(String.format("[IRMC] Please edit the new config.yml found in the IRMC folder and then restart the server."));
            saveDefaultConfig();
            return;
        }
        config = new Config(this);
        bot = new Bot(this, config);
        
        //getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }
    
    public Config getConf()
    {
        return config;
    }
}

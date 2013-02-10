package com.notoriousdev.irmc;

import com.notoriousdev.irmc.events.bukkit.PlayerListener;
import java.io.File;
import org.bukkit.plugin.java.JavaPlugin;

public class IRMC extends JavaPlugin
{

    private Bot bot;
    Configuration conf;

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
        conf = new Configuration(this);
        conf.loadConfig();
        bot = new Bot(this, conf);
        
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }
    
    public Configuration getConf()
    {
        return conf;
    }
    
    public Bot getBot()
    {
        return bot;
    }
}

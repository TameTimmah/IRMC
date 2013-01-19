package com.notoriousdev.irmc;

import com.notoriousdev.irmc.irc.Bot;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

public class IRMC extends JavaPlugin {

    private Bot bot;

    @Override
    public void onDisable() {
        getLogger().info("Disconnecting bot...");
        bot.endHere();
        getLogger().info("IRMC has been successfully disabled.");
    }

    @Override
    public void onEnable() {
        FileConfiguration config = this.getConfig();
        config = getConfig();
        config.addDefault("account.use-nickserv", "true");
        config.addDefault("account.name", "IRMC");
        config.addDefault("account.nickserv-pass", "IRMC");
        config.options().copyDefaults(true);
        saveConfig();
        bot = new Bot(this);
        bot.startHere();
        getLogger().info("IRMC has been successfully enabled.");
    }
}

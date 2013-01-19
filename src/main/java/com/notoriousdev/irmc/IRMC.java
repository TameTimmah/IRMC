package com.notoriousdev.irmc;

import com.notoriousdev.irmc.irc.Bot;
import org.bukkit.plugin.java.JavaPlugin;

public class IRMC extends JavaPlugin {

    private Bot bot;

    @Override
    public void onDisable() {
        bot.endHere();
    }

    @Override
    public void onEnable() {
        getConfig().addDefault("account.use-nickserv", "true");
        getConfig().addDefault("account.name", "IRMC");
        getConfig().addDefault("account.nickserv-pass", "IRMC");
        bot = new Bot(this);
        bot.startHere();
    }
}

package com.notoriousdev.irmc;

import com.notoriousdev.irmc.irc.Bot;
import org.bukkit.plugin.java.JavaPlugin;

public class IRMC extends JavaPlugin {

    private Bot bot;

    @Override
    public void onDisable() {
        // TODO: We need this shit, yo!
    }

    @Override
    public void onEnable() {
        bot = new Bot(this);
        bot.startHere();
    }
}

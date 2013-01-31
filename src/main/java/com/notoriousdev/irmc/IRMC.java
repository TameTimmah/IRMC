package com.notoriousdev.irmc;

import org.bukkit.plugin.java.JavaPlugin;

public class IRMC extends JavaPlugin
{

    private Bot bot;
    //private final int tid;

    @Override
    public void onDisable()
    {
        bot.kill();
        bot = null;
    }

    @Override
    public void onEnable()
    {
        saveDefaultConfig();
        System.out.println("[IRMC] Initialising Bot...");
        getServer().getScheduler().runTaskAsynchronously(this, bot);
        registerEvents();
        registerCommands();
    }

    private void registerEvents()
    {
    }

    private void registerCommands()
    {
    }
}
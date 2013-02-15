package com.notoriousdev.irmc.events.bukkit;

import com.notoriousdev.irmc.Bot;
import com.notoriousdev.irmc.IRMC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/* Server Listener - Joins, Quits, Kicks, Bans, Server Messages */
public class ServerListener implements Listener
{

    private final IRMC plugin;
    private final Bot bot;

    public ServerListener(IRMC plugin, Bot bot)
    {
        this.plugin = plugin;
        this.bot = bot;
    }

    @EventHandler
    public void playerJoin(final PlayerJoinEvent event)
    {
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, new Runnable()
        {
            @Override
            public void run()
            {
                bot.relayServerMessage(String.format("%s has joined the server!", event.getPlayer().getName()));
            }
        });
    }

    @EventHandler
    public void playerQuit(final PlayerQuitEvent event)
    {
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, new Runnable()
        {
            @Override
            public void run()
            {
                bot.relayServerMessage(String.format("%s has left the server!", event.getPlayer().getName()));
            }
        });
    }

    @EventHandler
    public void playerKicked(final PlayerKickEvent event)
    {
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, new Runnable()
        {
            @Override
            public void run()
            {
                bot.relayServerMessage(String.format("%s has been kicked for reason: %s", event.getPlayer().getName(), event.getReason()));
            }
        });
    }
}

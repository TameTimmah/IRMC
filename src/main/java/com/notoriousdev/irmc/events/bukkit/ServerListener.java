package com.notoriousdev.irmc.events.bukkit;

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

    public ServerListener(IRMC plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent event)
    {
    }

    @EventHandler
    public void playerQuit(PlayerQuitEvent event)
    {
    }

    @EventHandler
    public void playerKicked(PlayerKickEvent event)
    {
    }
}

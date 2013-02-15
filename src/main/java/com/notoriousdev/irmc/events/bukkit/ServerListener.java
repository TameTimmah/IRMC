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
    public void playerJoin(PlayerJoinEvent event)
    {
        bot.relayServerMessage(String.format("{%s has joined the server!}", event.getPlayer().getName()));
    }

    @EventHandler
    public void playerQuit(PlayerQuitEvent event)
    {
        bot.relayServerMessage(String.format("{%s has left the server!}", event.getPlayer().getName()));
    }

    @EventHandler
    public void playerKicked(PlayerKickEvent event)
    {
        bot.relayServerMessage(String.format("{%s has been kicked for reason: %s}", event.getPlayer().getName(), event.getReason()));
    }
}

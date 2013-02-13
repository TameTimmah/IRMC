package com.notoriousdev.irmc.events.bukkit;

import com.notoriousdev.irmc.Bot;
import com.notoriousdev.irmc.IRMC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/* Player Listener - Chat, Commands (/me, etc), Death */
public class PlayerListener implements Listener
{

    private final IRMC plugin;
    private final Bot bot;

    public PlayerListener(IRMC plugin, Bot bot)
    {
        this.plugin = plugin;
        this.bot = bot;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void playerChat(final AsyncPlayerChatEvent event)
    {
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, new Runnable()
        {
            @Override
            public void run()
            {
                bot.chatToIrc(event.getPlayer(), event.getMessage());
            }
        });
    }

    @EventHandler
    public void playerCommand(PlayerCommandPreprocessEvent event)
    {
    }

    @EventHandler
    public void playerDeath(PlayerDeathEvent event)
    {
    }
}

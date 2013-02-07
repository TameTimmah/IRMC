package com.notoriousdev.irmc.events.bukkit;

import com.notoriousdev.irmc.IRMC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerListener implements Listener
{
    private IRMC plugin;
    
    public PlayerListener (IRMC plugin)
    {
        this.plugin = plugin;
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onChat(AsyncPlayerChatEvent event)
    {
        
    }
}

package com.notoriousdev.irmc.events;

import com.notoriousdev.irmc.irc.Bot;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {

    private Bot bot;
    public String message;

    public void onPlayerChat(AsyncPlayerChatEvent event){
        message = event.getMessage();

    }

    public String getGameMessage() {
        return message;
    }

}

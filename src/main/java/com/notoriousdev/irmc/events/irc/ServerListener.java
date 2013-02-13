package com.notoriousdev.irmc.events.irc;

import com.notoriousdev.irmc.Bot;
import com.notoriousdev.irmc.IRMC;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.PartEvent;

/* Server Listener - Joins, Parts */
public class ServerListener extends ListenerAdapter
{

    private final IRMC plugin;
    private final Bot bot;

    public ServerListener(IRMC plugin, Bot bot)
    {
        this.plugin = plugin;
        this.bot = bot;
    }

    @Override
    public void onJoin(JoinEvent event)
    {
        String relay = String.format("[IRMC] %s has joined %s", event.getUser().getNick(), event.getChannel().getName());
        plugin.relayToServer(relay);
    }
    
    @Override
    public void onPart(PartEvent event)
    {
        String relay = String.format("[IRMC] %s has left %s: %s", event.getUser().getNick(), event.getChannel().getName(), event.getReason());
        plugin.relayToServer(relay);
    }
}

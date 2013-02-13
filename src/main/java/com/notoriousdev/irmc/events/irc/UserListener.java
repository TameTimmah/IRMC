package com.notoriousdev.irmc.events.irc;

import com.notoriousdev.irmc.Bot;
import com.notoriousdev.irmc.IRMC;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.MessageEvent;

/* User Listener - Chat, Action, Interacting with Bot */
public class UserListener extends ListenerAdapter
{

    private final IRMC plugin;
    private final Bot bot;

    public UserListener(IRMC plugin, Bot bot)
    {
        this.plugin = plugin;
        this.bot = bot;
    }

    @Override
    public void onMessage(MessageEvent event)
    {
        String relay = String.format("[IRMC] {%s} %s", event.getUser().getNick(), event.getMessage());
        plugin.relayToServer(relay);
    }
}

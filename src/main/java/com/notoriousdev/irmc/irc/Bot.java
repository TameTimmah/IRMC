package com.notoriousdev.irmc.irc;

import com.notoriousdev.irmc.IRMC;
import java.io.IOException;
import java.nio.channels.Channels;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

public class Bot {
    
    private final PircBotX bot;
    private final IRMC plugin;
    
    public Bot(IRMC plugin) {
        this.plugin = plugin;
        bot = new PircBotX();
    }
    
    public void startHere() {
        // Lets test some stuff, shall we?
        bot.setName("Professor`Oak");
        try {
            bot.connect("irc.esper.net");
        } catch (IOException ex) {
            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IrcException ex) {
            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
        }
        bot.joinChannel("#thereverend403");
        bot.getChannel("#thereverend403").sendMessage("Connection was a success!");
    }
    public void endHere() {
        for (Channel channel : bot.getChannels()) { bot.partChannel(channel); }
        bot.disconnect();
    }
}

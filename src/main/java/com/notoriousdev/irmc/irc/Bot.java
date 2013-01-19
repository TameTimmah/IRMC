package com.notoriousdev.irmc.irc;

import com.notoriousdev.irmc.IRMC;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

public class Bot {

    public final PircBotX bot;
    public final IRMC plugin;
    String nick;
    String channel;

    public Bot(IRMC plugin){
        this.plugin = plugin;
        bot = new PircBotX();

    }
    public void connect(){
      nick = plugin.getConfig().getString("account.nick");
      channel = plugin.getConfig().getString("account.channel");
      bot.setName(nick);
      try {
          bot.connect("irc.esper.net");
      }  catch (IOException ex) {
          Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
      }  catch (IrcException ex) {
          Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
      }
      bot.joinChannel(channel);
      bot.getChannel(channel).sendMessage("Connection success!");
    }

    public  void disconnect(){
        for (Channel channel : bot.getChannels()) { bot.partChannel(channel); }
        bot.disconnect();
    }

}

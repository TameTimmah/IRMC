package com.notoriousdev.irmc.irc;

import com.notoriousdev.irmc.IRMC;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.notoriousdev.irmc.docs.IrcConfig;
import com.sun.org.apache.xerces.internal.xs.StringList;
import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

public class Bot {

    public final PircBotX bot;
    public final IRMC plugin;
    public static IrcConfig ircConfig;
    public static Configuration config;
    String nick;
    String server;
    private static List<String> channels;

    public Bot(IRMC plugin){
        this.plugin = plugin;
        bot = new PircBotX();

    }
    public void connect(){
      channels = Configuration.getChannelList();
      server = Configuration.getServerAddress();
      nick = Configuration.getBotNickname();
      try {
          bot.connect(server);
      }  catch (IOException ex) {
          Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
      }  catch (IrcException ex) {
          Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
      }
      for (String s : channels){
      bot.setName(nick);
      bot.joinChannel(s);
      bot.getChannel(s).sendMessage("Connection success!");
      }
    }

    public  void disconnect(){
        for (Channel channel : bot.getChannels()) { bot.partChannel(channel); }
        bot.disconnect();
    }

}

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

    public Bot(IRMC plugin){
        this.plugin = plugin;
        bot = new PircBotX();

    }
    public void connect(){
      Configuration.loadOptions();
      bot.setName(Configuration.getBotNickname());
      connectToServer();
      for (String s : channels){
      bot.joinChannel(s);
      bot.getChannel(s).sendMessage("Connection success!");
      }
    }
    
    public static void connectToServer() {
        String server = Configuration.getServerAddress();
        int port = Configuration.getServerPort();
        String password = Configuration.getServerPassword();
        try {
            if (Configuration.useServerSSL() && Configuration.verifyServerSSL()) {
                bot.connect(server, port, password, new UtilSSLSocketFactory());
            } else if (Configuration.useServerSSL() && !Configuration.verifyServerSSL()) {
                bot.connect(server, port, password, new UtilSSLSocketFactory().trustAllCertificates());
            } else {
                bot.connect(server, port);
            }
        } catch (Exception e) {
        }
    }

    public  void disconnect(){
        for (Channel channel : bot.getChannels()) {
            bot.partChannel(channel);
        }
        bot.disconnect();
    }

}

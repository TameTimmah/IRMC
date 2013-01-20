package com.notoriousdev.irmc.irc;

import com.notoriousdev.irmc.IRMC;
import java.util.List;
import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.UtilSSLSocketFactory;

public class Bot {

    Configuration config = new Configuration();

    public final PircBotX bot;
    public final IRMC plugin;

    public Bot(IRMC plugin){
        this.plugin = plugin;
        bot = new PircBotX();

    }
    public void connect(){
      bot.setVerbose(true);
      config.loadConfig();
      bot.setName(config.getBotNickname());
      connectToServer();
      List<String> channels = config.getChannelList();
      for (String s : channels){
      bot.joinChannel(s);
      bot.getChannel(s).sendMessage("Connection success!");
      }
    }
    
    public void connectToServer() {
        String server = config.getServerAddress();
        int port = config.getServerPort();
        String password = config.getServerPassword();
        try {
            if (config.useServerSSL() && config.verifyServerSSL()) {
                bot.connect(server, port, password, new UtilSSLSocketFactory());
            } else if (config.useServerSSL() && !config.verifyServerSSL()) {
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

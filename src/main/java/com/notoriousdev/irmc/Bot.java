package com.notoriousdev.irmc;

import java.io.IOException;
import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

public class Bot implements Runnable
{

    private PircBotX bot;

    @Override
    public void run()
    {
        bot = new PircBotX();
        configureBot();
        addListeners();
        connect();
        joinChannels();
    }

    public void kill()
    {
        System.out.println("[IRMC] Attempting to kill Bot...");
        for (Channel channel : bot.getChannels()) {
            bot.partChannel(channel, String.format("Disconnecting %s from the Minecraft server...", bot.getName()));
        }
        System.out.println("[IRMC] Attempting disconnection...");
        bot.disconnect();
        System.out.println("[IRMC] Disconnected!");
    }

    private void configureBot()
    {
    }

    private void addListeners()
    {
    }

    private void connect()
    {
        System.out.println("[IRMC] Attempting connection...");
        try {
            bot.connect("irc.esper.net", 6667);
        } catch (IOException ex) {
        } catch (IrcException ex) {
        }
        System.out.println("[IRMC] Connected!");
    }

    private void joinChannels()
    {
        System.out.println("[IRMC] Attempting to join channels...");
        bot.joinChannel("#IRMC");
    }
}
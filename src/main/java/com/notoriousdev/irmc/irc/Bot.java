package com.notoriousdev.irmc.irc;

import org.pircbotx.PircBotX;

public class Bot {

    private static PircBotX bot;

    public static void start() {
        bot = new PircBotX();
    }
}

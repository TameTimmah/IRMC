package com.notoriousdev.irmc.commands;

import com.notoriousdev.irmc.irc.Bot;
import com.notoriousdev.irmc.docs.IrcConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandIRMC implements CommandExecutor
{

    private Bot bot;
    public static IrcConfig ircConfig;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (commandLabel.equalsIgnoreCase("irmc"))
        {
            if (args[0].equalsIgnoreCase("reload"))
            {
                bot.disconnect();
                ircConfig.reloadConfig();
                bot.connect();
            }
        }
        return false;
    }
}

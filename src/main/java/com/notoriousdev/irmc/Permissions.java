package com.notoriousdev.irmc;

import org.bukkit.command.CommandSender;

public enum Permissions
{

    /* Bukkit Commands */
    COMMAND_BUKKIT_IRMC,
    /* IRC Commands */
    COMMAND_IRC_COMMAND,
    /* Bukkit Messages */
    MESSAGE_BUKKIT_MESSAGE,
    /* IRC Messages */
    MESSAGE_IRC_MESSAGE,
    FINAL_PERMISSION;

    public boolean isAuthorised(CommandSender sender)
    {
        return sender.hasPermission(this.toString());
    }

    @Override
    public String toString()
    {
        return "irmc." + this.name().toLowerCase().replace("_", ".");
    }
}

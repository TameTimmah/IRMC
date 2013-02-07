package com.notoriousdev.irmc;

import java.util.List;
import lombok.Getter;
import org.bukkit.configuration.file.*;

public class Conf extends YamlConfiguration
{

    private IRMC plugin;
    private FileConfiguration config;
    private @Getter boolean verbose;
    private @Getter boolean debug;
    private @Getter boolean autoNick;
    private @Getter boolean autoRejoin;
    private @Getter boolean autoReconnect;
    private @Getter boolean autoSplit;
    private @Getter String nick;
    private @Getter String ident;
    private @Getter String pass;
    private @Getter String server;
    private @Getter int port;
    private @Getter String serverPass;
    private @Getter boolean ssl;
    private @Getter boolean verifySsl;
    private @Getter List<String> channels;

    public Conf(IRMC plugin)
    {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    public void loadConfig()
    {
        this.debug = config.getBoolean("irc.debug", false);
        this.verbose = config.getBoolean("irc.verbose", false);
        this.autoNick = config.getBoolean("irc.auto-change-nick", true);
        this.autoRejoin = config.getBoolean("irc.auto-rejoin-channel", true);
        this.autoReconnect = config.getBoolean("irc.auto-reconnect-server", true);
        this.autoSplit = config.getBoolean("irc.auto-split-message", true);

        this.nick = config.getString("irc.identity.nickname");
        this.ident = config.getString("irc.identity.ident");
        this.pass = config.getString("irc.identity.password");

        this.server = config.getString("irc.server.address");
        this.port = config.getInt("irc.server.port");
        this.serverPass = config.getString("irc.server.password", "");
        this.ssl = config.getBoolean("irc.server.use-ssl", false);
        this.verifySsl = config.getBoolean("irc.server.verify-ssl", false);

        this.channels = config.getStringList("irc.channels");
        plugin.getLogger().info("SHITS LOADED YO");
    }
}

package com.notoriousdev.irmc;

import java.util.List;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import lombok.Getter;
import lombok.Setter;

public class Config extends YamlConfiguration
{

    private IRMC plugin;
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

    public Config(IRMC plugin)
    {
        this.plugin = plugin;
    }

    public void loadConfig()
    {
        this.debug = getBoolean("irc.debug", false);
        this.verbose = getBoolean("irc.verbose", false);
        this.autoNick = getBoolean("irc.auto-change-nick", true);
        this.autoRejoin = getBoolean("irc.auto-rejoin-channel", true);
        this.autoReconnect = getBoolean("irc.auto-reconnect-server", true);
        this.autoSplit = getBoolean("irc.auto-split-message", true);

        this.nick = getString("irc.identity.nickname");
        this.ident = getString("irc.identity.ident");
        this.pass = getString("irc.identity.password");

        this.server = getString("irc.server.address");
        this.port = super.getInt("irc.server.port", 6667);
        this.serverPass = getString("irc.server.password");
        this.ssl = getBoolean("irc.server.use-ssl", true);
        this.verifySsl = getBoolean("irc.server.verify-ssl");

        this.channels = getStringList("irc.channels");
    }
}

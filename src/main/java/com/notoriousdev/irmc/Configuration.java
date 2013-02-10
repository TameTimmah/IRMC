package com.notoriousdev.irmc;

import java.io.File;
import java.io.IOException;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.FileConfiguration;

public class Configuration
{

    private final IRMC plugin;
    private final FileConfiguration config;
    /* General Config Options */
    @Getter
    @Setter
    private boolean debug;
    /* Bukkit Config Options */
    /* IRC Config Options */
    @Getter
    @Setter
    private boolean verbose;
    @Getter
    @Setter
    private boolean autoNick;
    @Getter
    @Setter
    private boolean autoRejoin;
    @Getter
    @Setter
    private boolean autoReconnect;
    @Getter
    @Setter
    private boolean autoSplit;
    @Getter
    @Setter
    private String address;
    @Getter
    @Setter
    private int port;
    @Getter
    @Setter
    private String serverPass;
    @Getter
    @Setter
    private boolean useSSL;
    @Getter
    @Setter
    private boolean verifySSL;
    @Getter
    @Setter
    private String nickname;
    @Getter
    @Setter
    private String ident;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private List<String> channels;
    @Getter
    @Setter
    private String trigger;

    public Configuration(IRMC plugin)
    {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    public void loadBukkitConfig()
    {
    }

    public void loadIrcConfig()
    {
        /* Settings */
        this.verbose = debug && config.getBoolean("irc.verbose", false);
        this.autoNick = config.getBoolean("irc.auto-nick-change", true);
        this.autoRejoin = config.getBoolean("irc.auto-rejoin-channels", true);
        this.autoReconnect = config.getBoolean("irc.auto-reconnect-server", true);
        this.autoSplit = config.getBoolean("irc.auto-split-message", true);
        /* Server */
        this.address = config.getString("irc.server.address", "irc.esper.net");
        this.port = config.getInt("irc.server.port", 6667);
        this.serverPass = config.getString("irc.server.password", "");
        this.useSSL = config.getBoolean("irc.server.use-ssl", false);
        this.verifySSL = config.getBoolean("irc.server.verify-ssl", false);
        /* Identity */
        this.nickname = config.getString("irc.identity.nickname", "IRMCBot");
        this.ident = config.getString("irc.identity.ident", "irmc");
        this.password = config.getString("irc.identity.password", "");
        /* Channels */
        this.channels = config.getStringList("irc.channels");
        /* Commands */
        this.trigger = config.getString("irc.prefix", "IRMC, ");
    }

    public void loadConfig()
    {
        loadBukkitConfig();
        loadIrcConfig();
    }

    public void addChannel(String channel)
    {
        if (!channels.contains(channel)) {
            channels.add(channel);
        }
    }

    public void removeChannel(String channel)
    {
        if (channels.contains(channel)) {
            channels.remove(channel);
        }
    }

    public String getPortString()
    {
        return String.valueOf(port);
    }

    public void setConfig(String path, Object setting)
    {
        config.set(path, setting);
        try {
            config.save(new File(plugin.getDataFolder(), "config.yml"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

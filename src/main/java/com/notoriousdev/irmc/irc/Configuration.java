package com.notoriousdev.irmc.irc;

import com.notoriousdev.irmc.IRMC;

import java.util.List;

public class Configuration
{

    private boolean enable_verbose;
    private boolean enable_auto_nickchange;
    private boolean enable_auto_reconnect_server;
    private boolean enable_auto_reconnect_channels;
    private boolean enable_auto_split_messages;
    //
    private String server_address;
    private int server_port;
    private String server_password;
    private boolean server_use_ssl;
    private boolean server_verify_ssl;
    //
    private String bot_nickname;
    private String bot_ident;
    private Boolean bot_use_nickserv;
    private String bot_password;
    //
    private String ctcp_version;
    private String ctcp_finger;
    //
    private List<String> channels_list;
    //
    private String command_trigger;
    //
    private List<String> user_permissions;
    private List<String> voice_permissions;
    private List<String> halfop_permissions;
    private List<String> op_permissions;
    private List<String> superop_permissions;
    private List<String> owner_permissions;
    //
    private List<String> accesslist;

    public void loadConfig()
    {
        enable_verbose = IRMC.ircConfig.getConfig().getBoolean("settings.enable-verbose");
        enable_auto_nickchange = IRMC.ircConfig.getConfig().getBoolean("settings.enable-auto-nickchange");
        enable_auto_reconnect_server = IRMC.ircConfig.getConfig().getBoolean("settings.enable-auto-aerver-reconnect");
        enable_auto_reconnect_channels = IRMC.ircConfig.getConfig().getBoolean("settings.enable-auto-channel-reconnect");
        enable_auto_split_messages = IRMC.ircConfig.getConfig().getBoolean("settings.enable-auto-split-messages");
        server_address = IRMC.ircConfig.getConfig().getString("server.address");
        server_port = IRMC.ircConfig.getConfig().getInt("server.port");
        server_password = IRMC.ircConfig.getConfig().getString("server.password");
        server_use_ssl = IRMC.ircConfig.getConfig().getBoolean("server.use-ssl");
        server_verify_ssl = IRMC.ircConfig.getConfig().getBoolean("server.verify-ssl");
        bot_nickname = IRMC.ircConfig.getConfig().getString("bot.nickname");
        bot_ident = IRMC.ircConfig.getConfig().getString("bot.ident");
        bot_password = IRMC.ircConfig.getConfig().getString("bot.password");
        bot_use_nickserv = IRMC.ircConfig.getConfig().getBoolean("bot.use-nickserv");
        ctcp_version = IRMC.ircConfig.getConfig().getString("ctcp.version");
        ctcp_finger = IRMC.ircConfig.getConfig().getString("ctcp.finger");
        channels_list = IRMC.ircConfig.getConfig().getStringList("channels");
        command_trigger = IRMC.ircConfig.getConfig().getString("command-trigger");
        user_permissions = IRMC.ircConfig.getConfig().getStringList("permissions.user");
        voice_permissions = IRMC.ircConfig.getConfig().getStringList("permissions.voice");
        halfop_permissions = IRMC.ircConfig.getConfig().getStringList("permissions.halfop");
        op_permissions = IRMC.ircConfig.getConfig().getStringList("permissions.op");
        superop_permissions = IRMC.ircConfig.getConfig().getStringList("permissions.superop");
        owner_permissions = IRMC.ircConfig.getConfig().getStringList("permissions.owner");
        accesslist = IRMC.ircConfig.getConfig().getStringList("accessList");
    }

    public boolean verboseEnabled()
    {
        return enable_verbose;
    }

    public boolean autoNickchangeEnabled()
    {
        return enable_auto_nickchange;
    }

    public boolean autoReconnectServerEnabled()
    {
        return enable_auto_reconnect_server;
    }

    public boolean autoReconnectChannelsEnabled()
    {
        return enable_auto_reconnect_channels;
    }

    public boolean autoSplitMessagesEnabled()
    {
        return enable_auto_split_messages;
    }

    public String getServerAddress()
    {
        return server_address;
    }

    public int getServerPort()
    {
        return server_port;
    }

    public String getServerPassword()
    {
        return server_password;
    }

    public boolean useServerSSL()
    {
        return server_use_ssl;
    }

    public boolean verifyServerSSL()
    {
        return server_verify_ssl;
    }

    public String getBotNickname()
    {
        return bot_nickname;
    }

    public String getBotIdent()
    {
        return bot_ident;
    }

    public String getBotPassword()
    {
        return bot_password;
    }

    public boolean useNickserv()
    {
        return bot_use_nickserv;
    }

    public String respondCTCPVersion()
    {
        return ctcp_version;
    }

    public String respondCTCPFinger()
    {
        return ctcp_finger;
    }

    public List<String> getChannelList()
    {
        return channels_list;
    }

    public String getCommandTrigger()
    {
        return command_trigger;
    }

    public List<String> getUserPermissions()
    {
        return user_permissions;
    }

    public List<String> getVoicePermissions()
    {
        return voice_permissions;
    }

    public List<String> getHalfopPermissions()
    {
        return halfop_permissions;
    }

    public List<String> getOpPermissions()
    {
        return op_permissions;
    }

    public List<String> getSuperopPermissions()
    {
        return superop_permissions;
    }

    public List<String> getOwnerPermissions()
    {
        return owner_permissions;
    }

    public List<String> getAccessList()
    {
        return accesslist;
    }
}

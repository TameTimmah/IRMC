package com.notoriousdev.irmc.irc;

import com.notoriousdev.irmc.IRMC;
import java.util.List;

public class Configuration {

    private static boolean enable_verbose;
    private static boolean enable_auto_nickchange;
    private static boolean enable_auto_reconnect_server;
    private static boolean enable_auto_reconnect_channels;
    private static boolean enable_auto_split_messages;
    //
    private static String server_address;
    private static int server_port;
    private static String server_password;
    private static boolean server_use_ssl;
    private static boolean server_verify_ssl;
    //
    private static String bot_nickname;
    private static String bot_ident;
    private static String bot_password;
    //
    private static String ctcp_version;
    private static String ctcp_finger;
    //
    private static List<String> channels_list;
    //
    private static String command_trigger;
    //
    private static List<String> user_permissions;
    private static List<String> voice_permissions;
    private static List<String> halfop_permissions;
    private static List<String> op_permissions;
    private static List<String> superop_permissions;
    private static List<String> owner_permissions;
    //
    private static List<String> accesslist;

    public static void loadConfig() {
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

    public static boolean verboseEnabled() {
        return enable_verbose;
    }

    public static boolean autoNickchangeEnabled() {
        return enable_auto_nickchange;
    }

    public static boolean autoReconnectServerEnabled() {
        return enable_auto_reconnect_server;
    }

    public static boolean autoReconnectChannelsEnabled() {
        return enable_auto_reconnect_channels;
    }

    public static boolean autoSplitMessagesEnabled() {
        return enable_auto_split_messages;
    }

    public static String getServerAddress() {
        return server_address;
    }

    public static int getServerPort() {
        return server_port;
    }

    public static String getServerPassword() {
        return server_password;
    }

    public static boolean useServerSSL() {
        return server_use_ssl;
    }

    public static boolean verifyServerSSL() {
        return server_verify_ssl;
    }

    public static String getBotNickname() {
        return bot_nickname;
    }

    public static String getBotIdent() {
        return bot_ident;
    }

    public static String getBotPassword() {
        return bot_password;
    }

    public static String respondCTCPVersion() {
        return ctcp_version;
    }

    public static String respondCTCPFinger() {
        return ctcp_finger;
    }

    public static List<String> getChannelList() {
        return channels_list;
    }

    public static String getCommandTrigger() {
        return command_trigger;
    }

    public static List<String> getUserPermissions() {
        return user_permissions;
    }

    public static List<String> getVoicePermissions() {
        return voice_permissions;
    }

    public static List<String> getHalfopPermissions() {
        return halfop_permissions;
    }

    public static List<String> getOpPermissions() {
        return op_permissions;
    }

    public static List<String> getSuperopPermissions() {
        return superop_permissions;
    }

    public static List<String> getOwnerPermissions() {
        return owner_permissions;
    }

    public static List<String> getAccessList() {
        return accesslist;
    }
}

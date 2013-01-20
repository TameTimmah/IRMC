package com.notoriousdev.irmc.docs;

import com.notoriousdev.irmc.IRMC;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class IrcConfig {

    private final IRMC plugin;
    private static FileConfiguration config;
    private static File configFile;
    private static String fileName = "irc.yml";

    public IrcConfig(IRMC plugin) {
        this.plugin = plugin;
    }

    public void reloadConfig() {
        if (configFile == null) {
            configFile = new File(plugin.getDataFolder(), fileName);
        }
        config = YamlConfiguration.loadConfiguration(configFile);

        InputStream defConfigStream = plugin.getResource(fileName);
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            config.setDefaults(defConfig);
        }
    }

    public FileConfiguration getConfig() {
        if (config == null) {
            this.reloadConfig();
        }
        return config;
    }

    public void saveConfig() {
        if (config == null || configFile == null) {
            return;
        }
        try {
            getConfig().save(configFile);
        } catch (IOException ex) {
            // TODO: Catch this error in a nice way
        }
    }

    public void saveDefaultConfig() {
        if (!new File(plugin.getDataFolder(), fileName).exists()) {
            this.plugin.saveResource(fileName, false);
        }
    }
}

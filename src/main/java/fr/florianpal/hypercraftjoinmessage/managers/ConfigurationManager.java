package fr.florianpal.hypercraftjoinmessage.managers;


import fr.florianpal.hypercraftjoinmessage.HypercraftJoinMessage;
import fr.florianpal.hypercraftjoinmessage.configurations.DatabaseConfig;
import fr.florianpal.hypercraftjoinmessage.configurations.MessageConfig;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigurationManager {
    private HypercraftJoinMessage core;

    private DatabaseConfig database = new DatabaseConfig();
    private File databaseFile;
    private Configuration databaseConfig;

    private MessageConfig message = new MessageConfig();
    private File messageFile;
    private Configuration messageConfig;

    private File langFile;
    private Configuration langConfig;

    public ConfigurationManager(HypercraftJoinMessage core) {
        try {
            this.core = core;

            messageFile = new File(this.core.getDataFolder(), "messages.yml");
            core.createDefaultConfiguration(messageFile, "messages.yml");
            messageConfig = ConfigurationProvider.getProvider(YamlConfiguration.class).load(messageFile);

            databaseFile = new File(this.core.getDataFolder(), "database.yml");
            core.createDefaultConfiguration(databaseFile, "database.yml");
            databaseConfig = ConfigurationProvider.getProvider(YamlConfiguration.class).load(databaseFile);

            message.load(messageConfig);
            database.load(databaseConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        message.load(messageConfig);
    }

    public MessageConfig getMessage() {
        return message;
    }

    public DatabaseConfig getDatabase() {
        return database;
    }
}

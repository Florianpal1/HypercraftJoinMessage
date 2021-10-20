package fr.florianpal.fjoinmessage;

import fr.florianpal.fjoinmessage.commands.JoinMessage;
import fr.florianpal.fjoinmessage.listeners.JoinListener;
import fr.florianpal.fjoinmessage.listeners.LeaveListener;
import fr.florianpal.fjoinmessage.managers.CommandManager;
import fr.florianpal.fjoinmessage.managers.ConfigurationManager;
import fr.florianpal.fjoinmessage.managers.DatabaseManager;
import fr.florianpal.fjoinmessage.queries.FirstJoinQueries;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.*;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class FJoinMessage extends Plugin {
    private ConfigurationManager configurationManager;
    private DatabaseManager databaseManager;
    private FirstJoinQueries firstJoinQueries;
    private CommandManager commandManager;

    @Override
    public void onEnable() {

        File languageFile = new File(getDataFolder(), "lang_fr.yml");
        createDefaultConfiguration(languageFile, "lang_fr.yml");

        configurationManager = new ConfigurationManager(this);
        commandManager = new CommandManager(this);
        commandManager.registerDependency(ConfigurationManager.class, configurationManager);

        databaseManager = new DatabaseManager(this);

        firstJoinQueries = new FirstJoinQueries(this);
        databaseManager.addRepository(firstJoinQueries);

        databaseManager.initializeTables();

        getProxy().getPluginManager().registerListener(this, new JoinListener(this));
        getProxy().getPluginManager().registerListener(this, new LeaveListener(this));

        commandManager.registerCommand(new JoinMessage(this));
    }

    public void createDefaultConfiguration(File actual, String defaultName) {
        // Make parent directories
        File parent = actual.getParentFile();
        if (!parent.exists()) {
            parent.mkdirs();
        }

        if (actual.exists()) {
            return;
        }

        InputStream input = null;
        try {
            JarFile file = new JarFile(this.getFile());
            ZipEntry copy = file.getEntry(defaultName);
            if (copy == null) throw new FileNotFoundException();
            input = file.getInputStream(copy);
        } catch (IOException e) {
            getLogger().severe("Unable to read default configuration: " + defaultName);
        }

        if (input != null) {
            FileOutputStream output = null;

            try {
                output = new FileOutputStream(actual);
                byte[] buf = new byte[8192];
                int length;
                while ((length = input.read(buf)) > 0) {
                    output.write(buf, 0, length);
                }

                getLogger().info("Default configuration file written: " + actual.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    input.close();
                } catch (IOException ignored) {
                }

                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException ignored) {
                }
            }
        }


    }

    public ConfigurationManager getConfigurationManager() {
        return configurationManager;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public FirstJoinQueries getFirstJoinQueries() {
        return firstJoinQueries;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}

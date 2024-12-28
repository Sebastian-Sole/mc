package bongothy;

import bongothy.commands.UserInput;
import bongothy.listeners.BlockBreak;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.SQLException;
import java.util.logging.Logger;

public final class PluginMain extends JavaPlugin {

    private GameEngine gameEngine;
    private Logger logger = Logger.getLogger("bongothy.PluginMain");
    private FileConfiguration databaseConfig;
    private File databaseConfigFile;
    private DatabaseManager databaseManager;

    @Override
    public void onEnable() {
        createDatabaseConfig();
        String dbHost = databaseConfig.getString("database.host");
        String dbUser = databaseConfig.getString("database.user");
        String dbPassword = databaseConfig.getString("database.password");
        String database = databaseConfig.getString("database.name");

        DatabaseManager dbManager = new DatabaseManager(dbHost, database, dbUser, dbPassword);
        try {
            dbManager.connect();
            this.databaseManager = dbManager;
        } catch (SQLException e) {
            getLogger().severe("Failed to connect to database!");
            e.printStackTrace();
            return;
        }

        // Plugin startup logic
        this.gameEngine = new GameEngine(this);
        gameEngine.setLogger(logger);
        registerEvents();
        setCommandExecutor();
        saveDefaultConfig();

        logger.info("Database host: " + dbHost);
        logger.info("Bongothy Plugin Enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            databaseManager.disconnect();
        } catch (SQLException e) {
            getLogger().severe("Failed to disconnect from database!");
            e.printStackTrace();
        }
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);
    }

    private void setCommandExecutor() {
        UserInput commands = new UserInput(gameEngine);
        for (String command : commands.getRegisteredCommands()) {
            this.getCommand(command).setExecutor(commands);
        }
        logger.info("Commands set");
        gameEngine.setCommands(commands);
    }

    private void createDatabaseConfig() {
        databaseConfigFile = new File(getDataFolder(), "database.yml");

        if (!databaseConfigFile.exists()) {
            databaseConfigFile.getParentFile().mkdirs();
            saveResource("database.yml", false);
        }

        // Load the database.yml file
        databaseConfig = YamlConfiguration.loadConfiguration(databaseConfigFile);
    }

    public File getDatabaseConfigFile() {
        return databaseConfigFile;
    }

    public FileConfiguration getDatabaseConfig() {
        return databaseConfig;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}

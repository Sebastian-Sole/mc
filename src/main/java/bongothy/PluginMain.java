package bongothy;

import bongothy.commands.UserInput;
import bongothy.listeners.BlockBreak;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class PluginMain extends JavaPlugin {

    private GameEngine gameEngine;
    private Logger logger = Logger.getLogger("manhunt_extreme.PluginMain");

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.gameEngine = new GameEngine(this);
        gameEngine.setLogger(logger);
        registerEvents();
        setCommandExecutor();
        logger.info("Bongothy Plugin Enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
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
}

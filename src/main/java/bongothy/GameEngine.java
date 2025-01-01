package bongothy;

import bongothy.commands.UserInput;
import bongothy.database.HomeStorage;
import bongothy.database.PlayerConfigData;
import bongothy.database.PlayerConfigManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.logging.Logger;


public class GameEngine {

    private PluginMain pluginMain;
    private UserInput commands;
    private Map<Player, Location> playerHomes = new HashMap<>();
    private Logger logger;

    private final HomeStorage homeStorage;
    private final PlayerConfigManager playerConfigManager;


    public GameEngine(PluginMain pluginMain) {
        this.pluginMain = pluginMain;
        this.homeStorage = new HomeStorage(pluginMain.getDatabaseManager());
        this.playerConfigManager = new PlayerConfigManager(pluginMain.getDatabaseManager());
    }

    public void updatePlayerHome(Player player, Location location) {
        playerHomes.put(player, location);
    }

    public Location getPlayerHome(Player player) {
        return playerHomes.get(player);
    }

    public void setCommands(UserInput commands) {
        this.commands = commands;
    }

    public UserInput getCommands() {
        return commands;
    }

    public PluginMain getPluginMain() {
        return pluginMain;
    }

    public void setPluginMain(PluginMain pluginMain) {
        this.pluginMain = pluginMain;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Logger getLogger() {
        return logger;
    }
    public HomeStorage getHomeStorage() {
        return homeStorage;
    }

    public PlayerConfigManager getPlayerConfigManager() {
        return playerConfigManager;
    }

}

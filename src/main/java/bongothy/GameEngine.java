package bongothy;

import bongothy.commands.UserInput;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class GameEngine {

    private PluginMain pluginMain;
    private UserInput commands;
    private Map<Player, Location> playerHomes = new HashMap<>();
    private Logger logger;


    public GameEngine(PluginMain pluginMain) {
        this.pluginMain = pluginMain;
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
}

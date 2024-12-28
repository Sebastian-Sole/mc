package bongothy.commands;

import bongothy.GameEngine;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class Home {

    private final Player player;
    private final GameEngine gameEngine;
    private final String[] args;

    public Home(Player player, GameEngine gameEngine, String[] args) {
        this.player = player;
        this.gameEngine = gameEngine;
        this.args = args;
    }

    public boolean execute() {
        if (args.length != 0) {
            player.sendMessage("Dawg you fucked up wit yo spellin. Use /home bruh bruh");
            return true;
        }
        try {
            var home = gameEngine.getHomeStorage().getHome(player.getUniqueId());
            player.teleport(home);
            player.sendMessage("Lazy ahh hoe, teleportin an shii");
            return true;
        } catch (SQLException e) {
            player.sendMessage("Bro you aint got no crib yet. Use /sethome first dawg.");
            throw new RuntimeException(e);
        }
    }
}

package bongothy.commands;

import bongothy.GameEngine;
import org.bukkit.entity.Player;

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
        var home = gameEngine.getPlayerHome(player);
        if (home == null) {
            player.sendMessage("Bro you aint got no crib yet. Use /sethome first dawg");
            return true;
        }
        player.teleport(home);
        player.sendMessage("Lazy ahh hoe, teleportin an shii");
        return true;
    }
}

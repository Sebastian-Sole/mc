package bongothy.commands;

import bongothy.GameEngine;
import org.bukkit.entity.Player;

public class SetHome {

    private final Player player;
    private final GameEngine gameEngine;
    private final String[] args;

    public SetHome(Player player, GameEngine gameEngine, String[] args) {
        this.player = player;
        this.gameEngine = gameEngine;
        this.args = args;
    }

    public boolean execute() {
        if (args.length != 0) {
            player.sendMessage("Dawg you fucked up wit yo spellin. Use /sethome bruh bruh");
            return true;
        }
        gameEngine.updatePlayerHome(player, player.getLocation());
        player.sendMessage("Crib set at " + player.getLocation().getBlockX() + ", " + player.getLocation().getBlockY() + ", " + player.getLocation().getBlockZ());
        return true;
    }
}

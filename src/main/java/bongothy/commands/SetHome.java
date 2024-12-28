package bongothy.commands;

import bongothy.GameEngine;
import org.bukkit.entity.Player;

import java.sql.SQLException;

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
        try {
            gameEngine.getHomeStorage().saveHome(player.getUniqueId(), player.getLocation());
            player.sendMessage("Crib set at " + player.getLocation().getBlockX() + ", " + player.getLocation().getBlockY() + ", " + player.getLocation().getBlockZ());
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package bongothy.commands;

import bongothy.GameEngine;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class Diamonds {
    private final Player player;
    private final GameEngine gameEngine;
    private final String[] args;

    public Diamonds(Player player, GameEngine gameEngine, String[] args) {
        this.player = player;
        this.gameEngine = gameEngine;
        this.args = args;
    }

    public boolean execute() {
        if (args.length != 2) {
            player.sendMessage("Dawg you fucked up wit yo spellin. Use /diamonds <PlayerName> <true/false> bruh bruh");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage("Player not found");
            return true;
        }

        try {
            gameEngine.getPlayerConfigManager().updateDiamondsEnabled(target.getUniqueId(), Boolean.parseBoolean(args[1]));
            player.sendMessage("Diamonds are " + (Boolean.parseBoolean(args[1]) ? "enabled" : "disabled") + " for " + args[0]);
            return true;
        } catch (SQLException e) {
            player.sendMessage("Something went wrong");
            throw new RuntimeException(e);
        }
    }
}

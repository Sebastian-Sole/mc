package bongothy.commands;

import bongothy.GameEngine;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Coords {

    private final Player player;
    private final String[] args;

    public Coords(Player player, String[] args) {
        this.player = player;
        this.args = args;
    }

    public boolean execute() {
        if (args.length != 0) {
            player.sendMessage("Dawg you fucked up wit yo spellin. Use /coords bruh bruh");
            return true;
        }

        Location location = player.getLocation();
        var dimension = Objects.requireNonNull(location.getWorld()).getEnvironment().name();

        Bukkit.broadcastMessage(dimension + ": " + location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ());
        return true;
    }
}

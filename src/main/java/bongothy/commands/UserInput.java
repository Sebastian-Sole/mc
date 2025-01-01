package bongothy.commands;

import bongothy.GameEngine;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class UserInput implements CommandExecutor {

    private final String[] registeredCommands = {
            "sethome",
            "home",
            "Home",
            "coords",
            "diamonds"
    };

    private final GameEngine gameEngine;

    public UserInput(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player senderPlayer = (Player) sender;
        switch (label) {
            case "sethome" -> {
                return new SetHome(senderPlayer, gameEngine, args).execute();
            }
            case "home", "Home" -> {
                return new Home(senderPlayer, gameEngine, args).execute();
            }
            case "coords" -> {
                return new Coords(senderPlayer, args).execute();
            }
            case "diamonds" -> {
                return new Diamonds(senderPlayer, gameEngine, args).execute();
            }
        }

        return false;
    }

    public List<String> getCompletions(String[] args, List<String> existingCompletions) {
        return switch (args[0]) {
            case "sethome", "home", "Home", "coords" -> new ArrayList<>();
            default -> existingCompletions;
        };
    }

    public String[] getRegisteredCommands() {
        return registeredCommands;
    }
}


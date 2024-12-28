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
            "coords"
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
        }

        return false;
    }

    public List<String> getCompletions(String[] args, List<String> existingCompletions) {
        switch (args[0]) {
            case "sethome":
            case "home":
            case "Home":
            case "coords":
                return new ArrayList<>();
            default:
                return existingCompletions;

        }
    }

    public String[] getRegisteredCommands() {
        return registeredCommands;
    }
}


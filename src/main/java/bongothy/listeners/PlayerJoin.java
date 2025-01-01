package bongothy.listeners;

import bongothy.GameEngine;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class PlayerJoin implements Listener {

    private final GameEngine gameEngine;

    public PlayerJoin(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        gameEngine.getPlayerConfigManager().loadConfigOnJoin(player.getUniqueId());
    }

}

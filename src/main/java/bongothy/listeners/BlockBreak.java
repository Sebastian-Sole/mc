package bongothy.listeners;

import bongothy.GameEngine;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.Material;

public class BlockBreak implements Listener {

    private final GameEngine gameEngine;

    public BlockBreak(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        var block = event.getBlock();
        if (block.getType() == Material.DIAMOND_ORE || block.getType() == Material.DEEPSLATE_DIAMOND_ORE) {
            Player player = event.getPlayer();
            if (!gameEngine.getPlayerConfigManager().isDiamondsEnabled(player.getUniqueId())) {
                block.setType(Material.AIR);
                block.getDrops().clear();
                event.getPlayer().sendMessage("nonono no diamonds for you");
            }
        }
    }

}
package bongothy.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.Material;

public class BlockBreak implements Listener {

    public BlockBreak() {

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        var block = event.getBlock();
        if (block.getType() == Material.DIAMOND_ORE) {
            block.setType(Material.AIR);
            block.getDrops().clear();
        }
        event.getPlayer().sendMessage("nonono, no diamonds for you");
    }

}
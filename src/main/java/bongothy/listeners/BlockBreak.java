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
        if (block.getType() == Material.DIAMOND_ORE || block.getType() == Material.DEEPSLATE_DIAMOND_ORE) {
//            int number = (int) (Math.random() * 100);
//            if (number == 69) {
//                block.setType(Material.AIR);
//                block.getDrops().clear();
//                event.getPlayer().sendMessage("the boogeyman stole your diamond, watch your back");
//            }

            block.setType(Material.AIR);
            block.getDrops().clear();
            event.getPlayer().sendMessage("nonono no diamonds for you");
        }
    }

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.util;

import java.util.HashSet;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

/**
 *
 * @author
 */
public class ActualHeight {
    
    private static Set<Material> set;
    static {
        set = new HashSet<>();
        set.add(Material.ACACIA_LOG);
        set.add(Material.BIRCH_LOG);
        set.add(Material.DARK_OAK_LOG);
        set.add(Material.JUNGLE_LOG);
        set.add(Material.OAK_LOG);
        set.add(Material.SPRUCE_LOG);
        
        set.add(Material.ACACIA_LEAVES);
        set.add(Material.BIRCH_LEAVES);
        set.add(Material.DARK_OAK_LEAVES);
        set.add(Material.JUNGLE_LEAVES);
        set.add(Material.OAK_LEAVES);
        set.add(Material.SPRUCE_LEAVES);
        
        set.add(Material.VINE);
        set.add(Material.BAMBOO);
        
        set.add(Material.AIR);
    }
    
    public static Location getHeight(Location loc) {
        World world = loc.getWorld();
        Block block = world.getHighestBlockAt(loc);
        while(block.getLocation().getY() > 50 && set.contains(block.getType())) {
            block = block.getRelative(BlockFace.DOWN);
        }
        return block.getLocation();
    }
}

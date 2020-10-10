/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forge_sandbox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

/**
 *
 * @author
 */
public class GroupHelper {
    public final static List<Material> CARPETS;
    static {
        CARPETS = new ArrayList<>();
        for(Material material : Material.values()) {
            if(material.toString().toUpperCase().contains("CARPET")) {
                CARPETS.add(material);
            }
        }
    }
    
    public final static BlockData OAK_LOG_Y = Bukkit.createBlockData("minecraft:oak_log[axis=y]");
    public final static Map<BlockFace, BlockData> CHEST;
    static {
        CHEST = new HashMap<>();
        CHEST.put(BlockFace.NORTH, Bukkit.createBlockData("minecraft:chest[facing=north,type=single]"));
        CHEST.put(BlockFace.SOUTH, Bukkit.createBlockData("minecraft:chest[facing=south,type=single]"));
        CHEST.put(BlockFace.EAST, Bukkit.createBlockData("minecraft:chest[facing=east,type=single]"));
        CHEST.put(BlockFace.WEST, Bukkit.createBlockData("minecraft:chest[facing=west,type=single]"));
    }
}

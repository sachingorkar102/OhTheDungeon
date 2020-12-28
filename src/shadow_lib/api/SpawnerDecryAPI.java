/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shadow_lib.api;

import java.util.SplittableRandom;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import otd.config.SimpleWorldConfig;
import otd.config.WorldConfig;

/**
 *
 * @author
 */
public class SpawnerDecryAPI {
    
    public static SplittableRandom random = new SplittableRandom();
    
    public static void setSpawnerDecry(Block block, JavaPlugin plugin) {
        
        World world = block.getWorld();
        String name = world.getName();
        
        double rate = 0;
        if(WorldConfig.wc.dict.containsKey(name)) {
            SimpleWorldConfig swc = WorldConfig.wc.dict.get(name);
            rate = swc.spawner_rejection_rate;
        }
        
        if(random.nextDouble() < rate) {
            block.setType(Material.AIR);
            return;
        }
        
        TileState ts = (TileState) block.getState();
        NamespacedKey key = new NamespacedKey(plugin, "decry");
        ts.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte)15);
        
        ts.update(true, false);
    }
}

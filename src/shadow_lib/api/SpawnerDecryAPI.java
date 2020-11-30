/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shadow_lib.api;

import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author
 */
public class SpawnerDecryAPI {
    public static void setSpawnerDecry(Block block, JavaPlugin plugin) {
        TileState ts = (TileState) block.getState();
        NamespacedKey key = new NamespacedKey(plugin, "decry");
        ts.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte)15);
        
        ts.update(true, false);
    }
}

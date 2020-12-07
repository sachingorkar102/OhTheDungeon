/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.world;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;

/**
 *
 * @author shadow
 */
public class WorldGenOptimization implements Listener {
    @EventHandler
    public void onWorldInit(WorldInitEvent e){
         if(e.getWorld().getName().equalsIgnoreCase(WorldDefine.WORLD_NAME)){
               e.getWorld().setKeepSpawnInMemory(false);
         }
    }
}

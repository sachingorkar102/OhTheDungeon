/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.world;

import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldLoadEvent;

/**
 *
 * @author shadow
 */
public class WorldGenOptimization implements Listener {
    @EventHandler
    public void onWorldInit(WorldInitEvent e){
         if(e.getWorld().getName().equalsIgnoreCase(WorldDefine.WORLD_NAME)){
               e.getWorld().setKeepSpawnInMemory(false);
               e.getWorld().setTime(6000);
               e.getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
               e.getWorld().setGameRule(GameRule.DO_WEATHER_CYCLE, false);
               e.getWorld().setDifficulty(Difficulty.HARD);
         }
    }
    @EventHandler
    public void onWorldLoad(WorldLoadEvent e){
         if(e.getWorld().getName().equalsIgnoreCase(WorldDefine.WORLD_NAME)){
               e.getWorld().setKeepSpawnInMemory(false);
               e.getWorld().setTime(6000);
               e.getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
               e.getWorld().setGameRule(GameRule.DO_WEATHER_CYCLE, false);
               e.getWorld().setDifficulty(Difficulty.HARD);
         }
    }
}

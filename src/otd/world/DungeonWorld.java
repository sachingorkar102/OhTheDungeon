/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.world;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.FileUtils;
import org.bukkit.entity.Player;
import otd.config.WorldConfig;
import otd.gui.dungeon_plot.UserTeleport;

/**
 *
 * @author shadow
 */
public class DungeonWorld {
    public static World world = null;
    public static DungeonWorldChunkGenerator generator = new DungeonWorldChunkGenerator();
    
    public static void loadDungeonWorld() {
        WorldCreator wc = new WorldCreator(WorldDefine.WORLD_NAME);
        wc.environment(World.Environment.NORMAL);
        wc.generator(generator);
        
        wc.generateStructures(false);
        wc.type(WorldType.NORMAL);
        
        world = wc.createWorld();
    }
    
    public static boolean generateDungeonWorld() {
        
        File container = Bukkit.getWorldContainer();
        File world_folder = new File(container, WorldDefine.WORLD_NAME);
        if(world_folder.exists()) {
            try {
                FileUtils.deleteDirectory(world_folder);
            } catch(IOException ex) {
                return false;
            }
        }
        loadDungeonWorld();
        return true;
    }
    
    public static boolean removeDungeonWorld() {
        if(world == null) return false;
        
        List<Player> ps = world.getPlayers();
        for(Player p : ps) {
            UserTeleport.teleportBed(p);
        }
        
        File fw = world.getWorldFolder();
        
        if(!Bukkit.unloadWorld(world, false)) return false;
        world = null;
        
        try {
            FileUtils.deleteDirectory(fw);
            WorldConfig.wc.dungeon_world.finished = false;
            WorldConfig.save();
            return true;
        } catch(IOException ex) {
            return false;
        }
    }
}

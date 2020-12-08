/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.world;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.FileUtils;

/**
 *
 * @author shadow
 */
public class DungeonWorld {
    public static World world = null;
    public static DungeonWorldChunkGenerator generator = new DungeonWorldChunkGenerator();
    public static void generateDungeonWorld() {
        WorldCreator wc = new WorldCreator(WorldDefine.WORLD_NAME);
        wc.environment(World.Environment.NORMAL);
        wc.generator(generator);
        
        wc.generateStructures(false);
        wc.type(WorldType.NORMAL);
        
        world = wc.createWorld();
    }
    
    public static boolean removeDungeonWorld() {
        File fw = world.getWorldFolder();
        
        Bukkit.unloadWorld(world, false);
        world = null;
        
        try {
            FileUtils.deleteDirectory(fw);
            return true;
        } catch(IOException ex) {
            return false;
        }
    }
}

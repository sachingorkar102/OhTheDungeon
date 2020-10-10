/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.generator;

import forge_sandbox.com.someguyssoftware.dungeons2.BukkitDungeonGenerator;
import java.util.Random;
import java.util.Set;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import otd.util.ActualHeight;
import zhehe.util.AsyncLog;
import zhehe.util.config.SimpleWorldConfig;
import zhehe.util.config.WorldConfig;

/**
 *
 * @author
 */
public class AntManGenerator implements IGenerator {
    @Override
    public Set<String> getBiomeExclusions(World world) {
        SimpleWorldConfig swc = WorldConfig.wc.dict.get(world.getName());
        return swc.ant_man_dungeon.biomeExclusions;
    }
    @Override
    public boolean generateDungeon(World world, Random random, Chunk chunk) {
        
        int rx = chunk.getX() * 16 + 7;
        int rz = chunk.getZ() * 16 + 7;
        Location loc = world.getHighestBlockAt(rx, rz).getLocation();
        loc = ActualHeight.getHeight(loc);
        int ry = loc.getBlockY();
//        Bukkit.getLogger().log(Level.SEVERE, ""+ry);
        if(ry < 50) return false;
        try {
            BukkitDungeonGenerator.generate(world, new Location(world, rx, ry, rz), new Random());
        } catch(Exception ex) {
            return false;
        }
        AsyncLog.logMessage("[Ant Man Dungeon @ " + world.getName() + "] x=" + rx + ", z=" + rz);
        return true;
    }
}

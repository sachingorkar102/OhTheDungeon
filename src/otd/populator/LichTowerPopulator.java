/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.populator;

import forge_sandbox.twilightforest.TFBukkitGenerator;
import java.util.Random;
import java.util.Set;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import otd.util.ActualHeight;
import otd.util.AsyncLog;
import otd.config.SimpleWorldConfig;
import otd.config.WorldConfig;

/**
 *
 * @author
 */
public class LichTowerPopulator implements IPopulator {
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
        loc = loc.subtract(0, 1, 0);
        int ry = loc.getBlockY();
        if(ry > 120) return false;
        TFBukkitGenerator.generateLichTower(world, loc, random);
        
        AsyncLog.logMessage("[Lich Tower @ " + world.getName() + "] x=" + rx + ", z=" + rz);
        return true;
    }
}

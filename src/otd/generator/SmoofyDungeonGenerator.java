/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.generator;

import java.util.Random;
import java.util.Set;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import otd.Main;
import zhehe.maze.SmoofyDungeonPopulator;
import zhehe.util.AsyncLog;
import zhehe.util.config.SimpleWorldConfig;
import zhehe.util.config.WorldConfig;

/**
 *
 * @author
 */
public class SmoofyDungeonGenerator implements IGenerator {
    @Override
    public Set<String> getBiomeExclusions(World world) {
        SimpleWorldConfig swc = WorldConfig.wc.dict.get(world.getName());
        return swc.smoofydungeon.biomeExclusions;
    }
    @Override
    public boolean generateDungeon(World world, Random random, Chunk chunk) {
        halfAsyncGenerate(world, chunk, random);
        AsyncLog.logMessage("[Smoofy Dungeon @ " + world.getName() + "] x=" + chunk.getX() * 16 + ", z=" + chunk.getZ() * 16);
        return true;
    }
    
    public static void halfAsyncGenerate(World w, Chunk c, Random rand) {
        SmoofyDungeonPopulator.SmoofyDungeonInstance instance = new SmoofyDungeonPopulator.SmoofyDungeonInstance();
        BukkitRunnable r = new BukkitRunnable() {
            int step = 0;
            @Override
            public void run() {
                boolean b = instance.placeDungeon(w, rand, c.getX(), c.getZ(), step, 3, 3, 1, 1);
                step++;
                if(b) this.cancel();
            }
        };
        r.runTaskTimer(Main.instance, 1, 1);
    }
}

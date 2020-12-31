/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.populator;

import static forge_sandbox.jaredbgreat.dldungeons.builder.Builder.placeDungeonAsync;
import java.util.Random;
import java.util.Set;
import org.bukkit.Chunk;
import org.bukkit.World;
import otd.util.AsyncLog;
import otd.config.SimpleWorldConfig;
import otd.config.WorldConfig;

/**
 *
 * @author
 */
public class DoomlikePopulator implements IPopulator {
    @Override
    public Set<String> getBiomeExclusions(World world) {
        SimpleWorldConfig swc = WorldConfig.wc.dict.get(world.getName());
        return swc.doomlike.biomeExclusions;
    }
    @Override
    public boolean generateDungeon(World world, Random random, Chunk chunk) {
        boolean res;
        try {
            res = placeDungeonAsync(random, chunk.getX(), chunk.getZ(), world);
        } catch (Throwable e) {
            return false;
        }
        if(res) AsyncLog.logMessage("[Doomlike Dungeon @ " + world.getName() + "] x=" + chunk.getX() * 16 + ", z=" + chunk.getZ() * 16);
        return res;
    }
}
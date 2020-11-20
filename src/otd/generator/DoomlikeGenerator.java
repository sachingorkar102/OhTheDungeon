/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.generator;

import static forge_sandbox.jaredbgreat.dldungeons.builder.Builder.placeDungeonAsync;
import java.util.Random;
import java.util.Set;
import org.bukkit.Chunk;
import org.bukkit.World;
import zhehe.util.AsyncLog;
import otd.util.config.SimpleWorldConfig;
import otd.util.config.WorldConfig;

/**
 *
 * @author
 */
public class DoomlikeGenerator implements IGenerator {
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
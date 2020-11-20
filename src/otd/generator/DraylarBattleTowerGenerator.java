/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.generator;

import otd.dungeon.battletower.BattleTower;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Biome;
import otd.dungeon.draylar.BattleTowerSchematics;
import zhehe.util.AsyncLog;
import zhehe.util.BiomeDictionary;
import zhehe.util.BiomeDictionary.Type;
import otd.util.config.SimpleWorldConfig;
import otd.util.config.WorldConfig;

/**
 *
 * @author
 */
public class DraylarBattleTowerGenerator implements IGenerator {
    @Override
    public Set<String> getBiomeExclusions(World world) {
        SimpleWorldConfig swc = WorldConfig.wc.dict.get(world.getName());
        return swc.draylar_battletower.biomeExclusions;
    }
    @Override
    public boolean generateDungeon(World world, Random random, Chunk chunk) {
        
        int rx = chunk.getX() * 16 + 7;
        int rz = chunk.getZ() * 16 + 7;
        
        BattleTowerSchematics.place(world, random, rx, rz);
        
        AsyncLog.logMessage("[DraylarBattleTower Dungeon @ " + world.getName() + "] x=" + rx + ", z=" + rz);
        return true;
    }
}

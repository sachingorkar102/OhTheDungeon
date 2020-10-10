/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.generator;

import otd.battletower.BattleTower;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Biome;
import zhehe.util.AsyncLog;
import zhehe.util.BiomeDictionary;
import zhehe.util.BiomeDictionary.Type;
import zhehe.util.config.SimpleWorldConfig;
import zhehe.util.config.WorldConfig;

/**
 *
 * @author
 */
public class BattleTowerGenerator implements IGenerator {
    @Override
    public Set<String> getBiomeExclusions(World world) {
        SimpleWorldConfig swc = WorldConfig.wc.dict.get(world.getName());
        return swc.battletower.biomeExclusions;
    }
    @Override
    public boolean generateDungeon(World world, Random random, Chunk chunk) {
        
        int rx = chunk.getX() * 16 + 7;
        int rz = chunk.getZ() * 16 + 7;
        int ry = world.getHighestBlockYAt(rx, rz);
        
        int choose;
        
        if(random.nextInt(100) == 0) choose = 6;
        else {
            Biome b = world.getBiome(rx, rz);
            Set<Integer> typelist = new HashSet<>();
            Set<Type> set = BiomeDictionary.getTypes(b);
            if(set.contains(Type.SANDY)) typelist.add(3);
            if(set.contains(Type.COLD)) typelist.add(4);
            if(set.contains(Type.SNOWY)) typelist.add(4);
            if(set.contains(Type.MOUNTAIN)) typelist.add(5);
            if(set.contains(Type.HILLS)) typelist.add(5);
            if(set.contains(Type.WET)) typelist.add(2);
            if(set.contains(Type.OCEAN)) typelist.add(2);
            if(set.contains(Type.RIVER)) typelist.add(2);
            if(set.contains(Type.JUNGLE)) typelist.add(7);
            if(set.contains(Type.PLAINS)) typelist.add(1);
            
            int size = typelist.size();
            if(size == 0) return false;
            int item = random.nextInt(size); // In real life, the Random object should be rather more shared than this
            int i = 0;
            Integer c = null;
            for(Integer obj : typelist)
            {
                if (i == item)
                    c = obj;
                i++;
            }
            if(c == null) c = 1;
            choose = c;
        }
        
        boolean under = false;
        if(ry > 50) under = random.nextBoolean();
        
        BattleTower.generate(world, random, rx, ry, rz, choose, under);
        
        AsyncLog.logMessage("[BattleTower Dungeon @ " + world.getName() + "] x=" + rx + ", z=" + rz);
        return true;
    }
}

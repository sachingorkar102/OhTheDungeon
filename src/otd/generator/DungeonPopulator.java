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
import org.bukkit.generator.BlockPopulator;
import otd.Main;
import shadow_manager.DungeonWorldManager;
import zhehe.util.RandomCollection;
import zhehe.util.config.SimpleWorldConfig;
import zhehe.util.config.WorldConfig;

/**
 *
 * @author
 */
public class DungeonPopulator extends BlockPopulator{
     @Override
    public void populate(final World world, final Random random, final Chunk source) {
        if(!Main.instance.isEnabled()) return;
        String world_name = world.getName();
        if(world_name.equals(DungeonWorldManager.WORLD_NAME)) return;
        if(!WorldConfig.wc.dict.containsKey(world_name)) return;
        SimpleWorldConfig swc = WorldConfig.wc.dict.get(world_name);
//        List<IGenerator> generator = new ArrayList<>();
//        List<Integer> part = new ArrayList<>();
        int distance = swc.distance;
        
        Random mrand = new Random(world.getSeed() + world_name.hashCode()
		+ (2027 * (long)(source.getX() / distance)) 
		+ (1987 * (long)(source.getX() / distance)));
        int xrand = mrand.nextInt();
        int zrand = mrand.nextInt();
        int xuse = ((source.getX() + xrand) % distance);
        int zuse = ((source.getZ() + zrand) % distance);
        
        if(xuse != 0 || zuse != 0) return;
        
        RandomCollection<IGenerator> generator = new RandomCollection<>(random);
        if(swc.roguelike.doNaturalSpawn) {
            generator.add(swc.roguelike_weight, new RoguelikeGenerator());
        }
        if(swc.doomlike.doNaturalSpawn) {
            generator.add(swc.doomlike_weight, new DoomlikeGenerator());
        }
        if(swc.battletower.doNaturalSpawn) {
            generator.add(swc.battle_tower_weight, new BattleTowerGenerator());
        }
        if(swc.smoofydungeon.doNaturalSpawn) {
            generator.add(swc.smoofy_weight, new SmoofyDungeonGenerator());
        }
        if(swc.draylar_battletower.doNaturalSpawn) {
            generator.add(swc.draylar_weight, new DraylarBattleTowerGenerator());
        }
        if(swc.ant_man_dungeon.doNaturalSpawn) {
            generator.add(swc.antman_weight, new AntManGenerator());
        }
        
        boolean r = swc.roguelike.doNaturalSpawn || swc.doomlike.doNaturalSpawn
                || swc.battletower.doNaturalSpawn || swc.smoofydungeon.doNaturalSpawn
                || swc.draylar_battletower.doNaturalSpawn || swc.ant_man_dungeon.doNaturalSpawn;
        
        if(!r) return;
        
        IGenerator choose = generator.next();
        if(choose == null) return;
        
        Set<String> biomes = choose.getBiomeExclusions(world);
        int rx = source.getX() * 16 + 7;
        int rz = source.getZ() * 16 + 7;
        int ry = world.getHighestBlockYAt(rx, rz);
        if(biomes.contains(world.getBiome(rx, rz).toString())) return;
        
        choose.generateDungeon(world, random, source);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.populator;

import java.util.Random;
import java.util.Set;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import otd.Main;
import shadow_manager.DungeonWorldManager;
import otd.util.RandomCollection;
import otd.config.SimpleWorldConfig;
import otd.config.WorldConfig;

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
        int distance = swc.distance;
        
        Random mrand = new Random(world.getSeed() + world_name.hashCode()
		+ (2027 * (long)(source.getX() / distance)) 
		+ (1987 * (long)(source.getZ() / distance)));
        int xrand = mrand.nextInt();
        int zrand = mrand.nextInt();
        int xuse = ((source.getX() + xrand) % distance);
        int zuse = ((source.getZ() + zrand) % distance);
        
        if(xuse != 0 || zuse != 0) return;
        
        RandomCollection<IPopulator> generator = new RandomCollection<>(random);
        if(swc.roguelike.doNaturalSpawn) {
            generator.add(swc.roguelike_weight, new RoguelikePopulator());
        }
        if(swc.doomlike.doNaturalSpawn) {
            generator.add(swc.doomlike_weight, new DoomlikePopulator());
        }
        if(swc.battletower.doNaturalSpawn) {
            generator.add(swc.battle_tower_weight, new BattleTowerPopulator());
        }
        if(swc.smoofydungeon.doNaturalSpawn) {
            generator.add(swc.smoofy_weight, new SmoofyPopulator());
        }
        if(swc.draylar_battletower.doNaturalSpawn) {
            generator.add(swc.draylar_weight, new DraylarBattleTowerPopulator());
        }
        if(swc.ant_man_dungeon.doNaturalSpawn) {
            generator.add(swc.antman_weight, new AntManPopulator());
        }
        if(swc.aether_dungeon.doNaturalSpawn) {
            generator.add(swc.aether_weight, new AetherPopulator());
        }
        if(swc.lich_tower.doNaturalSpawn) {
            generator.add(swc.lich_weight, new LichTowerPopulator());
        }
        
        boolean r = swc.roguelike.doNaturalSpawn || swc.doomlike.doNaturalSpawn
                || swc.battletower.doNaturalSpawn || swc.smoofydungeon.doNaturalSpawn
                || swc.draylar_battletower.doNaturalSpawn || swc.ant_man_dungeon.doNaturalSpawn 
                || swc.aether_dungeon.doNaturalSpawn || swc.lich_tower.doNaturalSpawn;
        
        if(!r) return;
        
        IPopulator choose = generator.next();
        if(choose == null) return;
        
        Set<String> biomes = choose.getBiomeExclusions(world);
        int rx = source.getX() * 16 + 7;
        int rz = source.getZ() * 16 + 7;
        int ry = world.getHighestBlockYAt(rx, rz);
        if(biomes.contains(world.getBiome(rx, rz).toString())) return;
        
        choose.generateDungeon(world, random, source);
    }
}

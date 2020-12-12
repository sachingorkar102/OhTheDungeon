/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.generator;

import forge_sandbox.greymerk.roguelike.dungeon.settings.DungeonSettings;
import forge_sandbox.greymerk.roguelike.dungeon.settings.SettingsContainer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.bukkit.Chunk;
import org.bukkit.World;
import shadow_lib.async.AsyncRoguelikeDungeon;
import shadow_lib.async.AsyncWorldEditor;
import zhehe.util.AsyncLog;
import otd.util.config.SimpleWorldConfig;
import otd.util.config.WorldConfig;
import otd.world.ChunkList;

/**
 *
 * @author
 */
public class RoguelikeGenerator implements IGenerator {
    
    public final static List<DungeonSettings> dungeons;
    static {
        dungeons = new ArrayList<>();
        dungeons.add(SettingsContainer.mushroom);
        dungeons.add(SettingsContainer.desert);
        dungeons.add(SettingsContainer.grassland);
        dungeons.add(SettingsContainer.jungle);
        dungeons.add(SettingsContainer.swamp);
        dungeons.add(SettingsContainer.mountain);
        dungeons.add(SettingsContainer.forest);
        dungeons.add(SettingsContainer.mesa);
        dungeons.add(SettingsContainer.ice);
        dungeons.add(SettingsContainer.ruin);
        dungeons.add(SettingsContainer.rare);
    }
    
    
    @Override
    public Set<String> getBiomeExclusions(World world) {
        SimpleWorldConfig swc = WorldConfig.wc.dict.get(world.getName());
        return swc.roguelike.biomeExclusions;
    }
    @Override
    public boolean generateDungeon(World world, Random random, Chunk chunk) {
        AsyncWorldEditor editor = new AsyncWorldEditor(world);
        boolean res = AsyncRoguelikeDungeon.generateAsync(random, editor, 
                chunk.getX() * 16 + 4, chunk.getZ() * 16 + 4);
        if(res) AsyncLog.logMessage("[Roguelike Dungeon @ " + world.getName() + "] x=" + chunk.getX() * 16 + ", z=" + chunk.getZ() * 16);
        return res;
    }
    
    public boolean generateDungeonWithRandomTheme(World world, Random random, Chunk chunk) {
        AsyncWorldEditor editor = new AsyncWorldEditor(world);
        boolean res = AsyncRoguelikeDungeon.generateAsync(random, editor, 
                chunk.getX() * 16 + 4, chunk.getZ() * 16 + 4,
                ChunkList.chunk_set, dungeons.get(random.nextInt(dungeons.size())));
        if(res) AsyncLog.logMessage("[Roguelike Dungeon @ " + world.getName() + "] x=" + chunk.getX() * 16 + ", z=" + chunk.getZ() * 16);
        return res;
    }
}

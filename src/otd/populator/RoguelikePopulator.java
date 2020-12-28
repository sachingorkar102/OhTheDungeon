/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.populator;

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
import otd.util.AsyncLog;
import otd.config.SimpleWorldConfig;
import otd.config.SimpleWorldConfig.Roguelike.Themes;
import otd.config.WorldConfig;
import otd.world.ChunkList;

/**
 *
 * @author
 */
public class RoguelikePopulator implements IPopulator {
    
//    public final static List<DungeonSettings> dungeons;
//    static {
//        dungeons = new ArrayList<>();
//        dungeons.add(SettingsContainer.house);
//        dungeons.add(SettingsContainer.desert);
//        dungeons.add(SettingsContainer.bunker);
//        dungeons.add(SettingsContainer.jungle);
//        dungeons.add(SettingsContainer.swamp);
//        dungeons.add(SettingsContainer.mountain);
//        dungeons.add(SettingsContainer.forest);
//        dungeons.add(SettingsContainer.mesa);
//        dungeons.add(SettingsContainer.ice);
//        dungeons.add(SettingsContainer.ruin);
//        dungeons.add(SettingsContainer.rare);
//    }
    
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
        List<DungeonSettings> adungeons = new ArrayList<>();
        String world_name = world.getName();
        if(WorldConfig.wc.dict.containsKey(world_name)) {
            Themes theme = WorldConfig.wc.dict.get(world_name).roguelike.themes;
            if(theme.house) adungeons.add(SettingsContainer.house);
            if(theme.desert) adungeons.add(SettingsContainer.desert);
            if(theme.bunker) adungeons.add(SettingsContainer.bunker);
            if(theme.jungle) adungeons.add(SettingsContainer.jungle);
            if(theme.swamp) adungeons.add(SettingsContainer.swamp);
            if(theme.mountain) adungeons.add(SettingsContainer.mountain);
            if(theme.forest) adungeons.add(SettingsContainer.forest);
            if(theme.mesa) adungeons.add(SettingsContainer.mesa);
            if(theme.ice) adungeons.add(SettingsContainer.ice);
            if(theme.ruin) adungeons.add(SettingsContainer.ruin);
            if(theme.rare) adungeons.add(SettingsContainer.rare);
        } else {
            adungeons.add(SettingsContainer.house);
            adungeons.add(SettingsContainer.desert);
            adungeons.add(SettingsContainer.bunker);
            adungeons.add(SettingsContainer.jungle);
            adungeons.add(SettingsContainer.swamp);
            adungeons.add(SettingsContainer.mountain);
            adungeons.add(SettingsContainer.forest);
            adungeons.add(SettingsContainer.mesa);
            adungeons.add(SettingsContainer.ice);
            adungeons.add(SettingsContainer.ruin);
            adungeons.add(SettingsContainer.rare);
        }
        
        AsyncWorldEditor editor = new AsyncWorldEditor(world);
        boolean res = AsyncRoguelikeDungeon.generateAsync(random, editor, 
                chunk.getX() * 16 + 4, chunk.getZ() * 16 + 4,
                ChunkList.chunk_set, adungeons.get(random.nextInt(adungeons.size())));
        if(res) AsyncLog.logMessage("[Roguelike Dungeon @ " + world.getName() + "] x=" + chunk.getX() * 16 + ", z=" + chunk.getZ() * 16);
        return res;
    }
}

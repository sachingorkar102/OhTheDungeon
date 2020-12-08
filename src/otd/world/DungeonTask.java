/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.world;

import java.util.HashMap;
import java.util.Map;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import otd.Main;
import otd.generator.AetherGenerator;
import otd.generator.AntManGenerator;
import otd.generator.BattleTowerGenerator;
import otd.generator.DoomlikeGenerator;
import otd.generator.DraylarBattleTowerGenerator;
import otd.generator.IGenerator;
import otd.generator.LichTowerGenerator;
import otd.generator.RoguelikeGenerator;
import otd.generator.SmoofyDungeonGenerator;
import otd.util.config.WorldConfig;
import otd.world.task.DungeonChunkTask;
import otd.world.task.DungeonPlaceTask;
import otd.world.task.DungeonWorldTask;
import zhehe.util.I18n;

/**
 *
 * @author shadow
 */
public class DungeonTask {
    private static int step = 0;
    private static int next = 0;
    
    private static boolean generating = false;
    private static boolean breaking = false;
    
    public static boolean isGenerating() {
        return generating;
    }
    
    public static void setBreak() {
        breaking = true;
    }
    
    public static int getTaskCount() {
        return ChunkList.task_pool.size();
    }
    
    private final static Map<DungeonType, IGenerator> dict;
    static {
        dict = new HashMap<>();
        dict.put(DungeonType.Aether, new AetherGenerator());
        dict.put(DungeonType.AntMan, new AntManGenerator());
        dict.put(DungeonType.BattleTower, new BattleTowerGenerator());
        dict.put(DungeonType.Doomlike, new DoomlikeGenerator());
        dict.put(DungeonType.Draylar, new DraylarBattleTowerGenerator());
        dict.put(DungeonType.Lich, new LichTowerGenerator());
        dict.put(DungeonType.Roguelike, new RoguelikeGenerator());
        dict.put(DungeonType.DungeonMaze, new SmoofyDungeonGenerator());
    }
    
    public static void globalMessage() {
        Bukkit.broadcastMessage(ChatColor.BLUE + I18n.instance.Dungeon_Plot_Finish);
    }
    
    public static void start() {
        step = 0;
        next = 0;
        generating = true;
        breaking = false;
        
        Bukkit.getScheduler().runTaskTimer(Main.instance, new BukkitRunnable() {
            @Override
            public void run() {
                if(step == next) {
                    if(DungeonWorld.world == null) return;
                    if(ChunkList.task_pool.size() <= next) {
                        this.cancel();
                        generating = false;
                        WorldConfig.wc.dungeon_world.finished = true;
                        WorldConfig.save();
                        return;
                    }
                    DungeonWorldTask dwt = ChunkList.task_pool.get(next);
                    
                    if(dwt instanceof DungeonChunkTask) {
                        int[] chunkPos = dwt.getChunkPos();
                        DungeonWorld.world.getChunkAt(chunkPos[0], chunkPos[1]);
                        step += dwt.getDelay();
                        next++;
                    }
                    if(dwt instanceof DungeonPlaceTask) {
                        DungeonPlaceTask dungeon = (DungeonPlaceTask) dwt;
                        int[] chunkPos = dungeon.getChunkPos();
                        int x = chunkPos[0] * 16 + 8;
                        int z = chunkPos[1] * 16 + 8;
                        
                        step += dungeon.getDelay();
                        next++;
                        
                        if(breaking) {
                            this.cancel();
                            generating = false;
                            WorldConfig.wc.dungeon_world.finished = true;
                            WorldConfig.save();
                        }
                    }
                }
            }
        }, 1, 1);
    }
}

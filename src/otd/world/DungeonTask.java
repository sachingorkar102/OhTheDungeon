/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.world;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
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
    private static int pointer = 0;
    
    private static boolean generating = false;
    private static boolean breaking = false;
    
    private final static Random random = new Random();
    
    public static boolean isGenerating() {
        return generating;
    }
    
    public static void setBreak() {
        breaking = true;
    }
    
    public static int getTaskCount() {
        return ChunkList.task_pool.size();
    }
    
    
    private final static AetherGenerator aether;
    private final static AntManGenerator antman;
    private final static BattleTowerGenerator battle_tower;
    private final static DoomlikeGenerator doomlike;
    private final static DraylarBattleTowerGenerator draylar;
    private final static LichTowerGenerator lich_tower;
    private final static RoguelikeGenerator roguelike;
    private final static SmoofyDungeonGenerator smoofy;
    private final static Map<DungeonType, IGenerator> dict;
    static {
        dict = new HashMap<>();
        aether = new AetherGenerator();
        antman = new AntManGenerator();
        battle_tower = new BattleTowerGenerator();
        doomlike = new DoomlikeGenerator();
        draylar = new DraylarBattleTowerGenerator();
        lich_tower = new LichTowerGenerator();
        roguelike = new RoguelikeGenerator();
        smoofy = new SmoofyDungeonGenerator();
        
        dict.put(DungeonType.Aether, aether);
        dict.put(DungeonType.AntMan, antman);
        dict.put(DungeonType.BattleTower, battle_tower);
        dict.put(DungeonType.Doomlike, doomlike);
        dict.put(DungeonType.Draylar, draylar);
        dict.put(DungeonType.Lich, lich_tower);
        dict.put(DungeonType.Roguelike, roguelike);
        dict.put(DungeonType.DungeonMaze, smoofy);
    }
    
    public static void placeAether(int x, int z) {
        aether.generateDungeon(DungeonWorld.world, random, DungeonWorld.world.getChunkAt(x, z));
    }
    public static void placeAntMan(int x, int z) {
        antman.generateDungeon(DungeonWorld.world, random, DungeonWorld.world.getChunkAt(x, z));
    }
    public static void placeBattleTower(int x, int z) {
        battle_tower.generateDungeon(DungeonWorld.world, random, DungeonWorld.world.getChunkAt(x, z));
    }
    public static void placeDoomLike(int x, int z) {
        doomlike.generateDungeon(DungeonWorld.world, random, DungeonWorld.world.getChunkAt(x, z));
    }
    public static void placeDraylar(int x, int z) {
        draylar.generateDungeon(DungeonWorld.world, random, DungeonWorld.world.getChunkAt(x, z));
    }
    public static void placeLichTower(int x, int z) {
        lich_tower.generateDungeon(DungeonWorld.world, random, DungeonWorld.world.getChunkAt(x, z));
    }
    public static void placeSmoofy(int x, int z) {
        smoofy.generateDungeon(DungeonWorld.world, random, DungeonWorld.world.getChunkAt(x, z));
    }
    public static void placeRoguelike(int x, int z) {
        roguelike.generateDungeonWithRandomTheme(DungeonWorld.world, random, DungeonWorld.world.getChunkAt(x, z));
    }
    
    public static void globalMessage() {
        Bukkit.broadcastMessage(ChatColor.BLUE + I18n.instance.Dungeon_Plot_Finish);
    }
    
    public static void start() {
        step = 0;
        next = 0;
        pointer = 0;
        generating = true;
        breaking = false;
        
        BukkitRunnable r = new BukkitRunnable() {
            @Override
            public void run() {
                if(step == next) {
                    if(DungeonWorld.world == null) {
                        this.cancel();
                        return;
                    }
                    if(ChunkList.task_pool.size() <= pointer) {
                        this.cancel();
                        generating = false;
                        WorldConfig.wc.dungeon_world.finished = true;
                        WorldConfig.save();
                        globalMessage();
                        return;
                    }
                    DungeonWorldTask dwt = ChunkList.task_pool.get(pointer);
                    pointer++;
                    
                    if(dwt instanceof DungeonChunkTask) {
                        int[] chunkPos = dwt.getChunkPos();
                        DungeonWorld.world.getChunkAt(chunkPos[0], chunkPos[1]);
                        step += dwt.getDelay();
                        next++;
                    }
                    if(dwt instanceof DungeonPlaceTask) {
                        DungeonPlaceTask dungeon = (DungeonPlaceTask) dwt;
                        int[] chunkPos = dungeon.getChunkPos();
                        
                        if(dungeon.dungeon == DungeonType.Aether) {
                            placeAether(chunkPos[0], chunkPos[1]);
                        } else if(dungeon.dungeon == DungeonType.AntMan) {
                            placeAntMan(chunkPos[0], chunkPos[1]);
                        } else if(dungeon.dungeon == DungeonType.BattleTower) {
                            placeBattleTower(chunkPos[0], chunkPos[1]);
                        } else if(dungeon.dungeon == DungeonType.Doomlike) {
                            placeDoomLike(chunkPos[0], chunkPos[1]);
                        } else if(dungeon.dungeon == DungeonType.Draylar) {
                            placeDraylar(chunkPos[0], chunkPos[1]);
                        } else if(dungeon.dungeon == DungeonType.DungeonMaze) {
                            placeSmoofy(chunkPos[0], chunkPos[1]);
                        } else if(dungeon.dungeon == DungeonType.Lich) {
                            placeLichTower(chunkPos[0], chunkPos[1]);
                        } else {
                            placeRoguelike(chunkPos[0], chunkPos[1]);
                        }
                        
                        step += dungeon.getDelay();
                        next++;
                        
                        if(breaking) {
                            this.cancel();
                            generating = false;
                            WorldConfig.wc.dungeon_world.finished = true;
                            WorldConfig.save();
                        }
                        
                        Bukkit.getLogger().log(Level.INFO, "Dungeon {0}, chunkx={1}, chunkz={2}", new Object[]{dungeon.dungeon.toString(), chunkPos[0], chunkPos[1]});

                    }
                } else if(next < step) {
                    next++;
                } else {
                    this.cancel();
                    generating = false;
                    WorldConfig.wc.dungeon_world.finished = true;
                    WorldConfig.save();
                }
            }
        };
        r.runTaskTimer(Main.instance, 1, 1);
    }
}

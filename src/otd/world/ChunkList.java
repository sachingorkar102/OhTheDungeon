/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import otd.util.config.DungeonWorldConfig;
import otd.world.task.DungeonChunkTask;
import otd.world.task.DungeonPlaceTask;
import otd.world.task.DungeonWorldTask;
import zhehe.util.RandomCollection;

/**
 *
 * @author shadow
 */
public class ChunkList {
    public static Map<int[], DungeonType> chunks = new HashMap<>();
    public static Map<int[], List<int[]>> groups = new HashMap<>();
    
    public final static Map<DungeonType, Integer> dict;
    static {
        dict = new HashMap<>();
        dict.put(DungeonType.BattleTower, 2);
        dict.put(DungeonType.Roguelike, 6);
        dict.put(DungeonType.Doomlike, 4);
        dict.put(DungeonType.DungeonMaze, 2);
        dict.put(DungeonType.Draylar, 2);
        dict.put(DungeonType.Aether, 2);
        dict.put(DungeonType.Lich, 2);
        dict.put(DungeonType.AntMan, 4);
    }
    public static List<DungeonWorldTask> task_pool = new ArrayList<>();
    
    public static void initChunksMap(DungeonWorldConfig dwc) {
        chunks.clear();
        groups.clear();
        task_pool.clear();
        RandomCollection<DungeonType> r = new RandomCollection();
        if(dwc.dungeon_world.roguelike_weight > 0) r.add(dwc.dungeon_world.roguelike_weight, DungeonType.Roguelike);
        if(dwc.dungeon_world.doomlike_weight > 0) r.add(dwc.dungeon_world.doomlike_weight, DungeonType.Doomlike);
        if(dwc.dungeon_world.battle_tower_weight > 0) r.add(dwc.dungeon_world.battle_tower_weight, DungeonType.BattleTower);
        if(dwc.dungeon_world.smoofy_weight > 0) r.add(dwc.dungeon_world.smoofy_weight, DungeonType.DungeonMaze);
        if(dwc.dungeon_world.draylar_weight > 0) r.add(dwc.dungeon_world.draylar_weight, DungeonType.Draylar);
        if(dwc.dungeon_world.antman_weight > 0) r.add(dwc.dungeon_world.antman_weight, DungeonType.AntMan);
        if(dwc.dungeon_world.aether_weight > 0) r.add(dwc.dungeon_world.aether_weight, DungeonType.Aether);
        if(dwc.dungeon_world.lich_weight > 0) r.add(dwc.dungeon_world.lich_weight, DungeonType.Lich);
        
        if(r.isEmpty()) return;
        zonePos = new ZonePos();
        int count = dwc.dungeon_count;
        
        for(int i = 0; i < count; i++) {
            int[] next = getNextZone();
            DungeonType dungeon = r.next();
            int d = dict.get(dungeon);
            List<int[]> sub = new ArrayList<>();
            for(int x = next[0] - d; x <= next[0] + d; x++) {
                for(int z = next[1] - d; z <= next[1] + d; z++) {
                    chunks.put(new int[] {x, z}, dungeon);
                    sub.add(new int[] {x, z});
                    
                    task_pool.add(new DungeonChunkTask(x, z));
                }
            }
            groups.put(next, sub);
            task_pool.add(new DungeonPlaceTask(next[0], next[1], dungeon));
        }
    }
    
    public static class ZonePos {
        int cx, cy;
        int r;
        public ZonePos() {
            cx = 48;
            cy = 48;
            r = 48;
        }
    }
    
    public static void main(String[] args) {
        zonePos = new ZonePos();
        for(int i = 0; i < 60; i++) System.out.println(Arrays.toString(getNextZone()));
    }
    public static ZonePos zonePos;
    public static int[] getNextZone() {
        int x = zonePos.cx;
        int y = zonePos.cy;
        int r = zonePos.r;
        if(x > 0 && x == y) {
            x = x - 32;
            zonePos.cx = x;
            zonePos.cy = y;
            zonePos.r = r;
            return new int[] {x,y};
        }
        if(x < 0 && (-x) == y) {
            y = y - 32;
            zonePos.cx = x;
            zonePos.cy = y;
            zonePos.r = r;
            return new int[] {x,y};
        }
        if(x < 0 && x == y) {
            x = x + 32;
            zonePos.cx = x;
            zonePos.cy = y;
            zonePos.r = r;
            return new int[] {x,y};
        }
        if(x > 0 && (-y) == x) {
            y = y + 32;
            zonePos.cx = x;
            zonePos.cy = y;
            zonePos.r = r;
            return new int[] {x,y};
        }
        if(y == r) {
            x = x - 32;
            zonePos.cx = x;
            zonePos.cy = y;
            zonePos.r = r;
            return new int[] {x,y};
        }
        if(x == -r) {
            y = y - 32;
            zonePos.cx = x;
            zonePos.cy = y;
            zonePos.r = r;
            return new int[] {x,y};
        }
        if(y == -r) {
            x = x + 32;
            zonePos.cx = x;
            zonePos.cy = y;
            zonePos.r = r;
            return new int[] {x,y};
        }
        if(x == r) {
            y = y + 32;
            if(y == r) {
                x = x + 32;
                y = y + 32;
                r = r + 32;
            }
            zonePos.cx = x;
            zonePos.cy = y;
            zonePos.r = r;
            return new int[] {x,y};
        }
        return null;
    }
}

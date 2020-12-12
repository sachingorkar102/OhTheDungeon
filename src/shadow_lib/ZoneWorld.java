/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shadow_lib;

import forge_sandbox.greymerk.roguelike.worldgen.Coord;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import shadow_lib.async.later.roguelike.Later;

/**
 *
 * @author
 */
public class ZoneWorld {
    
    public final static Set<Material> SPECIAL_BLOCKS = new HashSet<>();
    public List<Later> later_task = new ArrayList<>();
    public Material default_state = Material.STONE;
    
    public static void registerSpecialBlock(Material material) {
        SPECIAL_BLOCKS.add(material);
    }
    
    public static class CriticalNode {
        public int[] pos;
        public BlockData bd = null;
        public Material material = null;
    }
    
    public void addLater(Later later) {
        later_task.add(later);
    }
    
    public List<Later> getCriticalLater(int chunkX, int chunkZ) {
        int minX = chunkX * 16;
        int maxX = chunkX * 16 + 15;
        int minZ = chunkZ * 16;
        int maxZ = chunkZ * 16 + 15;
        
        List<Later> res = new ArrayList<>();
        
        for(Later later : later_task) {
            Coord pos = later.getPos();
            if(pos == null) continue;
            int x = pos.getX();
            int z = pos.getZ();
            
            if(
                    x >= minX &&
                    x <= maxX &&
                    z >= minZ &&
                    z <= maxZ
                    ) {
                res.add(later);
            }
        }
        
        return res;
    }
    
    public List<CriticalNode> getCriticalBlock(int chunkX, int chunkZ) {
        int minX = chunkX * 16;
        int maxX = chunkX * 16 + 15;
        int minZ = chunkZ * 16;
        int maxZ = chunkZ * 16 + 15;
        
        List<CriticalNode> res = new ArrayList<>();
        for(Map.Entry<String, BlockBase> entry : map.entrySet()) {
            if(true) {
                int[] pos = keyToXYZ(entry.getKey());
                if(pos[0] >= minX && pos[0] <= maxX && pos[2] >= minZ && pos[2] <= maxZ) {
                    CriticalNode cn = new CriticalNode();
                    cn.pos = pos;
                    cn.pos[0] = cn.pos[0] % 16;
                    if(cn.pos[0] < 0) cn.pos[0] = cn.pos[0] + 16;
                    cn.pos[1] = cn.pos[1] % 256;
                    cn.pos[2] = cn.pos[2] % 16;
                    if(cn.pos[2] < 0) cn.pos[2] = cn.pos[2] + 16;
                    if(entry.getValue().data != null) cn.bd = entry.getValue().data;
                    else cn.material = entry.getValue().material;
//                    if(cn.material == Material.AIR) continue;
                    res.add(cn);
                }
            }
        }
        
        return res;
    }
    
    private static class BlockBase {
        public BlockData data = null;
        public Material material = null;
        
        public Material getType() {
            if(data == null) return material;
            return data.getMaterial();
        }
    }
    
    public final static String WORLD_NAME = "otd_zone_world";
    private final Map<String, BlockBase> map = new HashMap<>();
    public ZoneWorld() {
        
    }
    
    public void commitAll(World w) {
        int count = 0;
        for(Map.Entry<String, BlockBase> entry : map.entrySet()) {
            String key = entry.getKey();
            int[] pos = keyToXYZ(key);
            BlockBase base = entry.getValue();
            if(base.data != null) {
                w.getBlockAt(pos[0], pos[1], pos[2]).setBlockData(base.data, false);
            } else {
                w.getBlockAt(pos[0], pos[1], pos[2]).setType(base.material, false);
            }
//            if(base.getType() != Material.AIR) {
//                count++;
//                if(count < 100) Bukkit.getLogger().log(Level.INFO, key + "," + base.getType().toString());
//            }
        }
    }
    
    private static int[] keyToXYZ(String key) {
        String[] keys = key.split(",");
        int x = Integer.parseInt(keys[0]);
        int y = Integer.parseInt(keys[1]);
        int z = Integer.parseInt(keys[2]);
        return new int[] { x,y,z };
    }
    
    private static String xyzToKey(int x, int y, int z) {
        return x+","+y+","+z;
    }
    
    public Material getType(int x, int y, int z) {
        String key = xyzToKey(x, y, z);
        if(!map.containsKey(key)) return default_state;
        
        BlockBase bb = map.get(key);
        if(bb.data != null) return bb.data.getMaterial();
        return bb.material;
    }
    
    public BlockData getData(int x, int y, int z) {
        String key = xyzToKey(x, y, z);
        if(!map.containsKey(key)) return Bukkit.createBlockData(default_state);
        
        BlockBase bb = map.get(key);
        if(bb.data != null) return bb.data;
        return Bukkit.createBlockData(bb.material);
    }
    
    public void setType(int x, int y, int z, Material material, boolean update) {
        String key = xyzToKey(x, y, z);
        if(!map.containsKey(key)) map.put(key, new BlockBase());
        BlockBase bb = map.get(key);
        
        bb.data = null;
        bb.material = material;
    }
    
    public void setBlockData(int x, int y, int z, BlockData bd, boolean update) {
        String key = xyzToKey(x, y, z);
        if(!map.containsKey(key)) map.put(key, new BlockBase());
        BlockBase bb = map.get(key);
        
        bb.data = bd.clone();
        bb.material = null;
    }
    
    public Set<int[]> getCriticalChunks() {
        return getCriticalChunks(null);
    }
    
    public Set<int[]> getCriticalChunks(Set<String> except) {
        Set<int[]> set = new HashSet<>();
        Set<String> dummy = new HashSet<>();
        for(Map.Entry<String, BlockBase> entry : map.entrySet()) {
            int[] pos = keyToXYZ(entry.getKey());
            double x = pos[0], z = pos[2];
            
            x = Math.floor(x / 16);
            z = Math.floor(z / 16);
            
            int[] tmp = {(int) x, (int) z};
            String key = tmp[0] + "," + tmp[1];
            if(!dummy.contains(key)) {
                if(except == null || (except != null && except.contains(key))) {
                    set.add(tmp);
                    dummy.add(key);
                }
            }
        }
        return set;
    }
    
    public Set<int[]> getLaterChunks() {
        Set<int[]> set = new HashSet<>();
        Set<String> dummy = new HashSet<>();
//        List<Later> res = new ArrayList<>();
        
        for(Later later : later_task) {
            Coord pos = later.getPos();
            int x = pos.getX();
            int z = pos.getZ();
            
            x = (int) Math.floor(x / 16.0);
            z = (int) Math.floor(z / 16.0);
            
            int[] tmp = {x, z};
            String key = tmp[0] + "," + tmp[1];
            if(!dummy.contains(key)) {
                set.add(tmp);
                dummy.add(key);
            }
        }
        return set;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.util.worldgen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

/**
 *
 * @author
 */
public class HalfAsyncWorld {
    public World world;
    private final Map<List<Integer>, HalfAsyncBlock> dict;
    
    private class HalfAsyncBlock {
        Material material = null;
        BlockData bd = null;
        boolean change = false;
        boolean phy = true;
    }
    
    private void flush(int x, int y, int z, HalfAsyncBlock b) {
        try {
            if(b.change) {
                if(b.bd != null) world.getBlockAt(x, y, z).setBlockData(b.bd, b.phy);
                else world.getBlockAt(x, y, z).setType(b.material, true);
            }
        } catch(Exception ex) {
            
        }
    }
    
    public Block getBlock(int x, int y, int z) {
        List<Integer> key = new ArrayList<>();
        key.add(x);
        key.add(y);
        key.add(z);
        
        if(dict.containsKey(key)) {
            HalfAsyncBlock b = dict.get(key);
            flush(x, y, z, b);
            dict.remove(key);
        }
        
        return world.getBlockAt(x, y, z);
    }
    
    public HalfAsyncWorld(World world) {
        this.world = world;
        this.dict = new HashMap<>();
    }
    
    public BlockData getBlockData(int x, int y, int z) {
        List<Integer> key = new ArrayList<>();
        key.add(x);
        key.add(y);
        key.add(z);
        
        if(dict.containsKey(key)) {
            HalfAsyncBlock b = dict.get(key);
            if(b.bd != null) return b.bd;
            b.bd = world.getBlockAt(x, y, z).getBlockData();
            return b.bd;
        } else {
            HalfAsyncBlock b = new HalfAsyncBlock();
            b.bd = world.getBlockAt(x, y, z).getBlockData();
            b.change = false;
            dict.put(key, b);
            return b.bd;
        }
    }
    
    public Material getType(int x, int y, int z) {
        List<Integer> key = new ArrayList<>();
        key.add(x);
        key.add(y);
        key.add(z);
        
        if(dict.containsKey(key)) {
            HalfAsyncBlock b = dict.get(key);
            if(b.bd == null) return b.material;
            return b.bd.getMaterial();
        } else {
//            return null;
            Material material = world.getBlockAt(x, y, z).getType();
            HalfAsyncBlock b = new HalfAsyncBlock();
            b.material = material;
            b.change = false;
            dict.put(key, b);
            return material;
        }
    }
    
    public void setBlockData(int x, int y, int z, BlockData bd, boolean phy) {
//        Material material = bd.getMaterial();
//        if(material == Material.IRON_BARS || material == Material.REDSTONE_WIRE
//            || material == Material.WATER || material == Material.LAVA
//            || material == Material.OAK_FENCE || material == Material.SPRUCE_FENCE
//            || material == Material.JUNGLE_FENCE || material == Material.BIRCH_FENCE
//            || material == Material.DARK_OAK_FENCE || material == Material.ACACIA_FENCE
//            || material == Material.NETHER_BRICK_FENCE
//        ) {
//            setType(x,y,z,material,true);
//            return;
//        }
        
        
        List<Integer> key = new ArrayList<>();
        key.add(x);
        key.add(y);
        key.add(z);
        
        if(dict.containsKey(key)) {
            HalfAsyncBlock b = dict.get(key);
            b.bd = bd.clone();
            b.phy = phy;
            b.change = true;
        } else {
            HalfAsyncBlock b = new HalfAsyncBlock();
            b.bd = bd.clone();
            b.phy = phy;
            b.change = true;
            dict.put(key, b);
        }
    }
    
    public boolean commit(int count) {
        boolean res = true;
        for (Iterator<Map.Entry<List<Integer>, HalfAsyncBlock>> it = dict.entrySet().iterator(); it.hasNext() && count > 0;){
            res = false;
            Map.Entry<List<Integer>, HalfAsyncBlock> item = it.next();
            count--;
            List<Integer> key = item.getKey();
            HalfAsyncBlock b = item.getValue();
            if(b.change) {
                flush(key.get(0), key.get(1), key.get(2), b);
            }
            it.remove();
        }
        return res;
    }
    
    public void setType(int x, int y, int z, Material material, boolean phy) {
        List<Integer> key = new ArrayList<>();
        key.add(x);
        key.add(y);
        key.add(z);
        
        if(dict.containsKey(key)) {
            HalfAsyncBlock b = dict.get(key);
            b.material = material;
            b.bd = null;
            b.phy = phy;
            b.change = true;
        } else {
            HalfAsyncBlock b = new HalfAsyncBlock();
            b.material = material;
            b.phy = phy;
            b.change = true;
            dict.put(key, b);
        }
    }
}

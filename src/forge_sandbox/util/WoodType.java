/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forge_sandbox.util;

import org.bukkit.Material;

/**
 *
 * @author zhehe
 */
public enum WoodType {
    OAK, BIRCH, SPRUCE, DARK_OAK, JUNGLE, ACACIA;
    
    public static Material getSlab(WoodType type) {
        if(type == OAK) return Material.OAK_SLAB;
        if(type == BIRCH) return Material.BIRCH_SLAB;
        if(type == SPRUCE) return Material.SPRUCE_SLAB;
        if(type == DARK_OAK) return Material.DARK_OAK_SLAB;
        if(type == JUNGLE) return Material.JUNGLE_SLAB;
        if(type == ACACIA) return Material.ACACIA_SLAB;
        return Material.AIR;
    }
    
    public static Material getPlanks(WoodType type) {
        if(type == OAK) return Material.OAK_PLANKS;
        if(type == BIRCH) return Material.BIRCH_PLANKS;
        if(type == SPRUCE) return Material.SPRUCE_PLANKS;
        if(type == DARK_OAK) return Material.DARK_OAK_PLANKS;
        if(type == JUNGLE) return Material.JUNGLE_PLANKS;
        if(type == ACACIA) return Material.ACACIA_PLANKS;
        return Material.AIR;
    }
}

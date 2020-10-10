/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.battletower;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author
 */
public class TreasureList {
    public static class ItemStackNode {
        ItemStack is;
        double chance;
        int min;
        int max;
        public ItemStackNode(ItemStack is, double chance, int min, int max) {
            this.is = is;
            this.chance = chance;
            this.min = min;
            this.max = max;
        }
    };
    
    public final static ItemStackNode TOP[] = {
        new ItemStackNode(new ItemStack(Material.EGG), 0.6, 16, 16),
        new ItemStackNode(new ItemStack(Material.SNOWBALL), 0.6, 16, 16),
        new ItemStackNode(new ItemStack(Material.STONE), 0.6, 32, 64),
        new ItemStackNode(new ItemStack(Material.STONE_AXE), 0.6, 1, 1),
        new ItemStackNode(new ItemStack(Material.STONE_HOE), 0.6, 1, 1),
        new ItemStackNode(new ItemStack(Material.STONE_SHOVEL), 0.6, 1, 1),
        new ItemStackNode(new ItemStack(Material.STONE_PICKAXE), 0.6, 1, 1),
        new ItemStackNode(new ItemStack(Material.STONE_SWORD), 0.6, 1, 1),

        new ItemStackNode(new ItemStack(Material.ACACIA_LOG), 0.6, 4, 17),
        new ItemStackNode(new ItemStack(Material.SPRUCE_LOG), 0.6, 4, 17),
        new ItemStackNode(new ItemStack(Material.OAK_LOG), 0.6, 4, 17),
        new ItemStackNode(new ItemStack(Material.JUNGLE_LOG), 0.6, 4, 17),
        new ItemStackNode(new ItemStack(Material.DARK_OAK_LOG), 0.6, 4, 17),
        new ItemStackNode(new ItemStack(Material.BIRCH_LOG), 0.6, 4, 17),
        new ItemStackNode(new ItemStack(Material.CHARCOAL), 0.6, 17, 24),
        new ItemStackNode(new ItemStack(Material.COBWEB), 0.6, 2, 12),
        new ItemStackNode(new ItemStack(Material.BONE_MEAL), 0.6, 19, 26),
        new ItemStackNode(new ItemStack(Material.SPONGE), 0.6, 2, 8),
        new ItemStackNode(new ItemStack(Material.FIRE_CHARGE), 0.6, 4, 8),
    };
    
    public final static Material treasure_block[] = {
        Material.IRON_BLOCK, Material.GOLD_BLOCK,
        Material.EMERALD_BLOCK, Material.LAPIS_BLOCK,
        Material.COAL_BLOCK, Material.REDSTONE_BLOCK,
    };
    public final static Material top_treasure_block[] = {
        Material.DIAMOND_BLOCK,
    };
}

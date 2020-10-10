/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zhehe.util.config;

import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author
 */
public class LootNode {
    public String item;
    public double chance;
    public int min = 1;
    public int max = 1;
    public String itemStackString;
    private transient ItemStack itemstack;
    
    public static String itemStackToString(ItemStack is) {
        YamlConfiguration yml = new YamlConfiguration();
        yml.set("item", is);
        return yml.saveToString();
    }
    
    public static ItemStack stringToItemStack(String str) {
        YamlConfiguration yml = new YamlConfiguration();
        ItemStack item;
        try {
            yml.loadFromString(str);
            item = yml.getItemStack("item");
        } catch (InvalidConfigurationException ex) {
            item = new ItemStack(Material.AIR, 1);
        }
        return item;
    }
    
    public LootNode(ItemStack item, double chance, int max, int min) {
        this.chance = chance;
        this.itemStackString = itemStackToString(item);
        this.itemstack = item.clone();
        this.max = max;
        this.min = min;
    }
    
    public ItemStack getItem() {
        if(itemstack != null) return itemstack;
        itemstack = stringToItemStack(itemStackString);
        return itemstack;
    }
}

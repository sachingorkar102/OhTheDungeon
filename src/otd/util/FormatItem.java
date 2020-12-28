/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/*
 * You know, it's pretty interesting.
 * The most recent spigot update (1.13+) has changed all of the Material values.
 * They changed it so that it matches the vanilla display name of the items!
 * This makes formatting the item names very easy.
 *
 * In previous versions, they did not match the display name of the item...
 * It made it much more annoying to have to do that before.
 * You would *have* to use Locale.
 * However now the only benefit to using Locale is to allocate for different languages.
 *
 * I plan on adding to this in the future to support multiple languages.
 */
public class FormatItem {
    protected ItemStack item;
 
    /**
    * Creates instance of FormatItem from an ItemStack
    * @param item ItemStack you want to get the format of.
    */
    public FormatItem(ItemStack item) {
        this.item = item;
    }
 
    /**
    * Creates instance of FormatItem from a Material
    * @param material Material you want to get the format of.
    */
    public FormatItem(Material material) {
        this(new ItemStack(material));
    }
 
    /**
    * Creates instance of FormatItem from a display name.
    * @param displayName String you want to get the format of.
    */
    public FormatItem(String displayName) {
        try {
            Material material = Material.valueOf(displayName.toUpperCase().replace(" ", "_"));
            this.item = new ItemStack(material);
        } catch (Exception ex) {
            this.item = new ItemStack(Material.AIR);
        }
    }
 
    /**
    * Returns the ItemStack value of the formatter.
    * @return ItemStack
    */
    public ItemStack getItem() { return this.item; }
 
    /**
    * Get the format of the item's name.
    * @param Player to use for the language format
    * @return Format of the FormatItem instance
    */
    private String format() {
        if(this.item == null)
            return null;
     
        //Make sure the item doesn't have a custom display name set
        final ItemMeta im = this.item.getItemMeta();
        if(im != null && im.getDisplayName() != null && im.getDisplayName() != "")
            return im.getDisplayName();
     
        //Grab the material we're using for the name
        final Material material = this.item.getType();
     
        //Format to proper label: "Stone Bricks" instead of STONE_BRICKS
        StringBuilder builder = new StringBuilder();
        for(String word : material.toString().split("_"))
            builder.append(word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase() + " ");
     
        //Trim the string, and also return it
        return builder.toString().trim();
    }
 
    /**
    * Grabs the display name of the ItemFormat instance.
    * @return String of the display name.
    */
    public String getName() {
        String format = this.format();
        if(format != null) return format;
        return "";
    }
 
    /**
    * Grabs the unlocalized name of the ItemFormat instance.
    * @return String
    */
    public String getUnlocalizedName() {
        if(this.item == null || this.item.getType() == null)
            return null;
     
        //Grab the prefix
        String prefix = "";
        if(this.item.getType().isBlock())
            prefix = "block";
        if(this.item.getType().isItem())
            prefix = "item";
     
        //Generate the unlocalized name
        return prefix + ".minecraft." + this.item.getType().toString().toLowerCase();
    }
}


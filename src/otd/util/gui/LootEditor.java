/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.util.gui;

import forge_sandbox.greymerk.roguelike.treasure.loot.Enchant;
import forge_sandbox.greymerk.roguelike.treasure.loot.Loot;
import forge_sandbox.greymerk.roguelike.util.TextFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import zhehe.util.I18n;

/**
 *
 * @author
 */
public class LootEditor extends Content {
    private final static int SLOT = 27;
    
    public static WorldManager instance = new WorldManager();
    
    public LootEditor() {
        super("Loot Editor for %s", SLOT);
        worlds = new ArrayList<>();
        offset = 0;
    }
    
    public final static Material NORMAL = Material.GRASS_BLOCK;
    public final static Material NETHER = Material.NETHER_BRICKS;
    public final static Material ENDER = Material.END_PORTAL_FRAME;
    
    private List<World> worlds;
    private int offset;
    
    private final static String WORLD_KEYWORD = "World";
        
    @Override
    public void init() {
			ItemStack item = new ItemStack(Material.GOLDEN_HELMET);
			item = Loot.setItemNameNew(item, I18n.instance.NEBRISCROWN.Name, null);
			item = Loot.setItemLoreNew(item, I18n.instance.NEBRISCROWN.Lore, TextFormat.DARKGREEN);
			item.addUnsafeEnchantment(Enchant.getEnchant(Enchant.PROTECTION), 4);
			item.addUnsafeEnchantment(Enchant.getEnchant(Enchant.UNBREAKING), 3);
                        
                        ItemMeta im = item.getItemMeta();
                        List<String> lores = im.getLore();
                        lores.add(0, "Click to Edit or Disable");
                        im.setLore(lores);
                        item.setItemMeta(im);
                        addItem(0, item);
                        Random r = new Random();
                        for(int i = 1; i < 27; i++) {
                            Material m = Material.POTION;
                            int k = r.nextInt(9);
                            if(k == 0) m = Material.WOODEN_HOE;
                            if(k == 1) m = Material.DIAMOND_BOOTS;
                            if(k == 2) m = Material.STICK;
                            if(k == 3) m = Material.GOLDEN_SWORD;
                            if(k == 4) m = Material.GOLDEN_APPLE;
                            if(k == 5) m = Material.LEATHER_BOOTS;
                            addItem(i, new ItemStack(m));
                        }
    }
}

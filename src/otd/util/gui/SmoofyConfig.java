/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.util.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import zhehe.util.I18n;
import otd.util.config.LootNode;
import otd.util.config.SimpleWorldConfig;
import otd.util.config.WorldConfig;

/**
 *
 * @author
 */
public class SmoofyConfig extends Content {
    private final static int SLOT = 18;
    
    public static SmoofyConfig instance = new SmoofyConfig();
    public final String world;
    private final Content parent;
    
    private SmoofyConfig() {
        super(I18n.instance.Smoofy_Config, SLOT);
        this.world = null;
        this.parent = null;
    }
    
    public SmoofyConfig(String world, Content parent) {
        super(I18n.instance.Smoofy_Config, SLOT);
        this.world = world;
        this.parent = parent;
    }
    
    private final static Material DISABLE = Material.MUSIC_DISC_BLOCKS;
    private final static Material ENABLE = Material.MUSIC_DISC_CAT;
    
    @EventHandler
    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof SmoofyConfig)) {
            return;
        }
        if (e.getClick().equals(ClickType.NUMBER_KEY)){
            kcancel(e);
            return;
        }

        kcancel(e);
        int slot = e.getRawSlot();
        Player p = (Player) e.getWhoClicked();
        SmoofyConfig holder = (SmoofyConfig) e.getInventory().getHolder();
        if(holder == null) return;
        if(holder.world == null) return;
        String key = holder.world;
        SimpleWorldConfig swc = WorldConfig.wc.dict.get(key);
        
        if(slot == 0) {
            swc.smoofydungeon.doNaturalSpawn = !swc.smoofydungeon.doNaturalSpawn;
            WorldConfig.wc.dict.put(key, swc);
            WorldConfig.save();
            p.sendMessage(I18n.instance.World_Config_Save);
            holder.init();
        }
        if(slot == 1) {
            swc.smoofydungeon.builtinLoot = !swc.smoofydungeon.builtinLoot;
            WorldConfig.wc.dict.put(key, swc);
            WorldConfig.save();
            p.sendMessage(I18n.instance.World_Config_Save);
            holder.init();
        }
        if(slot == 2) {
            List<LootNode> loots = swc.smoofydungeon.loots;
            LootManager lm = new LootManager(loots, holder);
            lm.openInventory(p);
        }
        if(slot == 3) {
            Set<String> biomes = swc.smoofydungeon.biomeExclusions;
            BiomeSetting bs = new BiomeSetting(holder.world, holder, biomes);
            bs.openInventory(p);
        }
        if(slot == 17) {
            holder.parent.openInventory(p);
        }
    }
    
    @Override
    public void init() {
        if(WorldConfig.wc.dict.get(world) == null) {
            SimpleWorldConfig swc = new SimpleWorldConfig();
            WorldConfig.wc.dict.put(world, swc);
            WorldConfig.save();
        }
        show();
    }
    
    private void show() {
        inv.clear();
        SimpleWorldConfig swc = WorldConfig.wc.dict.get(world);
        {
            Material material;
            String status;
            if(swc.smoofydungeon.doNaturalSpawn) {
                material = ENABLE;
                status = I18n.instance.Enable;
            } else {
                material = DISABLE;
                status = I18n.instance.Disable;
            }
            
            ItemStack is = new ItemStack(material);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(I18n.instance.Natural_Spawn);
            
            List<String> lores = new ArrayList<>();
            lores.add(I18n.instance.Status + " : " + status);
            for(String str : I18n.instance.NaturalSpawnStr) {
                lores.add(str);
            }
            im.setLore(lores);
            is.setItemMeta(im);
            
            addItem(0, is);
        }
        {
            Material material;
            String status;
            if(swc.smoofydungeon.builtinLoot) {
                material = ENABLE;
                status = I18n.instance.Enable;
            } else {
                material = DISABLE;
                status = I18n.instance.Disable;
            }
            
            ItemStack is = new ItemStack(material);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(I18n.instance.Builtin_Loot);
            
            List<String> lores = new ArrayList<>();
            lores.add(I18n.instance.Status + " : " + status);
            im.setLore(lores);
            is.setItemMeta(im);
            
            addItem(1, is);
        }
        {
            ItemStack is = new ItemStack(Material.CHEST);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(I18n.instance.Loot_Config);
            is.setItemMeta(im);
            
            addItem(2, is);
        }
        {
            ItemStack is = new ItemStack(Material.LILAC);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(I18n.instance.Biome_Setting);
            is.setItemMeta(im);
            
            addItem(3, is);
        }
        {
            ItemStack is = new ItemStack(Material.LEVER);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(I18n.instance.Back);
            is.setItemMeta(im);
            
            addItem(1, 8, is);
        }
    }
}

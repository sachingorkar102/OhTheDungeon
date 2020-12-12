/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.util.gui;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import otd.util.config.SimpleWorldConfig;
import otd.util.config.WorldConfig;
import otd.world.ChunkList;
import otd.world.DungeonTask;
import otd.world.DungeonWorld;
import otd.world.WorldDefine;
import zhehe.util.I18n;

/**
 *
 * @author zhehe
 */
public class CreateDungeonWorld extends Content {
    
    public final static CreateDungeonWorld instance = new CreateDungeonWorld();
    
    public CreateDungeonWorld() {
        super(I18n.instance.Menu_Remove_World, InventoryType.DISPENSER);
    }
    
    @Override
    public void init() {
        inv.clear();
        {
            ItemStack is = new ItemStack(Material.MAP);
            ItemMeta im = is.getItemMeta();
            
            im.setDisplayName(I18n.instance.Menu_create_world_config);
            List<String> lores = new ArrayList<>();
            lores.add(I18n.instance.Menu_create_world_config_lore);
            im.setLore(lores);
            
            is.setItemMeta(im);
            
            addItem(0, is);
        }
        {
            ItemStack is = new ItemStack(Material.COMPASS);
            ItemMeta im = is.getItemMeta();
            
            im.setDisplayName(I18n.instance.Menu_create_dungeon_count);
            List<String> lores = new ArrayList<>();
            lores.add(I18n.instance.Current_Dungeon_Count + " : " + WorldConfig.wc.dungeon_world.dungeon_count);
            lores.add(I18n.instance.Amount_Item_Tip1);
            lores.add(I18n.instance.Amount_Item_Tip2);
            im.setLore(lores);
            is.setItemMeta(im);
            
            addItem(1, is);
        }
        {
            ItemStack is = new ItemStack(Material.OAK_SIGN);
            ItemMeta im = is.getItemMeta();
            
            im.setDisplayName(I18n.instance.Menu_create);
            List<String> lores = new ArrayList<>();
            lores.add(I18n.instance.Menu_create_lore);
            im.setLore(lores);
            
            is.setItemMeta(im);
            
            addItem(8, is);
        }
    }
    
    @EventHandler
    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof CreateDungeonWorld)) {
            return;
        }
        if (e.getClick().equals(ClickType.NUMBER_KEY)){
            kcancel(e);
            return;
        }

        kcancel(e);
        int slot = e.getRawSlot();
        Player p = (Player) e.getWhoClicked();
        CreateDungeonWorld holder = (CreateDungeonWorld) e.getInventory().getHolder();
        if(holder == null) return;
        
        if(slot == 0) {
            WorldEditor we = new WorldEditor(WorldDefine.WORLD_NAME, Environment.NORMAL, holder);
            we.openInventory(p);
        }
        if(slot == 1) {
            if (e.getClick().equals(ClickType.LEFT)) {
                WorldConfig.wc.dungeon_world.dungeon_count++;
                if(WorldConfig.wc.dungeon_world.dungeon_count > 30) WorldConfig.wc.dungeon_world.dungeon_count = 30;
                WorldConfig.save();
            } else if (e.getClick().equals(ClickType.RIGHT)) {
                WorldConfig.wc.dungeon_world.dungeon_count--;
                if(WorldConfig.wc.dungeon_world.dungeon_count < 1) WorldConfig.wc.dungeon_world.dungeon_count = 1;
                WorldConfig.save();
            }
            holder.init();
        }
        if(slot == 8) {
            if(!checkEnabled()) {
                p.sendMessage(ChatColor.RED + I18n.instance.Must_Natural_Spawn);
            } else {
                if(!DungeonTask.isGenerating()) {
                    ChunkList.initChunksMap(WorldConfig.wc.dungeon_world);
                    DungeonWorld.generateDungeonWorld();
                    DungeonTask.start();
                    p.sendMessage(ChatColor.BLUE + I18n.instance.Dungeon_Plot_In_Progress);
                } else {
                    p.sendMessage(ChatColor.BLUE + I18n.instance.Dungeon_Plot_In_Progress);
                }
                p.closeInventory();
            }
        }
    }
    
    private static boolean checkEnabled() {
        SimpleWorldConfig swc = WorldConfig.wc.dict.get(WorldDefine.WORLD_NAME);
        
        return swc.aether_dungeon.doNaturalSpawn ||
                swc.ant_man_dungeon.doNaturalSpawn ||
                swc.battletower.doNaturalSpawn ||
                swc.doomlike.doNaturalSpawn ||
                swc.draylar_battletower.doNaturalSpawn ||
                swc.lich_tower.doNaturalSpawn ||
                swc.roguelike.doNaturalSpawn ||
                swc.smoofydungeon.doNaturalSpawn;
    }
}

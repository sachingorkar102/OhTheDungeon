/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.util.gui;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import otd.util.config.WorldConfig;
import otd.world.DungeonTask;
import zhehe.util.I18n;

/**
 *
 * @author shadow
 */
public class MainMenu extends Content {
    
    public final static MainMenu instance = new MainMenu();
    
    public MainMenu() {
        super(I18n.instance.Main_Menu, InventoryType.DISPENSER);
    }
    
    @Override
    public void init() {
        inv.clear();
        {
            ItemStack is = new ItemStack(Material.MAP);
            ItemMeta im = is.getItemMeta();
            
            im.setDisplayName(I18n.instance.Menu1);
            List<String> lores = new ArrayList<>();
            lores.add(I18n.instance.Menu1_Lore1);
            lores.add(I18n.instance.Menu1_Lore2);
            lores.add(I18n.instance.Menu1_Lore3);
            lores.add(I18n.instance.Menu1_Lore4);
            im.setLore(lores);
            
            is.setItemMeta(im);
            
            addItem(3, is);
        }
        {
            ItemStack is = new ItemStack(Material.WRITTEN_BOOK);
            ItemMeta im = is.getItemMeta();
            
            im.setDisplayName(I18n.instance.Menu2);
            List<String> lores = new ArrayList<>();
            lores.add(I18n.instance.Menu2_Lore1);
            lores.add(I18n.instance.Menu2_Lore2);
            lores.add(I18n.instance.Menu2_Lore3);
            lores.add(I18n.instance.Menu2_Lore4);
            im.setLore(lores);
            
            is.setItemMeta(im);
            
            addItem(5, is);
        }
    }
    
    @EventHandler
    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof MainMenu)) {
            return;
        }
        if (e.getClick().equals(ClickType.NUMBER_KEY)){
            kcancel(e);
            return;
        }

        kcancel(e);
        int slot = e.getRawSlot();
        Player p = (Player) e.getWhoClicked();
        MainMenu holder = (MainMenu) e.getInventory().getHolder();
        if(holder == null) return;
        
        if(slot == 3) {
            WorldManager wm = new WorldManager();
            wm.openInventory(p);
        }
        if(slot == 5) {
            if(DungeonTask.isGenerating()) {
                p.sendMessage(I18n.instance.Dungeon_Plot_In_Progress);
                p.closeInventory();
            } else if(WorldConfig.wc.dungeon_world.finished) {
                RemoveDungeonWorld r = new RemoveDungeonWorld();
                r.openInventory(p);
            } else {
                CreateDungeonWorld c = new CreateDungeonWorld();
                c.openInventory(p);
            }
        }
    }
}

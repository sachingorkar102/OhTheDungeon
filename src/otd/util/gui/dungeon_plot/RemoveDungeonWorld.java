/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.util.gui.dungeon_plot;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import otd.util.gui.Content;
import otd.world.ChunkList;
import otd.world.DungeonWorld;
import zhehe.util.I18n;

/**
 *
 * @author shadow
 */
public class RemoveDungeonWorld extends Content {
    
    public final static RemoveDungeonWorld instance = new RemoveDungeonWorld();
    
    public RemoveDungeonWorld() {
        super(I18n.instance.Menu_Remove_World, InventoryType.DISPENSER);
    }
    
    @Override
    public void init() {
        inv.clear();
        {
            ItemStack is = new ItemStack(Material.LAVA_BUCKET);
            ItemMeta im = is.getItemMeta();
            
            im.setDisplayName(I18n.instance.Menu_remove);
            List<String> lores = new ArrayList<>();
            lores.add(I18n.instance.Menu_remove_lore);
            im.setLore(lores);
            
            is.setItemMeta(im);
            
            addItem(4, is);
        }
    }
    
    @EventHandler
    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof RemoveDungeonWorld)) {
            return;
        }
        if (e.getClick().equals(ClickType.NUMBER_KEY)){
            kcancel(e);
            return;
        }

        kcancel(e);
        int slot = e.getRawSlot();
        Player p = (Player) e.getWhoClicked();
        RemoveDungeonWorld holder = (RemoveDungeonWorld) e.getInventory().getHolder();
        if(holder == null) return;
        
        if(slot == 4) {
            DungeonWorld.removeDungeonWorld();
            ChunkList.clear();
            p.closeInventory();
            p.sendMessage(ChatColor.RED + I18n.instance.Menu_remove_click);
        }
    }
}

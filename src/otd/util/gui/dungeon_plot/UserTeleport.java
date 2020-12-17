/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.util.gui.dungeon_plot;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import otd.util.config.WorldConfig;
import otd.util.gui.Content;
import otd.world.ChunkList;
import otd.world.DungeonTask;
import otd.world.DungeonWorld;
import zhehe.util.I18n;

/**
 *
 * @author shadow
 */
public class UserTeleport extends Content {
    public final static UserTeleport instance = new UserTeleport();
    private final static int SLOT = 54;
    public UserTeleport() {
        super(I18n.instance.Dungeon_Plot_User_Teleport, SLOT);
    }
    
    private static void teleport(Player p, int[] pos) {
        if(
                DungeonWorld.world == null ||
                WorldConfig.wc.dungeon_world.finished == false ||
                DungeonTask.isGenerating()
                ) {
            p.sendMessage(I18n.instance.Dungeon_Plot_Not_Ready);
            return;
        }
        int x = pos[0] * 16;
        int z = pos[1] * 16 - 16;
        
        int y = DungeonWorld.world.getHighestBlockYAt(x, z);
        if(y > 80) y = 66;
        p.teleport(new Location(DungeonWorld.world, x, y, z));
    }
    
    @Override
    public void init() {
        int count = WorldConfig.wc.dungeon_world.dungeon_count_finish;
        
        for(int i = 0; i < count; i++) {
            ItemStack is = new ItemStack(Material.COMPASS);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(I18n.instance.Dungeon_Plot + " " + i);
            
            List<String> lores = new ArrayList<>();
            lores.add(I18n.instance.Click_To_Teleport);
            
            im.setLore(lores);
            
            is.setItemMeta(im);
            
            addItem(i, is);
        }
    }
    
    @EventHandler
    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof UserTeleport)) {
            return;
        }
        
        if (e.getClick().equals(ClickType.NUMBER_KEY)){
            kcancel(e);
            return;
        }

        kcancel(e);
        int slot = e.getRawSlot();
        Player p = (Player) e.getWhoClicked();
        UserTeleport holder = (UserTeleport) e.getInventory().getHolder();
        if(holder == null) return;
        
        int count = WorldConfig.wc.dungeon_world.dungeon_count_finish;
        if(slot < count) {
            ItemStack is = e.getInventory().getItem(slot);
            if(is != null && is.getType() == Material.COMPASS) {
                int[] pos = ChunkList.chunk_table.get(slot);
                p.closeInventory();
                teleport(p, pos);
            }
        }
    }
}

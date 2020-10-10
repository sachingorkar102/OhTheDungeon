/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zhehe.util.gui;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import zhehe.util.I18n;
import zhehe.util.config.SimpleWorldConfig;
import zhehe.util.config.WorldConfig;

/**
 *
 * @author
 */
public class WorldSpawnerManager extends Content {
    public final World world;
    public final WorldEditor parent;
    private boolean egg_change_spawner;
    private boolean silk_vanilla_spawner;
    private boolean silk_dungeon_spawner;
    private boolean mob_drop_in_vanilla_spawner;
    private boolean mob_drop_in_dungeon_spawner;
    private float disappearance_rate_vanilla;
    private float disappearance_rate_dungeon;
    
    private final static int SLOT = 18;
    
    private final static Material DISABLE = Material.MUSIC_DISC_BLOCKS;
    private final static Material ENABLE = Material.MUSIC_DISC_CAT;
    private final static Material RATE = Material.WRITABLE_BOOK;
    private final static Material APPLY = Material.LEVER;
    
    private WorldSpawnerManager() {
        super("", SLOT);
        this.world = null;
        this.parent = null;
    }
    public static WorldSpawnerManager instance = new WorldSpawnerManager();
    
    public WorldSpawnerManager(World world, WorldEditor parent) {
        super(I18n.instance.World_Spawner_Manager + " : " + world.getName(), SLOT);
        this.world = world;
        this.parent = parent;
        
        {
            if(WorldConfig.wc.dict.containsKey(world.getName())) {
                SimpleWorldConfig config = WorldConfig.wc.dict.get(world.getName());
                
                egg_change_spawner = config.egg_change_spawner;
                silk_vanilla_spawner = config.silk_vanilla_spawner;
                silk_dungeon_spawner = config.silk_dungeon_spawner;
                mob_drop_in_vanilla_spawner = config.mob_drop_in_vanilla_spawner;
                mob_drop_in_dungeon_spawner = config.mob_drop_in_dungeon_spawner;
                disappearance_rate_vanilla = config.disappearance_rate_vanilla;
                disappearance_rate_dungeon = config.disappearance_rate_dungeon;
            } else {
                egg_change_spawner = false;
                silk_vanilla_spawner = false;
                silk_dungeon_spawner = false;
                mob_drop_in_vanilla_spawner = true;
                mob_drop_in_dungeon_spawner = false;
                disappearance_rate_vanilla = 0;
                disappearance_rate_dungeon = 0;
            }
            
            if(disappearance_rate_vanilla < 0) disappearance_rate_vanilla = 0;
            if(disappearance_rate_vanilla > 100) disappearance_rate_vanilla = 100;
            if(disappearance_rate_dungeon < 0) disappearance_rate_dungeon = 0;
            if(disappearance_rate_dungeon > 100) disappearance_rate_dungeon = 100;
        }

    }
    
    @EventHandler
    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof WorldSpawnerManager)) {
            return;
        }
        kcancel(e);
        
        int slot = e.getRawSlot();
        if(slot < 0 && slot >= 18) {
            return;
        }

        Player p = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;
        WorldSpawnerManager holder = (WorldSpawnerManager) e.getInventory().getHolder();
        if(holder == null || holder.world == null) return;
        
//        Bukkit.getLogger().log(Level.SEVERE, "!"+slot);
        
        if(slot == 0) {
            holder.egg_change_spawner = !holder.egg_change_spawner;
            holder.init();
        }
        if(slot == 1) {
            holder.mob_drop_in_vanilla_spawner = !holder.mob_drop_in_vanilla_spawner;
            holder.init();
        }
        if(slot == 2) {
            holder.mob_drop_in_dungeon_spawner = !holder.mob_drop_in_dungeon_spawner;
            holder.init();
        }
        if(slot == 3) {
            if (e.getClick().equals(ClickType.LEFT)) {
                holder.disappearance_rate_vanilla += 1;
            } else if(e.getClick().equals(ClickType.RIGHT)) {
                holder.disappearance_rate_vanilla -= 1;
            }
            holder.init();
        }
        if(slot == 4) {
            if (e.getClick().equals(ClickType.LEFT)) {
                holder.disappearance_rate_dungeon += 1;
            } else if(e.getClick().equals(ClickType.RIGHT)) {
                holder.disappearance_rate_dungeon -= 1;
            }
            holder.init();
        }
        if(slot == 17) {
            holder.save();
            WorldConfig.save();
            
            holder.parent.openInventory(p);
        }
    }
    
    private void save() {
        if(WorldConfig.wc.dict.containsKey(world.getName())) {
            SimpleWorldConfig config = WorldConfig.wc.dict.get(world.getName());
            config.egg_change_spawner = this.egg_change_spawner;
            config.silk_vanilla_spawner = this.silk_vanilla_spawner;
            config.silk_dungeon_spawner = this.silk_dungeon_spawner;
            config.mob_drop_in_vanilla_spawner = this.mob_drop_in_vanilla_spawner;
            config.mob_drop_in_dungeon_spawner = this.mob_drop_in_dungeon_spawner;
            config.disappearance_rate_vanilla = this.disappearance_rate_vanilla;
            config.disappearance_rate_dungeon = this.disappearance_rate_dungeon;
        } else {
            SimpleWorldConfig config = new SimpleWorldConfig();
            config.egg_change_spawner = this.egg_change_spawner;
            config.silk_vanilla_spawner = this.silk_vanilla_spawner;
            config.silk_dungeon_spawner = this.silk_dungeon_spawner;
            config.mob_drop_in_vanilla_spawner = this.mob_drop_in_vanilla_spawner;
            config.mob_drop_in_dungeon_spawner = this.mob_drop_in_dungeon_spawner;
            config.disappearance_rate_vanilla = this.disappearance_rate_vanilla;
            config.disappearance_rate_dungeon = this.disappearance_rate_dungeon;
            WorldConfig.wc.dict.put(world.getName(), config);
        }
    }
    
    @Override
    public void init() {
        inv.clear();
        if(disappearance_rate_vanilla < 0) disappearance_rate_vanilla = 0;
        if(disappearance_rate_vanilla > 100) disappearance_rate_vanilla = 100;
        if(disappearance_rate_dungeon < 0) disappearance_rate_dungeon = 0;
        if(disappearance_rate_dungeon > 100) disappearance_rate_dungeon = 100;
        {
            Material material;
            String status;
            if(egg_change_spawner) {
                material = ENABLE;
                status = I18n.instance.Enable;
            } else {
                material = DISABLE;
                status = I18n.instance.Disable;
            }
            ItemStack is = new ItemStack(material);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(I18n.instance.ChangeSpawner);
            List<String> lores = new ArrayList<>();
            lores.add(I18n.instance.Status + " : " + status);
            im.setLore(lores);
            
            is.setItemMeta(im);
            
            addItem(0, 0, is);
        }
//        {
//            Material material;
//            String status;
//            if(silk_vanilla_spawner) {
//                material = ENABLE;
//                status = I18n.instance.Enable;
//            } else {
//                material = DISABLE;
//                status = I18n.instance.Disable;
//            }
//            ItemStack is = new ItemStack(material);
//            ItemMeta im = is.getItemMeta();
//            im.setDisplayName(I18n.instance.Silk_Vanilla_Spawner);
//            List<String> lores = new ArrayList<>();
//            lores.add(I18n.instance.Status + " : " + status);
//            im.setLore(lores);
//            
//            is.setItemMeta(im);
//            
//            addItem(0, 1, is);
//        }
//        {
//            Material material;
//            String status;
//            if(silk_dungeon_spawner) {
//                material = ENABLE;
//                status = I18n.instance.Enable;
//            } else {
//                material = DISABLE;
//                status = I18n.instance.Disable;
//            }
//            ItemStack is = new ItemStack(material);
//            ItemMeta im = is.getItemMeta();
//            im.setDisplayName(I18n.instance.Silk_Dungeon_Spawner);
//            List<String> lores = new ArrayList<>();
//            lores.add(I18n.instance.Status + " : " + status);
//            lores.add(I18n.instance.Dungeon_Spawner_Lore1);
//            lores.add(I18n.instance.Dungeon_Spawner_Lore2);
//            im.setLore(lores);
//            
//            is.setItemMeta(im);
//            
//            addItem(0, 2, is);
//        }
        {
            Material material;
            String status;
            if(mob_drop_in_vanilla_spawner) {
                material = ENABLE;
                status = I18n.instance.Enable;
            } else {
                material = DISABLE;
                status = I18n.instance.Disable;
            }
            ItemStack is = new ItemStack(material);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(I18n.instance.Mob_Drop_In_Vanilla_Spawner);
            List<String> lores = new ArrayList<>();
            lores.add(I18n.instance.Status + " : " + status);
            im.setLore(lores);
            
            is.setItemMeta(im);
            
            addItem(0, 1, is);
        }
        {
            Material material;
            String status;
            if(mob_drop_in_dungeon_spawner) {
                material = ENABLE;
                status = I18n.instance.Enable;
            } else {
                material = DISABLE;
                status = I18n.instance.Disable;
            }
            ItemStack is = new ItemStack(material);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(I18n.instance.Mob_Drop_In_Dungeon_Spawner);
            List<String> lores = new ArrayList<>();
            lores.add(I18n.instance.Status + " : " + status);
            lores.add(I18n.instance.Dungeon_Spawner_Lore1);
            lores.add(I18n.instance.Dungeon_Spawner_Lore2);
            lores.add(I18n.instance.Dungeon_Spawner_Lore3);
            im.setLore(lores);
            
            is.setItemMeta(im);
            
            addItem(0, 2, is);
        }
        {
            Material material = RATE;
            ItemStack is = new ItemStack(material);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(I18n.instance.Spawner_Disappear_Vanilla);
            List<String> lores = new ArrayList<>();
            lores.add(disappearance_rate_vanilla + "%");
            lores.add("===========");
            lores.add(I18n.instance.Amount_Item_Tip1);
            lores.add(I18n.instance.Amount_Item_Tip2);
            im.setLore(lores);
            
            is.setItemMeta(im);
            
            addItem(0, 3, is);
        }
        {
            Material material = RATE;
            ItemStack is = new ItemStack(material);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(I18n.instance.Spawner_Disappear_Dungeon);
            List<String> lores = new ArrayList<>();
            lores.add(disappearance_rate_dungeon + "%");
            lores.add(I18n.instance.Dungeon_Spawner_Lore1);
            lores.add(I18n.instance.Dungeon_Spawner_Lore2);
            lores.add(I18n.instance.Dungeon_Spawner_Lore3);
            lores.add("===========");
            lores.add(I18n.instance.Amount_Item_Tip1);
            lores.add(I18n.instance.Amount_Item_Tip2);
            im.setLore(lores);
            
            is.setItemMeta(im);
            
            addItem(0, 4, is);
        }
        {
            ItemStack is = new ItemStack(APPLY);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(I18n.instance.Back);
            is.setItemMeta(im);
            
            addItem(1, 8, is);
        }
    }
}

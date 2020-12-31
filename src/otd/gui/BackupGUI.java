/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.gui;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import otd.Main;
import otd.util.GZIPUtils;
import otd.config.WorldConfig;
import otd.util.I18n;

/**
 *
 * @author shadow
 */
public class BackupGUI extends Content {
    public final static BackupGUI instance = new BackupGUI();
    private final static int SLOT = 54;
    private final static Material PAGE = Material.END_CRYSTAL;
    private final static Material ISO = Material.BLUE_STAINED_GLASS_PANE;
    private final static Material BACKUP = Material.PAPER;
    public int offset;
    public final Content parent;
    
    private BackupGUI() {
        super(I18n.instance.Config_Backup, SLOT);
        parent = null;
        
        offset = 0;
    }
    
    public BackupGUI(Content parent) {
        super(I18n.instance.Config_Backup, SLOT);
        this.parent = parent;
        
        offset = 0;
    }
    
    public static void initBackupFolder() {
        File backup = new File(Main.instance.getDataFolder(), "backups");
        if(!backup.exists()) {
            backup.mkdir();
        }
    }
    
    private static String getFileName() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss.SSS");
        String str = simpleDateFormat.format(new Date());
        return "File-" + str + ".dat";
    }
    
    private static void createBackup(Player p) {
        String file_name = getFileName();
        String json = (new Gson()).toJson(WorldConfig.wc);
        json = GZIPUtils.compress(json);
        
        File file = new File(Main.instance.getDataFolder() + File.separator + "backups", file_name);
        try(OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "utf-8")) {
            oStreamWriter.append(json);
            oStreamWriter.close();
            p.sendMessage(ChatColor.YELLOW + file.getAbsolutePath());
        } catch(IOException e) {
            p.sendMessage(ChatColor.RED + I18n.instance.Fail_To_Create_Backup);
        }
    }
    
    private static void restoreBackup(String file_name, Player p) {
        File file = new File(Main.instance.getDataFolder() + File.separator + "backups", file_name);
        WorldConfig old = WorldConfig.wc;
        if(!file.exists()) {
            p.sendMessage(ChatColor.RED + I18n.instance.Fail_To_Restore_Backup);
            return;
        }
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"))) {
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                line = reader.readLine();
            }
            String json = sb.toString();
            json = GZIPUtils.uncompress(json);
            WorldConfig wc = (new Gson()).fromJson(json, WorldConfig.class);
            wc.dungeon_world = WorldConfig.wc.dungeon_world;
            WorldConfig.wc = wc;
            WorldConfig.save();
            p.sendMessage(ChatColor.YELLOW + file.getAbsolutePath());
        } catch (IOException ex) {
            WorldConfig.wc = old;
            p.sendMessage(ChatColor.RED + I18n.instance.Fail_To_Restore_Backup);
        }
    }
    
    @EventHandler
    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof BackupGUI)) {
            return;
        }
        if (e.getClick().equals(ClickType.NUMBER_KEY)){
            kcancel(e);
            return;
        }

        kcancel(e);

        Player p = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();

        // verify current item is not null
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;
        
        int slot = e.getRawSlot();
        BackupGUI holder = (BackupGUI) e.getInventory().getHolder();
        if(holder == null) return;
        
        if(slot == 0) {
            createBackup(p);
            holder.init();
        }
        if(slot == 6) {
            holder.offset--;
            if(holder.offset < 0) holder.offset = 0;
            holder.init();
        }
        if(slot == 7) {
            holder.offset++;
            holder.init();
        }
        if(slot == 8) {
            holder.parent.openInventory(p);
        }
        if(slot >= 18 && slot <= 53) {
            ItemStack is = clickedItem;
            if(is.getType() == BACKUP) {
                String file_name = is.getItemMeta().getDisplayName();
                restoreBackup(file_name, p);
                p.closeInventory();
            }
        }
    }
    
    @Override
    public void init() {
        File backup = new File(Main.instance.getDataFolder(), "backups");
        File [] files = backup.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".dat");
            }
        });
        
        List<File> list = new ArrayList<>();
        
        for(File file : files) {
            list.add(file);
        }
        
        inv.clear();
        {
            ItemStack is = new ItemStack(Material.FEATHER);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(I18n.instance.Create_New_Backup);
            is.setItemMeta(im);
            
            addItem(0, 0, is);
        }
        {
            ItemStack is = new ItemStack(PAGE);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(I18n.instance.Previous);
            List<String> lores = new ArrayList<>();
            lores.add(I18n.instance.Current_Page + " : " + Integer.toString(offset + 1));
            im.setLore(lores);
            is.setItemMeta(im);
            
            addItem(0, 6, is);
        }
        {
            ItemStack is = new ItemStack(PAGE);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(I18n.instance.Next);
            List<String> lores = new ArrayList<>();
            lores.add(I18n.instance.Current_Page + " : " + Integer.toString(offset + 1));
            im.setLore(lores);
            is.setItemMeta(im);
            
            addItem(0, 7, is);
        }
        {
            ItemStack is = new ItemStack(Material.STONE_BUTTON);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(I18n.instance.Back);
            is.setItemMeta(im);
            
            addItem(0, 8, is);
        }
        {
            for(int i = 0; i < 9; i++) {
                ItemStack is = new ItemStack(ISO);
                addItem(1, i, is);
            }
        }
        {
            int index = 18;
            int i = offset * 36;
            while(index < SLOT && i < list.size()) {
                File f = list.get(i);
                ItemStack is = new ItemStack(BACKUP);
                ItemMeta im = is.getItemMeta();
                if(im != null) {
                    im.setDisplayName(f.getName());
                    List<String> lores = new ArrayList<>();
//                    lores.add(f.getAbsolutePath());
                    lores.add(0, I18n.instance.Click_To_Restore);
                    im.setLore(lores);
                    is.setItemMeta(im);
                    addItem(index, is);
                }
                index++;
                i++;
            }
        }
    }
}

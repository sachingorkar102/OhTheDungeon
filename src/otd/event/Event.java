/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.spigotmc.SpigotConfig;
import otd.Main;
import static otd.update.UpdateChecker.UPDATE_RESULT;
import otd.util.Roll;
import zhehe.util.I18n;

/**
 *
 * @author
 */
public class Event implements Listener {
    
    public static Map<String, Long> roll_cool_down = new HashMap<>();
    public final static Set<Material> SPAWN_EGGS;
    static {
        SPAWN_EGGS = new HashSet<>();
        for(Material m : Material.values()) {
            String s = m.toString();
            if(s.contains("SPAWN_EGG")) {
                SPAWN_EGGS.add(m);
            }
        }
    }
    
    private boolean getBoolean(String path, boolean def, String worldName) {
        SpigotConfig.config.addDefault("world-settings.default." + path, def);
        return SpigotConfig.config.getBoolean("world-settings." + worldName + "." + path, SpigotConfig.config.getBoolean("world-settings.default." + path));
    }
    
    @EventHandler
    public void onPlayerJoin_Updater(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if(p.hasPermission("oh_the_dungeons.admin")) {
            synchronized(UPDATE_RESULT) {
                if(UPDATE_RESULT.update_valid) {
                    p.sendMessage(ChatColor.RED + "[OP ONLY MESSAGE]" + ChatColor.GREEN + " [Oh The Dungeons You'll Go] A new version is valid on Publish Site");
                    p.sendMessage(ChatColor.RED + "[OP ONLY MESSAGE]" + ChatColor.GREEN + "https://www.spigotmc.org/resources/oh-the-dungeons-youll-go.76437/");
                }
            }
//            p.sendMessage(ChatColor.GREEN + "[Oh The Dungeons You'll Go] The free version is no longer supported. We will release Premium version");
            //boolean b = (boolean) Bukkit.getServer().spigot().getConfig().get("world-settings.default.nerf-spawner-mobs", false);
            boolean b = false;
            for(World world : Bukkit.getWorlds()) {
                b = b | getBoolean("nerf-spawner-mobs", false, world.getName());
            }
            if(b) {
                p.sendMessage(ChatColor.RED + I18n.instance.Nerf_Msg);
            }
        }        
//        p.sendMessage("[Oh The Dungeons You'll Go] New release site: https://ohthedungeon.com/down");
    }
    private static NamespacedKey root = new NamespacedKey(Main.instance, "dungeon/root");
    
    @EventHandler
    public void onPlayQuit(PlayerQuitEvent e) {
        roll_cool_down.remove(e.getPlayer().getName());
    }
    
    @EventHandler(priority=EventPriority.HIGH)
    public void onPlayerUse(PlayerInteractEvent event){
        Player p = event.getPlayer();
        Inventory inv = p.getInventory();
        
        if(inv instanceof PlayerInventory) {
            boolean use = false;
            PlayerInventory pinv = (PlayerInventory) inv;
            if(!use) {
                ItemStack item = pinv.getItemInMainHand();
                ItemMeta im = item.getItemMeta();
                if(im != null && im.hasLore()) {
                    List<String> lores = im.getLore();
                    if(lores != null && lores.size() > 0) {
                        if(lores.get(0).equals(Roll.DICE)) {
                            use = true;
                        }
                    }
                }
            }
            if(!use) {
                ItemStack item = pinv.getItemInOffHand();
                ItemMeta im = item.getItemMeta();
                if(im != null && im.hasLore()) {
                    List<String> lores = im.getLore();
                    if(lores != null && lores.size() > 0) {
                        if(lores.get(0).equals(Roll.DICE)) {
                            use = true;
                        }
                    }
                }
            }
            if(use) {
                event.setCancelled(true);
                Roll.roll(p);
            }
        }
    }
    
    
//    private static NamespacedKey bt = new NamespacedKey(Main.instance, "dungeon/battletower");
//    private static NamespacedKey rg = new NamespacedKey(Main.instance, "dungeon/roguelike");
//    private static NamespacedKey dm = new NamespacedKey(Main.instance, "dungeon/doomlike");
    
//    @EventHandler
//    public void onPlayerTeleport(PlayerTeleportEvent event) {
//        Player p = event.getPlayer();
//        String world_name = p.getWorld().getName();
//        
//        if(WorldConfig.wc.dict.containsKey(world_name)) {
//            SimpleWorldConfig swc = WorldConfig.wc.dict.get(world_name);
//            if(swc.battletower.doNaturalSpawn) {
//                Advancement adv = Bukkit.getAdvancement(bt);
//                if(adv != null) {
//                    AdvancementProgress ap = p.getAdvancementProgress(adv);
//                    if(!ap.isDone()) {
//                        for(String str : ap.getRemainingCriteria()) ap.awardCriteria(str);
//                    }
//                }
//            }
//            if(swc.doomlike.doNaturalSpawn) {
//                Advancement adv = Bukkit.getAdvancement(dm);
//                if(adv != null) {
//                    AdvancementProgress ap = p.getAdvancementProgress(adv);
//                    if(!ap.isDone()) {
//                        for(String str : ap.getRemainingCriteria()) ap.awardCriteria(str);
//                    }
//                }
//            }
//            if(swc.roguelike.doNaturalSpawn) {
//                Advancement adv = Bukkit.getAdvancement(rg);
//                if(adv != null) {
//                    AdvancementProgress ap = p.getAdvancementProgress(adv);
//                    if(!ap.isDone()) {
//                        for(String str : ap.getRemainingCriteria()) ap.awardCriteria(str);
//                    }
//                }
//            }
//        }
//    }
    
//    @EventHandler
//    public void onPlayerJoin(PlayerJoinEvent event) {
//        Player p = event.getPlayer();
////        p.sendMessage("[Oh The Dungeons You'll Go] It's a good day to die!");
//        
//        Advancement adv = Bukkit.getAdvancement(root);
//        if(adv == null) return;
//        AdvancementProgress ap = p.getAdvancementProgress(adv);
//        if(ap.isDone()) return;
//        Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
//            for(String str : ap.getRemainingCriteria()) {
//                ap.awardCriteria(str);
//            }
//        }, 40);
//    }
}

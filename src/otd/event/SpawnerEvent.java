/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.event;

import forge_sandbox.twilightforest.structures.lichtower.boss.Lich;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;
import otd.Main;
import static otd.event.Event.SPAWN_EGGS;
import zhehe.util.I18n;
import otd.util.config.SimpleWorldConfig;
import otd.util.config.WorldConfig;

/**
 *
 * @author
 */
public class SpawnerEvent implements Listener {
    @EventHandler
    public void NoChangeLimit(PlayerInteractEvent e) {
        if (e.getItem() == null) {
            return;
        }
        Block block = e.getClickedBlock();
        if (block == null) {
            return;
        }
        Material type = block.getType();
        if(type != Material.SPAWNER) return;
        
        Material item1 = e.getPlayer().getInventory().getItemInMainHand().getType();
        Material item2 = e.getPlayer().getInventory().getItemInOffHand().getType();
        
        if((!SPAWN_EGGS.contains(item1)) && (!SPAWN_EGGS.contains(item2))) return;
        
        Player p = e.getPlayer();
        String world = p.getWorld().getName();
        if(WorldConfig.wc.dict.containsKey(world) && !WorldConfig.wc.dict.get(world).egg_change_spawner) {
            if(p.hasPermission("oh_the_dungeons.admin")) {
                p.sendMessage("Bypass spawner change for OP");
            } else {
                e.setCancelled(true);
                p.sendMessage(I18n.instance.ChangeSpawnerMessage);
            }
        }
    }
    @EventHandler (priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onSpawnerMine(BlockBreakEvent event) {
        Block block = event.getBlock();
        if(block.getType() != Material.SPAWNER) return;
        
        World world = block.getWorld();
        Player p = event.getPlayer();
        String world_name = world.getName();
        
        boolean isDungeon = false;
        if(block.getState() instanceof TileState) {
            TileState ts = (TileState) block.getState();
            NamespacedKey key = new NamespacedKey(Main.instance, "decry");
            if(ts.getPersistentDataContainer().has(key, PersistentDataType.BYTE)) {
                isDungeon = true;
            }
        }
        if(!isDungeon) return;
        
        if(WorldConfig.wc.dict.containsKey(world_name)) {
            SimpleWorldConfig config = WorldConfig.wc.dict.get(world_name);
            
            if(config.prevent_spawner_breaking) {
                if(p.hasPermission("oh_the_dungeons.admin")) {
                    p.sendMessage("Bypass spawner change for OP");
                    return;
                }
                event.setCancelled(true);
                return;
            }
            
            if(config.prevent_spawner_dropping) {
                event.setCancelled(true);
                block.setType(Material.AIR);
            }
        }
    }
    
//    @EventHandler
//    public void onSpawnerMine(BlockBreakEvent event) {
//        Block block = event.getBlock();
//        
//        if(block.getType() != Material.SPAWNER) return;
//        
//        World world = block.getWorld();
//        Player p = event.getPlayer();
//        
//        ItemStack itemInHand = p.getInventory().getItemInMainHand();
//        Material tool = itemInHand.getType();
//        
//        boolean vanilla = false;
//        boolean dungeon = false;
//        if(WorldConfig.wc.dict.containsKey(world.getName())) {
//            SimpleWorldConfig config = WorldConfig.wc.dict.get(world.getName());
//            vanilla = config.silk_vanilla_spawner;
//            dungeon = config.silk_dungeon_spawner;
//        }
//        
//        boolean flag = false;
//        if(tool.toString().toUpperCase().contains("PICKAXE")) {
//            if(itemInHand.containsEnchantment(Enchantment.SILK_TOUCH)) {
//                flag = true;
//            }
//        }
//        if(!flag) return;
//        
//        boolean isDungeon = false;
//        
//        if(block.getState() instanceof TileState) {
//            TileState ts = (TileState) block.getState();
//            NamespacedKey key = new NamespacedKey(Main.instance, "decry");
//            if(ts.getPersistentDataContainer().has(key, PersistentDataType.BYTE)) {
//                isDungeon = true;
//            }
//        }
//        
//        boolean bypass = false;
//        if( isDungeon && dungeon) bypass = true;
//        if(!isDungeon && vanilla) bypass = true;
//        
//        if(bypass) {
//            event.setExpToDrop(0);
//            block.getDrops().clear();
//            
//            ItemStack drop = new ItemStack(Material.SPAWNER);
//            
//            BlockState bs = block.getState();
//            CreatureSpawner cs = (CreatureSpawner) bs;
//            
//            EntityType type = cs.getSpawnedType();
//            
//            BlockStateMeta bsm = (BlockStateMeta) drop.getItemMeta();
//            
//            CreatureSpawner ncs = (CreatureSpawner) bsm.getBlockState();
//            ncs.setSpawnedType(type);
//            bsm.setBlockState(ncs);
//            
//            drop.setItemMeta(bsm);
//            
//            Location loc = block.getLocation();
//            world.dropItemNaturally(loc, drop);
//        }
//    }
    
    private final static Random RAND = new Random();
    
    @EventHandler
    public void onSpawner(SpawnerSpawnEvent event) {
        if(event.isCancelled()) return;
        try {
            Block block = event.getSpawner().getBlock();
            World world = block.getWorld();

            float vanilla = 0;
            float dungeon = 0;
            boolean vanilla_drop = true;
            boolean dungeon_drop = false;
            if(WorldConfig.wc.dict.containsKey(world.getName())) {
                SimpleWorldConfig config = WorldConfig.wc.dict.get(world.getName());
                vanilla = config.disappearance_rate_vanilla / 100;
                dungeon = config.disappearance_rate_dungeon / 100;
                vanilla_drop = config.mob_drop_in_vanilla_spawner;
                dungeon_drop = config.mob_drop_in_dungeon_spawner;
            }

            boolean isDungeon = false;

            {
                TileState ts = (TileState) block.getState();
                NamespacedKey key = new NamespacedKey(Main.instance, "decry");
                if(ts.getPersistentDataContainer().has(key, PersistentDataType.BYTE)) {
                    isDungeon = true;
                }
            }

            if(isDungeon) {
                if(RAND.nextFloat() < dungeon) {
                    //event.setCancelled(true);
                    block.setType(Material.AIR, true);
                }
                if(!dungeon_drop) {
                    Entity entity = event.getEntity();
                    NamespacedKey key = new NamespacedKey(Main.instance, "no_drop");
                    entity.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte)1);
                }
            } else {
                if(RAND.nextFloat() < vanilla) {
                    //event.setCancelled(true);
                    block.setType(Material.AIR, true);
                }
                if(!vanilla_drop) {
                    Entity entity = event.getEntity();
                    NamespacedKey key = new NamespacedKey(Main.instance, "no_drop");
                    entity.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte)1);
                }
            }
            
            {
                TileState ts = (TileState) block.getState();
                if(Lich.isLichSpawner(ts)) {
                    block.setType(Material.AIR, true);
                    
                    Location loc = block.getLocation();
                    loc.setY(loc.getBlockY() + 2);
                    
                    Lich.spawnBoss(loc);
                }
            }
        } catch(Exception ex) {
            
        }
    }
    
    @EventHandler
    public void onMobDrop(EntityDeathEvent event) {
        try {
            Entity e = event.getEntity();

            NamespacedKey key = new NamespacedKey(Main.instance, "no_drop");
            if(e.getPersistentDataContainer().has(key, PersistentDataType.BYTE)) {
                event.getDrops().clear();
                event.setDroppedExp(0);
            }
        } catch(Exception ex) {
            
        }
    }
}

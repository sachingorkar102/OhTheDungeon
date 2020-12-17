/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.util;

import java.util.List;
import java.util.logging.Level;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.generator.BlockPopulator;
import org.spigotmc.SpigotConfig;
import otd.generator.DungeonPopulator;
import otd.world.WorldDefine;
import shadow_lib.async.io.papermc.lib.PaperLib;
import shadow_manager.DungeonWorldManager;

/**
 *
 * @author
 */
public class Diagnostic {
    
    private static boolean isWorldHookReady() {
        List<World> worlds = Bukkit.getWorlds();
        for(World world : worlds) {
            if(world.getName().equalsIgnoreCase(DungeonWorldManager.WORLD_NAME)) continue;
            if(world.getName().equalsIgnoreCase(WorldDefine.WORLD_NAME)) continue;
            List<BlockPopulator> populators = world.getPopulators();
            boolean flag = false;
            for(BlockPopulator populator : populators) {
                if(populator instanceof DungeonPopulator) {
                    flag = true;
                    break;
                }
            }
            if(!flag) return false;
        }
        return true;
    }
    
    public static void diagnostic() {
        if(!isWorldHookReady()) {
            Bukkit.getLogger().log(Level.INFO, "{0}[OTD] Looks like you use /reload to load this plugin.,.. You need to restart your server, otherwise it won''t work", ChatColor.RED);
        }
    }
    
    private static boolean isJre32() {
        return System.getProperty("sun.arch.data.model").equals("32");
    }
    
    public static void main(String[] args) {
        System.out.println(System.getProperty("sun.arch.data.model"));
    }
    
    private static boolean getBoolean(String path, boolean def, String worldName) {
        SpigotConfig.config.addDefault("world-settings.default." + path, def);
        return SpigotConfig.config.getBoolean("world-settings." + worldName + "." + path, SpigotConfig.config.getBoolean("world-settings.default." + path));
    }

    
    public static boolean isSpawnerNotReady() {
        boolean b = false;
        for(World world : Bukkit.getWorlds()) {
            b = b | getBoolean("nerf-spawner-mobs", false, world.getName());
        }
        return b;
    }
    
    public static void check(Player p) {
        int count = 0;
        if(isSpawnerNotReady()) {
            count++;
            p.sendMessage(ChatColor.BLUE + "https://github.com/OhTheDungeon/OhTheDungeon/blob/main/docs/Spawner_Not_Working.md");
        }
        if(isJre32()) {
            count++;
            p.sendMessage(ChatColor.BLUE + "https://github.com/OhTheDungeon/OhTheDungeon/blob/main/docs/Use_Jre64.md");
        }
        if(!isWorldHookReady()) {
            count++;
            p.sendMessage(ChatColor.BLUE + "https://github.com/OhTheDungeon/OhTheDungeon/blob/main/docs/Fail_to_Hook_Minecraft_World_Object.md");
        }
        if(!PaperLib.isPaper()) {
            count++;
            p.sendMessage(ChatColor.BLUE + "https://github.com/OhTheDungeon/OhTheDungeon/blob/main/docs/Using_Paper.md");
        }
        
        if(count > 0) {
            p.sendMessage("OTD founds " + count + " issue(s) on your server. Check the above link for help");
        } else {
            p.sendMessage("OTD founds 0 issue on your server.");
        }
    }
}

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
import org.bukkit.generator.BlockPopulator;
import otd.generator.DungeonPopulator;
import shadow_manager.DungeonWorldManager;

/**
 *
 * @author
 */
public class WorldDiagnostic {
    public static void diagnostic() {
        List<World> worlds = Bukkit.getWorlds();
        for(World world : worlds) {
            if(world.getName().equals(DungeonWorldManager.WORLD_NAME)) continue;
            List<BlockPopulator> populators = world.getPopulators();
            boolean flag = false;
            for(BlockPopulator populator : populators) {
                if(populator instanceof DungeonPopulator) {
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                Bukkit.getLogger().log(Level.INFO, ChatColor.RED + "[OTD] Looks like you use /reload to load this plugin.,.. You need to restart your server, otherwise it won't work");
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.dungeon.dungeonmaze;

import java.util.Random;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import otd.dungeon.dungeonmaze.SmoofyDungeonPopulator.SmoofyDungeonInstance;

/**
 *
 * @author
 */
public class main extends JavaPlugin {
    @Override
    public void onEnable() {
        
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        if (command.getName().equalsIgnoreCase("mazetest")) {
            
            Bukkit.getLogger().log(Level.SEVERE, label);
            return true;
        }
        if (command.getName().equalsIgnoreCase("maze")) {
            Player p = (Player) sender;
//            OasisChunkPopulator lr = new OasisChunkPopulator();
//            lr.populate(p.getWorld(), new Random(), p.getWorld().getChunkAt(p.getLocation()));
            
            Chunk c = p.getWorld().getChunkAt(p.getLocation());
//            SmoofyDungeonPopulator.populateChunk(p.getWorld(), new Random(), c.getX(), c.getZ(), 4, 5, 1, 1);

            SmoofyDungeonInstance instance = new SmoofyDungeonInstance();
            BukkitRunnable r = new BukkitRunnable() {
                int step = 0;
                @Override
                public void run() {
                    boolean b = instance.placeDungeon(p.getWorld(), new Random(), c.getX(), c.getZ(), step, 3, 3, 1, 1);
                    step++;
                    if(b) this.cancel();
                }
            };
            r.runTaskTimer(this, 1, 1);
        }
        return true;
    }
}

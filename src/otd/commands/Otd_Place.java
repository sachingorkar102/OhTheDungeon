/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.commands;

import forge_sandbox.StructureBoundingBox;
import forge_sandbox.com.someguyssoftware.dungeons2.BukkitDungeonGenerator;
import static forge_sandbox.jaredbgreat.dldungeons.builder.Builder.commandPlaceDungeon;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import static otd.Main.instance;
import otd.dungeon.aetherlegacy.AetherBukkitGenerator;
import otd.dungeon.draylar.BattleTowerSchematics;
import otd.generator.BattleTowerGenerator;
import otd.generator.SmoofyDungeonGenerator;
import otd.util.ActualHeight;
import shadow_lib.ExceptionRepoter;
import shadow_lib.async.AsyncRoguelikeDungeon;
import shadow_lib.async.AsyncWorldEditor;
import forge_sandbox.twilightforest.TFBukkitGenerator;
import forge_sandbox.twilightforest.TFFeature;
import forge_sandbox.twilightforest.structures.darktower.StructureStartDarkTower;
import forge_sandbox.twilightforest.structures.lichtower.StructureStartLichTower;
import forge_sandbox.twilightforest.structures.minotaurmaze.StructureStartLabyrinth;
//import twilightforest.structures.minotaurmaze.StructureStartLabyrinth;

public class Otd_Place implements TabExecutor {
    
    private final Set<Player> players = new HashSet<>();
    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> res = new ArrayList<>();
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(!p.hasPermission("oh_the_dungeons.admin")) return res;
            if(args.length == 1) {
                res.add("roguelike");
                res.add("doomlike");
                res.add("battletower");
                res.add("smoofy");
                res.add("draylar");
                res.add("antman");
                res.add("aether");
                res.add("lich");
            }
        }
        return res;
    }
    @Override
    @SuppressWarnings("ConvertToStringSwitch")
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if(sender == null) return false;
        if(!(sender instanceof Player)) {
            sender.sendMessage("Player only command");
            return true;
        }
        Player p = (Player) sender;
        if(!p.hasPermission("oh_the_dungeons.admin")) {
            sender.sendMessage("You don't have permission to do that");
            return true;
        }
        String type;
        if(args.length == 0) {
            sender.sendMessage("Dungeon type is needed here");
            return true;
        }
        
        if(!players.contains(p)) {
            sender.sendMessage("Make sure you want to do that");
            sender.sendMessage("Type command again in 10s to confirm");
            players.add(p);
            
            Bukkit.getScheduler().runTaskLater(instance, new Runnable() {
                @Override
                public void run() {
                    players.remove(p);
                }
            }, 20 * 10);
            
            return true;
        }
        
        type = args[0];
        Location loc = p.getLocation();
        Chunk chunk = loc.getChunk();
        World world = loc.getWorld();
        if(type.equals("doomlike")) {
            try {
                boolean res = commandPlaceDungeon(new Random(), chunk.getX(), chunk.getZ(), world);
                if(!res) {
                    sender.sendMessage("Fail: No theme available for this chunk...");
                } else {
                    sender.sendMessage("Done");
                }
            } catch (Throwable ex) {
                sender.sendMessage("Internal Error when placing a dungeon in this chunk...");
            }
        } else if(type.equals("roguelike")) {
            Random rand = new Random();
            //IWorldEditor editor = new forge_sandbox.greymerk.roguelike.worldgen.WorldEditor(world);
            AsyncWorldEditor editor = new AsyncWorldEditor(world);
            boolean flag = AsyncRoguelikeDungeon.generateAsync(rand, editor, loc.getBlockX(), loc.getBlockZ());
            
            if(!flag) sender.sendMessage("Fail: No theme available for this chunk...");
            sender.sendMessage("Done");
        } else if(type.equals("battletower")) {
            BattleTowerGenerator g = new BattleTowerGenerator();
            g.generateDungeon(world, new Random(), chunk);
            sender.sendMessage("Done");
        } else if(type.equals("smoofy")) {
            SmoofyDungeonGenerator.halfAsyncGenerate(world, chunk, new Random());
            sender.sendMessage("Done");
        } else if(type.equals("draylar")) {
            Location location = p.getLocation();
            BattleTowerSchematics.place(world, new Random(), location.getBlockX(), location.getBlockZ());
            sender.sendMessage("Done");
        } else if(type.equals("antman")) {
            Location location = p.getLocation();
            location = location.getWorld().getHighestBlockAt(location).getLocation();
            location = ActualHeight.getHeight(location);
            
            try {
                BukkitDungeonGenerator.generate(world, location, new Random());
                sender.sendMessage("Done");
            } catch(Exception ex) {
                Bukkit.getLogger().log(Level.SEVERE, ExceptionRepoter.exceptionToString(ex));
            }
        } else if(type.equals("aether")) {
            Location location = p.getLocation();
            AetherBukkitGenerator.generate(world, new Random(), location.getBlockX(), location.getBlockZ());
            sender.sendMessage("Done");
        } else if(type.equals("lich")) {
            Location location = p.getLocation();
            location = location.getWorld().getHighestBlockAt(location).getLocation();
            location = ActualHeight.getHeight(location);
            TFBukkitGenerator.generateLichTower(world, location, new Random());
        } else return false;
        return true;
    }
}

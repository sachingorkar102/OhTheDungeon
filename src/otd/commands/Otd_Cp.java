/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.commands;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import zhehe.util.config.WorldConfig;

public class Otd_Cp implements TabExecutor {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> res = new ArrayList<>();
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(!p.hasPermission("oh_the_dungeons.admin")) return res;
        }
        if(args.length == 1 || args.length == 2) {
            for(World w : Bukkit.getWorlds()) {
                res.add(w.getName());
            }
        }
        return res;
    }
    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(!p.hasPermission("oh_the_dungeons.admin")) {
                sender.sendMessage("You don't have permission to do that");
                return true;
            }
        }
        
        if(args.length != 2) return false;
        if(!WorldConfig.wc.dict.containsKey(args[0])) {
            sender.sendMessage("Invalid source world");
            return true;
        }
        
        WorldConfig.wc.dict.put(args[1], WorldConfig.wc.dict.get(args[0]));
        WorldConfig.save();
        sender.sendMessage("Done");
        
        return true;
    }
}

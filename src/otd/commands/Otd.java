/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.commands;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import otd.util.gui.WorldManager;

public class Otd implements TabExecutor {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return new ArrayList<>();
    }
    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Player only command");
            return true;
        }
        Player p = (Player) sender;
        if(!p.hasPermission("oh_the_dungeons.admin")) {
            sender.sendMessage("You don't have permission to do that");
            return true;
        }
        WorldManager wm = new WorldManager();
        wm.openInventory(p);
        return true;
    }
}

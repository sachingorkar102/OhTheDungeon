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
import otd.util.config.WorldConfig;
import otd.util.gui.dungeon_plot.UserTeleport;
import otd.world.DungeonTask;
import otd.world.DungeonWorld;
import zhehe.util.I18n;

/**
 *
 * @author shadow
 */
public class Otd_Tp implements TabExecutor {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return new ArrayList<>();
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
        if(!p.hasPermission("oh_the_dungeons.teleport")) {
            sender.sendMessage(I18n.instance.No_Permission);
            return true;
        }
        
        if(
                DungeonWorld.world == null ||
                WorldConfig.wc.dungeon_world.finished == false ||
                DungeonTask.isGenerating()
                ) {
            sender.sendMessage(I18n.instance.Dungeon_Plot_Not_Ready);
            return true;
        }
        
        if(args.length != 0) return false;
        
        UserTeleport ut = new UserTeleport();
        ut.openInventory(p);
        
        return true;
    }
}

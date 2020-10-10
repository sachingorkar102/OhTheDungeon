/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zhehe.util;

import org.bukkit.Location;

/**
 *
 * @author
 */
public class BlockPos {
    Location loc;
    boolean noworld;
    public BlockPos(Location loc) {
        this.loc = loc;
        noworld = false;
    }
    public BlockPos(int x, int y, int z) {
        this.loc = new Location(null, x, y, z);
        noworld = true;
    }
    public int getX() {
        return loc.getBlockX();
    }
    public int getY() {
        return loc.getBlockY();
    }
    public int getZ() {
        return loc.getBlockZ();
    }
}

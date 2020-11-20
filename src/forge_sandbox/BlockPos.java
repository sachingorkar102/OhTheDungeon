/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forge_sandbox;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.block.BlockFace;

/**
 *
 * @author
 */
public class BlockPos extends Vec3i {
    public int x, y, z;
    public BlockPos(int x, int y, int z) {
        super(x, y, z);
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public BlockPos(Vec3d vec) {
        super((int) vec.x, (int) vec.y, (int) vec.z);
        this.x = (int) vec.x;
        this.y = (int) vec.y;
        this.z = (int) vec.z;
    }
    
    @Override
    public int getX() {
        return this.x;
    }
    
    @Override
    public int getY() {
        return this.y;
    }
    
    @Override
    public int getZ() {
        return this.z;
    }
    
    public BlockPos add(int x, int y, int z) {
        return new BlockPos(this.x + x, this.y + y, this.z + z);
    }
    public static List<BlockPos> getAllInBox(int x0, int y0, int z0, int x1, int y1, int z1) {
        List<BlockPos> list = new ArrayList<>();
        for(int x = x0; x < x1; x++)
            for(int y = y0; y < y1; y++)
                for(int z = z0; z < z1; z++)
                    list.add(new BlockPos(x, y, z));
        return list;
    }
    public BlockPos north() {
        return offset(BlockFace.NORTH, 1);
    }
    public BlockPos south() {
        return offset(BlockFace.SOUTH, 1);
    }
    public BlockPos east() {
        return offset(BlockFace.EAST, 1);
    }
    public BlockPos west() {
        return offset(BlockFace.WEST, 1);
    }
    public BlockPos offset(BlockFace facing, int n) {
        return new BlockPos(this.x + facing.getModX() * n, this.y + facing.getModY() * n, this.z + facing.getModZ() * n);
    }
    public BlockPos down() {
        return offset(BlockFace.DOWN, 1);
    }
}

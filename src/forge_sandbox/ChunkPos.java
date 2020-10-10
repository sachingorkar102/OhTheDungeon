/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forge_sandbox;

public class ChunkPos
{
    public final int x;
    public final int z;

    public ChunkPos(int x, int z)
    {
        this.x = x;
        this.z = z;
    }

    public ChunkPos(BlockPos pos)
    {
        this.x = pos.getX() >> 4;
        this.z = pos.getZ() >> 4;
    }
}
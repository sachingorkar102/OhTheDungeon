/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.world.task;

/**
 *
 * @author shadow
 */
public abstract class DungeonWorldTask {
    
    public int[] chunkPos;
    
    public DungeonWorldTask(int x, int z) {
        chunkPos = new int[] {x, z};
    }
    public int getDelay() {
        return 1;
    }
    public int[] getChunkPos() {
        return chunkPos;
    }
}

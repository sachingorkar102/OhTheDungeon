/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shadow_lib.async.later.twilightforest;

import forge_sandbox.greymerk.roguelike.worldgen.Coord;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import shadow_lib.async.AsyncWorldEditor;
import shadow_lib.async.later.roguelike.Later;
import forge_sandbox.twilightforest.TFBoss;
import forge_sandbox.twilightforest.structures.lichtower.boss.Lich;

/**
 *
 * @author shadow
 */
public class SpawnerBoss_Later extends Later {
    private AsyncWorldEditor world;
    private final Coord loc;
    private final TFBoss boss;
    
    public SpawnerBoss_Later(final AsyncWorldEditor world, final Coord loc, final TFBoss boss) {
        this.world = world;
        this.loc = loc;
        this.boss = boss;
    }
    
    public static boolean generate_later(final AsyncWorldEditor world, final Coord loc, final TFBoss boss) {
        SpawnerBoss_Later later = new SpawnerBoss_Later(world, loc, boss);
        world.addLater(later);
        
        return true;
    }
    
    @Override
    public void doSomething() {
        
    }
    
    @Override
    public void doSomethingInChunk(Chunk c) {
        int x = loc.getX() % 16;
        int y = loc.getY();
        int z = loc.getZ() % 16;
        if(x < 0) x = x + 16;
        if(z < 0) z = z + 16;            

        Block block = c.getBlock(x, y, z);
        
        if(boss == TFBoss.Lich) {
            Lich.createSpawner(block);
        }
        this.world = null;
    }

    @Override
    public Coord getPos() {
        return this.loc;
    }
}

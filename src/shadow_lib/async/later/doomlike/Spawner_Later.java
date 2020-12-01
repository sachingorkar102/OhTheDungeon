/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shadow_lib.async.later.doomlike;

import forge_sandbox.greymerk.roguelike.worldgen.Coord;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import otd.Main;
import shadow_lib.api.SpawnerDecryAPI;
import shadow_lib.async.AsyncWorldEditor;
import shadow_lib.async.later.roguelike.Later;

/**
 *
 * @author
 */
public class Spawner_Later extends Later {
    private Coord coords;
    private AsyncWorldEditor world;
    private EntityType mob;
    
    private static boolean generate(Chunk chunk, Coord pos, EntityType mob) {

        int x = pos.getX() % 16;
        int y = pos.getY();
        int z = pos.getZ() % 16;
        if(x < 0) x = x + 16;
        if(z < 0) z = z + 16;            
        Block b = chunk.getBlock(x, y, z);
        CreatureSpawner  sp = (CreatureSpawner) b.getState();
        sp.setSpawnedType(mob);
        sp.update();
        SpawnerDecryAPI.setSpawnerDecry(b, Main.instance);
        
        return true;
    }
    
    public static boolean generate_later(AsyncWorldEditor world, Coord pos, EntityType mob) {
        Spawner_Later later = new Spawner_Later();
        later.world = world;
        later.coords = new Coord(pos.getX(), pos.getY(), pos.getZ());
        later.mob = mob;
        
        world.addLater(later);
        
        return true;
    }

    @Override
    public Coord getPos() {
        return this.coords;
    }

    @Override
    public void doSomething() {
    }

    @Override
    public void doSomethingInChunk(Chunk c) {
        Spawner_Later.generate(c, coords, mob);
        this.world = null;
    }
}


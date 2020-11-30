/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shadow_lib.async.later.twilightforest;

import forge_sandbox.greymerk.roguelike.worldgen.Coord;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import otd.Main;
import shadow_lib.api.SpawnerDecryAPI;
import shadow_lib.async.AsyncWorldEditor;
import shadow_lib.async.later.roguelike.Later;

/**
 *
 * @author shadow
 */
public class Spawner_Later extends Later {
    private AsyncWorldEditor world;
    private final Coord loc;
    private final EntityType type;
    
    public Spawner_Later(AsyncWorldEditor world, Coord loc, EntityType type) {
        this.world = world;
        this.loc = loc;
        this.type = type;
    }
    
    public static boolean generate_later(final AsyncWorldEditor world, final Coord loc, final EntityType type) {
        Spawner_Later later = new Spawner_Later(world, loc, type);
        world.addLater(later);
        
        return true;
    }

    @Override
    public Coord getPos() {
        return this.loc;
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
        block.setType(Material.SPAWNER);
        CreatureSpawner tileentitymobspawner = ((CreatureSpawner)block.getState());
        tileentitymobspawner.setSpawnedType(type);
        tileentitymobspawner.update();
        SpawnerDecryAPI.setSpawnerDecry(block, Main.instance);
        
        this.world = null;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shadow_lib.async.later.dungeons2;

import forge_sandbox.com.someguyssoftware.gottschcore.positional.ICoords;
import forge_sandbox.greymerk.roguelike.worldgen.Coord;
import java.util.Random;
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
 * @author
 */
public class Spawner_Later extends Later {
    private Coord coords;
    private AsyncWorldEditor world;
    private Random random;
    private String mob;
    
    private static boolean generate(final Chunk chunk, final Random random, Coord pos, String mob) {

        int x = pos.getX() % 16;
        int y = pos.getY();
        int z = pos.getZ() % 16;
        if(x < 0) x = x + 16;
        if(z < 0) z = z + 16;            

        Block block = chunk.getBlock(x, y, z);
        block.setType(Material.SPAWNER);
        CreatureSpawner tileentitymobspawner = ((CreatureSpawner)block.getState());
        tileentitymobspawner.setSpawnedType(EntityType.valueOf(mob.toUpperCase()));
        tileentitymobspawner.update();
        SpawnerDecryAPI.setSpawnerDecry(block, Main.instance);
        
        return true;
    }
    
    public static boolean generate_later(final AsyncWorldEditor world, final Random random, ICoords coords, String mob) {
        Spawner_Later later = new Spawner_Later();
        later.world = world;
        later.random = random;
        later.coords = new Coord(coords.getX(), coords.getY(), coords.getZ());
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
        Spawner_Later.generate(c, random, coords, mob);
    }
}

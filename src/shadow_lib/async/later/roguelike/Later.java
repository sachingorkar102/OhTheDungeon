/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shadow_lib.async.later.roguelike;

import forge_sandbox.greymerk.roguelike.worldgen.Coord;
import org.bukkit.Chunk;

/**
 *
 * @author
 */
public abstract class Later {
    
    public abstract Coord getPos();
    
    public abstract void doSomething();
    
    public abstract void doSomethingInChunk(Chunk c);
}

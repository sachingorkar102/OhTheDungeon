/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shadow_lib.async.later;

import forge_sandbox.greymerk.roguelike.worldgen.Coord;
import forge_sandbox.greymerk.roguelike.worldgen.IWorldEditor;
import forge_sandbox.greymerk.roguelike.worldgen.spawners.Spawnable;
import java.util.Random;
import org.bukkit.Chunk;

/**
 *
 * @author
 */
public class Spawnable_Later extends Later {
    
    private Coord pos;
    private IWorldEditor editor;
    private Random rand;
    private Coord cursor;
    private int level;
    private Spawnable spawnable;
    
    @Override
    public Coord getPos() {
        return pos;
    }
    
    public Spawnable_Later(Spawnable spawnable, Coord pos, IWorldEditor editor, Random rand, Coord cursor, int level) {
        this.spawnable = spawnable;
        this.pos = pos;
        this.editor = editor;
        this.rand = rand;
        this.cursor = cursor;
        this.level = level;
    }
    
    @Override
    public void doSomething() {
        spawnable.generate_later(pos, editor, rand, cursor, level);
    }

    @Override
    public void doSomethingInChunk(Chunk c) {
        spawnable.generate_later_chunk(c, pos, editor, rand, cursor, level);
    }
}

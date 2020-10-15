/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shadow_lib.async.later.roguelike;

import forge_sandbox.greymerk.roguelike.treasure.TreasureChest;
import forge_sandbox.greymerk.roguelike.worldgen.Coord;
import forge_sandbox.greymerk.roguelike.worldgen.IWorldEditor;
import org.bukkit.Chunk;

/**
 *
 * @author
 */
public class Chest_Generate_Later extends Later {
    
    private TreasureChest tc;
    private IWorldEditor editor;
    private Coord pos;
    
    @Override
    public Coord getPos() {
        return pos;
    }
    
    public Chest_Generate_Later(TreasureChest tc, IWorldEditor editor, Coord pos) {
        this.tc = tc;
        this.editor = editor;
        this.pos = pos;
    }
    
    @Override
    public void doSomething() {
        tc.generate_later(editor, pos);
    }
    
    @Override
    public void doSomethingInChunk(Chunk c) {
        tc.generate_later_chunk(c, pos);
    }
}

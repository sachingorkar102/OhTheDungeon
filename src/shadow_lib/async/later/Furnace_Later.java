/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shadow_lib.async.later;

import forge_sandbox.greymerk.roguelike.worldgen.Coord;
import forge_sandbox.greymerk.roguelike.worldgen.IWorldEditor;
import forge_sandbox.greymerk.roguelike.worldgen.blocks.Furnace;
import org.bukkit.Chunk;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author
 */
public class Furnace_Later extends Later {
    
    private IWorldEditor editor;
    private ItemStack fuel;
    private Coord pos;
    
    @Override
    public Coord getPos() {
        return pos;
    }
    
    public Furnace_Later(IWorldEditor editor, ItemStack fuel, Coord pos) {
        this.editor = editor;
        this.fuel = fuel;
        this.pos = pos;
    }
    
    @Override
    public void doSomething() {
        Furnace.generate_later(editor, fuel, pos);
    }

    @Override
    public void doSomethingInChunk(Chunk c) {
        Furnace.generate_later_chunk(c, fuel, pos);
    }
}

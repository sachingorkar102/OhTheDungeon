/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shadow_lib.async.later.roguelike;

import forge_sandbox.greymerk.roguelike.worldgen.Coord;
import forge_sandbox.greymerk.roguelike.worldgen.IWorldEditor;
import forge_sandbox.greymerk.roguelike.worldgen.redstone.Dispenser;
import org.bukkit.Chunk;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author
 */
public class Dispenser_Later extends Later {
    
    private IWorldEditor editor;
    private Coord pos;
    private int slot;
    private ItemStack item;
    
    @Override
    public Coord getPos() {
        return pos;
    }
    
    public Dispenser_Later(IWorldEditor editor, Coord pos, int slot, ItemStack item) {
        this.editor = editor;
        this.pos = pos;
        this.slot = slot;
        this.item = item;
    }
    
    @Override
    public void doSomething() {
        Dispenser.add_later(editor, pos, slot, item);
    }

    @Override
    public void doSomethingInChunk(Chunk c) {
        Dispenser.add_later_chunk(c, pos, slot, item);
    }
}

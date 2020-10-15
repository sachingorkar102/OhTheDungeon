/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shadow_lib.async.later.roguelike;

import forge_sandbox.greymerk.roguelike.worldgen.Coord;
import forge_sandbox.greymerk.roguelike.worldgen.IWorldEditor;
import forge_sandbox.greymerk.roguelike.worldgen.redstone.Dropper;
import org.bukkit.Chunk;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author
 */
public class Dropper_Later extends Later {
    
    private Dropper dropper;
    private IWorldEditor editor;
    private Coord pos;
    private int slot;
    private ItemStack item;
    
    @Override
    public Coord getPos() {
        return pos;
    }
    
    public Dropper_Later(Dropper dropper, IWorldEditor editor, Coord pos, int slot, ItemStack item) {
        this.dropper = dropper;
        this.editor = editor;
        this.pos = pos;
        this.slot = slot;
        this.item = item;
    }
    
    @Override
    public void doSomething() {
        dropper.add_later(editor, pos, slot, item);
    }

    @Override
    public void doSomethingInChunk(Chunk c) {
        dropper.add_later_chunk(c, pos, slot, item);
    }
}

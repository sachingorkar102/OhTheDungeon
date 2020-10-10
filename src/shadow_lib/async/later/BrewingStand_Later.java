/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shadow_lib.async.later;

import forge_sandbox.greymerk.roguelike.worldgen.Coord;
import forge_sandbox.greymerk.roguelike.worldgen.IWorldEditor;
import forge_sandbox.greymerk.roguelike.worldgen.blocks.BrewingStand;
import org.bukkit.Chunk;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author
 */
public class BrewingStand_Later extends Later {
    
    private IWorldEditor editor;
    private Coord pos;
    private BrewingStand slot;
    private ItemStack item;
    
    @Override
    public Coord getPos() {
        return pos;
    }
    
    public BrewingStand_Later(IWorldEditor editor, Coord pos, BrewingStand slot, ItemStack item) {
        this.editor = editor;
        this.pos = pos;
        this.slot = slot;
        this.item = item;
    }
    
    @Override
    public void doSomething() {
        BrewingStand.add_later(editor, pos, slot, item);
    }

    @Override
    public void doSomethingInChunk(Chunk c) {
        BrewingStand.add_later_chunk(c, pos, slot, item);
    }
}

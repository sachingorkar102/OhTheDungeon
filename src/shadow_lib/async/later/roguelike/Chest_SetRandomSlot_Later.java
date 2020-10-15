/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shadow_lib.async.later.roguelike;

import forge_sandbox.greymerk.roguelike.treasure.TreasureChest;
import forge_sandbox.greymerk.roguelike.worldgen.Coord;
import org.bukkit.Chunk;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author
 */
public class Chest_SetRandomSlot_Later extends Later {
    
    private TreasureChest tc;
    private ItemStack item;
    private Coord pos;
    
    @Override
    public Coord getPos() {
        return pos;
    }
    
    public Chest_SetRandomSlot_Later(TreasureChest tc, ItemStack item, Coord pos) {
        this.tc = tc;
        this.item = item;
        this.pos = pos;
    }
    
    @Override
    public void doSomething() {
        this.tc.setRandomEmptySlot_later(item);
    }

    @Override
    public void doSomethingInChunk(Chunk c) {
        this.tc.setRandomEmptySlot_later(item);
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.world.task;

import otd.world.DungeonType;

/**
 *
 * @author shadow
 */
public class DungeonPlaceTask extends DungeonWorldTask {
    
    public final DungeonType dungeon;
    
    public DungeonPlaceTask(int x, int z, DungeonType dungeon) {
        super(x, z);
        this.dungeon = dungeon;
    }
    
    @Override
    public int getDelay() {
        if(dungeon == DungeonType.Roguelike) return 200;
        return 100;
    }
}

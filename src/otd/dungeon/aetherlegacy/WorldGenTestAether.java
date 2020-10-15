/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.dungeon.aetherlegacy;

import java.util.Random;
import org.bukkit.World;

/**
 *
 * @author
 */
public class WorldGenTestAether {

    public static void generate(World world, Random random, int x, int z) {
        int firstStaircaseZ, secondStaircaseZ, finalStaircaseZ;
        int xTendency, zTendency;
        ComponentSilverDungeon dungeon = new ComponentSilverDungeon();
        dungeon.setBaseStructureOffset(x, 180, z);
        dungeon.setStructureOffset(0, 0, 0);
        firstStaircaseZ = random.nextInt(3);
        secondStaircaseZ = random.nextInt(3);
        finalStaircaseZ = random.nextInt(3);

        xTendency = random.nextInt(3) - 1;
        zTendency = random.nextInt(3) - 1;

        dungeon.setStaircasePosition(firstStaircaseZ, secondStaircaseZ, finalStaircaseZ);
        dungeon.setCloudTendencies(xTendency, zTendency);
        
        dungeon.addComponentParts(world, random);
    }
}

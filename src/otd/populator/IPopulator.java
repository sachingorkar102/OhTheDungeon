/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.populator;

import java.util.Random;
import java.util.Set;
import org.bukkit.Chunk;
import org.bukkit.World;

/**
 *
 * @author
 */
public interface IPopulator {
    public abstract boolean generateDungeon(World world, Random random, Chunk chunk);
    public abstract Set<String> getBiomeExclusions(World world);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.world;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;

/**
 *
 * @author shadow
 */
public class DungeonWorldChunkGenerator extends ChunkGenerator {
    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunk = Bukkit.createChunkData(world);
        
        String chunkPos = chunkX + "," + chunkZ;
        if(ChunkList.chunks.containsKey(chunkPos)) {
            for(int i = 0; i < 16; i++) {
                for(int j = 0; j < 16; j++) {
                    biome.setBiome(i, j, Biome.PLAINS);
                    chunk.setBlock(i, 0, j, Material.BEDROCK);
                    for(int k = 1; k <= 60; k++) {
                        chunk.setBlock(i, k, j, Material.STONE);
                    }
                    chunk.setBlock(i, 61, j, Material.DIRT);
                    chunk.setBlock(i, 62, j, Material.DIRT);
                    chunk.setBlock(i, 63, j, Material.GRASS_BLOCK);
                }
            }
        } else {
                for(int i = 0; i < 16; i++)
                    for(int j = 0; j < 16; j++)
                        biome.setBiome(i, j, Biome.PLAINS);
        }
        
        return chunk;
    }
}

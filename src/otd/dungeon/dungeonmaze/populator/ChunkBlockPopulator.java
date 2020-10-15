package otd.dungeon.dungeonmaze.populator;

import java.util.HashSet;
import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public abstract class ChunkBlockPopulator extends BlockPopulator {
    
    private boolean const_room = false;
    public boolean getConstRoom() {
        return const_room;
    }

    /**
     * The last accessed dungeon chunk.
     */

    @Override
    public void populate(World world, Random rand, Chunk chunk) {
        populate(world, rand, chunk, false);
    }

	public void populate(World world, Random rand, Chunk chunk, boolean force) {
        // TODO: Re-enable queued populators, and properly implement them for the next release
//        // Queue populators when not force
//        if(!force && !(chunk.getX() == 0 && chunk.getZ() == 0)) {
//            DungeonMaze.instance.queuedPopulators.add(new DelayedPopulator(this, world, rand, chunk));
//            return;
//        }

        // Check whether this this chunk should be populated based on it's chance
        if(rand.nextFloat() >= getChunkChance())
            return;

        // Iterate through this chunk
        final int iterations = getChunkIterations();
        final int iterationsMax = getChunkIterationsMax();
        int iterationCount = 0;
        for(int i = 0; i < iterations; i++) {
            // Make sure we didn't iterate too many times
            if(iterationCount >= iterationsMax && iterationsMax >= 0)
                break;

            // Check whether this this chunk should be populated in the current iteration based on it's iteration chance
            if(rand.nextFloat() >= getChunkIterationsChance())
                continue;

            // Increase the iterations counter
            iterationCount++;

            // Construct the MazeBlockPopulatorArgs to use the the populateMaze method
            ChunkBlockPopulatorArgs args = new ChunkBlockPopulatorArgs(world, rand, chunk, new HashSet<>());

            // Populate the maze
            populateChunk(args);
        }
    }
	
	/**
	 * Population method.
     *
	 * @param args Populator arguments.
	 */
	public abstract void populateChunk(ChunkBlockPopulatorArgs args);

    /**
     * Get the chunk population chance. This value is between 0.0 and 1.0.
     *
     * @return The population chance of the chunk.
     */
    public float getChunkChance() {
        return 1.0f;
    }

    /**
     * Get the number of times to iterate through each chunk.
     *
     * @return The number of iterations.
     */
    public int getChunkIterations() {
        return 1;
    }

    /**
     * Get the chunk population chance for each iteration. This value is between 0.0 and 1.0.
     *
     * @return The population chance of the chunk.
     */
    public float getChunkIterationsChance() {
        return 1.0f;
    }

    /**
     * Get the maximum number of times to iterate based on the chance and iteration count.
     *
     * @return The maximum number of times to iterate. Return a negative number of ignore the maximum.
     */
    public int getChunkIterationsMax() {
        return -1;
    }
}

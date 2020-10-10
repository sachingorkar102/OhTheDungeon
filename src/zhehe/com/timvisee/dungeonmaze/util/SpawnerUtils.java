package zhehe.com.timvisee.dungeonmaze.util;

import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import otd.Main;
import shadow_lib.api.SpawnerDecryAPI;
import zhehe.util.config.WorldConfig;

@SuppressWarnings("UnusedDeclaration")
public class SpawnerUtils {

	/**
	 * Get the creature spawner state instance from a block
	 * @param b Block to get the creature spawner state instance from (Block must be a spawner)
	 * @return Creature spawner state instance, or null if failed
	 */
	public static CreatureSpawner getSpawner(Block b) {
		// Make sure the block isn't null
		if(b == null)
			return null;
		
		// Cast the block to a creature spawner state instance
		try {
			BlockState state = b.getState();
			if(state instanceof CreatureSpawner)
				// Return the creature spawner state instance
				return (CreatureSpawner) state;
		
		} catch(Exception ignored) { }
		
		return null;
	}

	/**
	 * Check whether a block can be casted to a creature spawner state instance or not
	 * @param b The block to check
	 * @return True if the block could be casted to a creature spawner state instance
	 */
	public static boolean isSpawner(Block b) {
		return (getSpawner(b) != null);
	}

	/**
	 * Create a creature spawner block at the given block.
	 * The spawner will then be configured to spawn the given entity type.
	 *
	 * @param block The block to change to a creature spawner.
	 * @param type The entity type to spawn.
	 *
	 * @return True on success, false on failure.
	 */
	public static boolean createSpawner(Block block, EntityType type) {
        // Change the block to a spawner
        block.setType(Material.SPAWNER);

        // Set the spawning type
        boolean b = setSpawnerType(block, type);
        SpawnerDecryAPI.setSpawnerDecry(block, Main.instance);
        return b;
	}

	/**
	 * Change the spawning entity type of the given spawner block.
	 *
	 * The given block must be a spawner, or else the change will fail.
	 *
	 * @param spawnerBlock The spawner block to update.
	 * @param type The entity type to change the spawner to.
	 *
	 * @return True on success, false on failure.
	 */
	public static boolean setSpawnerType(Block spawnerBlock, EntityType type) {
		// Get the block, make sure it's valid
		CreatureSpawner spawner = getSpawner(spawnerBlock);
		if(spawner == null)
			return false;

		// Update the type
		return setSpawnerType(spawner, type);
	}

	/**
	 * Change the spawning entity type of the given spawner.
	 *
	 * @param spawner The spawner to update.
	 * @param type The entity type to change the spawner too.
	 *
	 * @return True on success, false on failure.
	 */
	public static boolean setSpawnerType(CreatureSpawner spawner, EntityType type) {
		try {
			// Set the spawner type
			spawner.setSpawnedType(type);

			// Force update, and don't update physics
			return spawner.update(true, false);

		} catch(Exception e) {
			// Show a proper error message
			Bukkit.getLogger().log(Level.SEVERE, "Failed to set spawner type to {0}, ignoring...", type.name());
			return false;
		}
	}
        
        public static boolean isSkeletonAllowed(World world) {
            String world_name = world.getName();
            if(!WorldConfig.wc.dict.containsKey(world_name)) return true;
            
            return WorldConfig.wc.dict.get(world_name).smoofydungeon.Skeleton;
        }
        
        public static boolean isSpiderAllowed(World world) {
            String world_name = world.getName();
            if(!WorldConfig.wc.dict.containsKey(world_name)) return true;
            
            return WorldConfig.wc.dict.get(world_name).smoofydungeon.Spider;
        }
        
        public static boolean isBlazeAllowed(World world) {
            String world_name = world.getName();
            if(!WorldConfig.wc.dict.containsKey(world_name)) return true;
            
            return WorldConfig.wc.dict.get(world_name).smoofydungeon.Blaze;
        }
        
        public static boolean isZombieAllowed(World world) {
            String world_name = world.getName();
            if(!WorldConfig.wc.dict.containsKey(world_name)) return true;
            
            return WorldConfig.wc.dict.get(world_name).smoofydungeon.Zombie;
        }
        
        public static boolean isPigZombieAllowed(World world) {
            String world_name = world.getName();
            if(!WorldConfig.wc.dict.containsKey(world_name)) return true;
            
            return WorldConfig.wc.dict.get(world_name).smoofydungeon.PigZombie;
        }
        
        public static boolean isGhastAllowed(World world) {
            String world_name = world.getName();
            if(!WorldConfig.wc.dict.containsKey(world_name)) return true;
            
            return WorldConfig.wc.dict.get(world_name).smoofydungeon.Ghast;
        }
        
        public static boolean isCreeperAllowed(World world) {
            String world_name = world.getName();
            if(!WorldConfig.wc.dict.containsKey(world_name)) return true;
            
            return WorldConfig.wc.dict.get(world_name).smoofydungeon.Creeper;
        }
}

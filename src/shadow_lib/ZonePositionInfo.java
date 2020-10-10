/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shadow_lib;

import forge_sandbox.greymerk.roguelike.worldgen.IPositionInfo;
import org.bukkit.block.Biome;

/**
 *
 * @author
 */
public class ZonePositionInfo implements IPositionInfo {
    
        public Biome biome;
        public ZonePositionInfo(Biome b) {
            this.biome = b;
        }
	
	@Override
	public String getDimension() {
		return ZoneWorld.WORLD_NAME;
	}

	@Override
	public Biome getBiome() {
                return this.biome;
	}
}

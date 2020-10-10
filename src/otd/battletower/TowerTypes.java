/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.battletower;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;

public enum TowerTypes
{
    Null("null",Material.AIR, Material.AIR, Material.AIR, Material.AIR),
    CobbleStone("cobblestone",Material.COBBLESTONE, Material.TORCH, BlockConst.DOUBLE_STONE_SLAB, Material.STONE_STAIRS),
    CobbleStoneMossy("cobblestonemossy",Material.MOSSY_COBBLESTONE, Material.TORCH, BlockConst.DOUBLE_STONE_SLAB, Material.STONE_STAIRS),
    SandStone("sandstone",Material.SANDSTONE, Material.TORCH, BlockConst.DOUBLE_SANDSTONE_SLAB, Material.SANDSTONE_STAIRS),
    Ice("ice",Material.ICE, Material.AIR /* Blocks.GLOWSTONE */, Material.CLAY, Material.OAK_STAIRS), // since when does glowstone melt ice
    SmoothStone("smoothstone",Material.STONE, Material.TORCH, BlockConst.PETRIFIED_OAK_SLAB, Material.STONE_STAIRS),
    Netherrack("netherrack",Material.NETHERRACK, Material.GLOWSTONE, Material.SOUL_SAND, Material.NETHER_BRICK_STAIRS),
    Jungle("jungle",Material.MOSSY_COBBLESTONE, Material.COBWEB, Material.DIRT, Material.JUNGLE_STAIRS);

    private final Material wallBlock;
    private final Material lightBlock;
    private final BlockData floorBlock;
    private final Material stairBlock;
    private final String typeName;
    
    TowerTypes(String t, Material a, Material b, Material c, Material e)
    {
        this.wallBlock = a;
        this.lightBlock = b;
        this.floorBlock = Bukkit.createBlockData(c);
        this.stairBlock = e;
        this.typeName = t;
    }

    TowerTypes(String t, Material a, Material b, BlockData c, Material e)
    {
        this.wallBlock = a;
        this.lightBlock = b;
        this.floorBlock = c;
        this.stairBlock = e;
        this.typeName = t;
    }

    Material getWallBlockMaterial()
    {
        return wallBlock;
    }

    Material getLightBlockMaterial()
    {
        return lightBlock;
    }

    BlockData getFloorBlockData()
    {
        return floorBlock;
    }

    Material getStairBlockMaterial()
    {
        return stairBlock;
    }

    public String getName()
    {
        return this.typeName;
    }
}


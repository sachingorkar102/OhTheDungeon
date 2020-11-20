package forge_sandbox.greymerk.roguelike.treasure.loot;

import forge_sandbox.greymerk.roguelike.util.DyeColor;
import java.util.Random;
import org.bukkit.Material;

import org.bukkit.inventory.ItemStack;
import otd.Main;
import otd.MultiVersion;

public class Firework {
    
    private static class Firework114 {
        public ItemStack get(Random rand, int stackSize) {
            ItemStack rocket = new ItemStack(Material.FIREWORK_ROCKET, stackSize);
            net.minecraft.server.v1_14_R1.NBTTagCompound tag = new net.minecraft.server.v1_14_R1.NBTTagCompound();
            net.minecraft.server.v1_14_R1.NBTTagCompound fireworks = new net.minecraft.server.v1_14_R1.NBTTagCompound();

            fireworks.setByte("Flight", (byte) (rand.nextInt(3) + 1));

            net.minecraft.server.v1_14_R1.NBTTagList explosion = new net.minecraft.server.v1_14_R1.NBTTagList();

            net.minecraft.server.v1_14_R1.NBTTagCompound properties = new net.minecraft.server.v1_14_R1.NBTTagCompound();
            properties.setByte("Flicker", (byte) (rand.nextBoolean() ? 1 : 0));
            properties.setByte("Trail", (byte) (rand.nextBoolean() ? 1 : 0));
            properties.setByte("Type", (byte) (rand.nextInt(5)));

            int size = rand.nextInt(4) + 1;
            int[] colorArr = new int[size];
            for(int i = 0; i < size; ++i){
                colorArr[i] = DyeColor.HSLToColor(rand.nextFloat(), (float)1.0, (float)0.5);
            }

            net.minecraft.server.v1_14_R1.NBTTagIntArray colors = new net.minecraft.server.v1_14_R1.NBTTagIntArray(colorArr);
            properties.set("Colors", colors);

            explosion.add(properties);
            fireworks.set("Explosions", explosion);
            tag.set("Fireworks", fireworks);

            net.minecraft.server.v1_14_R1.ItemStack tmp = org.bukkit.craftbukkit.v1_14_R1.inventory.CraftItemStack.asNMSCopy(rocket);
            tmp.setTag(tag);
            rocket = org.bukkit.craftbukkit.v1_14_R1.inventory.CraftItemStack.asBukkitCopy(tmp);
            
            return rocket;
        }
    }
    
    private static class Firework115 {
        public ItemStack get(Random rand, int stackSize) {
            ItemStack rocket = new ItemStack(Material.FIREWORK_ROCKET, stackSize);
            net.minecraft.server.v1_15_R1.NBTTagCompound tag = new net.minecraft.server.v1_15_R1.NBTTagCompound();
            net.minecraft.server.v1_15_R1.NBTTagCompound fireworks = new net.minecraft.server.v1_15_R1.NBTTagCompound();

            fireworks.setByte("Flight", (byte) (rand.nextInt(3) + 1));

            net.minecraft.server.v1_15_R1.NBTTagList explosion = new net.minecraft.server.v1_15_R1.NBTTagList();

            net.minecraft.server.v1_15_R1.NBTTagCompound properties = new net.minecraft.server.v1_15_R1.NBTTagCompound();
            properties.setByte("Flicker", (byte) (rand.nextBoolean() ? 1 : 0));
            properties.setByte("Trail", (byte) (rand.nextBoolean() ? 1 : 0));
            properties.setByte("Type", (byte) (rand.nextInt(5)));

            int size = rand.nextInt(4) + 1;
            int[] colorArr = new int[size];
            for(int i = 0; i < size; ++i){
                colorArr[i] = DyeColor.HSLToColor(rand.nextFloat(), (float)1.0, (float)0.5);
            }

            net.minecraft.server.v1_15_R1.NBTTagIntArray colors = new net.minecraft.server.v1_15_R1.NBTTagIntArray(colorArr);
            properties.set("Colors", colors);

            explosion.add(properties);
            fireworks.set("Explosions", explosion);
            tag.set("Fireworks", fireworks);

            net.minecraft.server.v1_15_R1.ItemStack tmp = org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack.asNMSCopy(rocket);
            tmp.setTag(tag);
            rocket = org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack.asBukkitCopy(tmp);
            
            return rocket;
        }
    }
    
    private static class Firework116R3 {
        public ItemStack get(Random rand, int stackSize) {
            ItemStack rocket = new ItemStack(Material.FIREWORK_ROCKET, stackSize);
            net.minecraft.server.v1_16_R3.NBTTagCompound tag = new net.minecraft.server.v1_16_R3.NBTTagCompound();
            net.minecraft.server.v1_16_R3.NBTTagCompound fireworks = new net.minecraft.server.v1_16_R3.NBTTagCompound();

            fireworks.setByte("Flight", (byte) (rand.nextInt(3) + 1));

            net.minecraft.server.v1_16_R3.NBTTagList explosion = new net.minecraft.server.v1_16_R3.NBTTagList();

            net.minecraft.server.v1_16_R3.NBTTagCompound properties = new net.minecraft.server.v1_16_R3.NBTTagCompound();
            properties.setByte("Flicker", (byte) (rand.nextBoolean() ? 1 : 0));
            properties.setByte("Trail", (byte) (rand.nextBoolean() ? 1 : 0));
            properties.setByte("Type", (byte) (rand.nextInt(5)));

            int size = rand.nextInt(4) + 1;
            int[] colorArr = new int[size];
            for(int i = 0; i < size; ++i){
                colorArr[i] = DyeColor.HSLToColor(rand.nextFloat(), (float)1.0, (float)0.5);
            }

            net.minecraft.server.v1_16_R3.NBTTagIntArray colors = new net.minecraft.server.v1_16_R3.NBTTagIntArray(colorArr);
            properties.set("Colors", colors);

            explosion.add(properties);
            fireworks.set("Explosions", explosion);
            tag.set("Fireworks", fireworks);

            net.minecraft.server.v1_16_R3.ItemStack tmp = org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack.asNMSCopy(rocket);
            tmp.setTag(tag);
            rocket = org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack.asBukkitCopy(tmp);
            
            return rocket;
        }
    }
    
    private static class Firework116R2 {
        public ItemStack get(Random rand, int stackSize) {
            ItemStack rocket = new ItemStack(Material.FIREWORK_ROCKET, stackSize);
            net.minecraft.server.v1_16_R2.NBTTagCompound tag = new net.minecraft.server.v1_16_R2.NBTTagCompound();
            net.minecraft.server.v1_16_R2.NBTTagCompound fireworks = new net.minecraft.server.v1_16_R2.NBTTagCompound();

            fireworks.setByte("Flight", (byte) (rand.nextInt(3) + 1));

            net.minecraft.server.v1_16_R2.NBTTagList explosion = new net.minecraft.server.v1_16_R2.NBTTagList();

            net.minecraft.server.v1_16_R2.NBTTagCompound properties = new net.minecraft.server.v1_16_R2.NBTTagCompound();
            properties.setByte("Flicker", (byte) (rand.nextBoolean() ? 1 : 0));
            properties.setByte("Trail", (byte) (rand.nextBoolean() ? 1 : 0));
            properties.setByte("Type", (byte) (rand.nextInt(5)));

            int size = rand.nextInt(4) + 1;
            int[] colorArr = new int[size];
            for(int i = 0; i < size; ++i){
                colorArr[i] = DyeColor.HSLToColor(rand.nextFloat(), (float)1.0, (float)0.5);
            }

            net.minecraft.server.v1_16_R2.NBTTagIntArray colors = new net.minecraft.server.v1_16_R2.NBTTagIntArray(colorArr);
            properties.set("Colors", colors);

            explosion.add(properties);
            fireworks.set("Explosions", explosion);
            tag.set("Fireworks", fireworks);

            net.minecraft.server.v1_16_R2.ItemStack tmp = org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack.asNMSCopy(rocket);
            tmp.setTag(tag);
            rocket = org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack.asBukkitCopy(tmp);
            
            return rocket;
        }
    }
    
    private static class Firework116 {
        public ItemStack get(Random rand, int stackSize) {
            ItemStack rocket = new ItemStack(Material.FIREWORK_ROCKET, stackSize);
            net.minecraft.server.v1_16_R1.NBTTagCompound tag = new net.minecraft.server.v1_16_R1.NBTTagCompound();
            net.minecraft.server.v1_16_R1.NBTTagCompound fireworks = new net.minecraft.server.v1_16_R1.NBTTagCompound();

            fireworks.setByte("Flight", (byte) (rand.nextInt(3) + 1));

            net.minecraft.server.v1_16_R1.NBTTagList explosion = new net.minecraft.server.v1_16_R1.NBTTagList();

            net.minecraft.server.v1_16_R1.NBTTagCompound properties = new net.minecraft.server.v1_16_R1.NBTTagCompound();
            properties.setByte("Flicker", (byte) (rand.nextBoolean() ? 1 : 0));
            properties.setByte("Trail", (byte) (rand.nextBoolean() ? 1 : 0));
            properties.setByte("Type", (byte) (rand.nextInt(5)));

            int size = rand.nextInt(4) + 1;
            int[] colorArr = new int[size];
            for(int i = 0; i < size; ++i){
                colorArr[i] = DyeColor.HSLToColor(rand.nextFloat(), (float)1.0, (float)0.5);
            }

            net.minecraft.server.v1_16_R1.NBTTagIntArray colors = new net.minecraft.server.v1_16_R1.NBTTagIntArray(colorArr);
            properties.set("Colors", colors);

            explosion.add(properties);
            fireworks.set("Explosions", explosion);
            tag.set("Fireworks", fireworks);

            net.minecraft.server.v1_16_R1.ItemStack tmp = org.bukkit.craftbukkit.v1_16_R1.inventory.CraftItemStack.asNMSCopy(rocket);
            tmp.setTag(tag);
            rocket = org.bukkit.craftbukkit.v1_16_R1.inventory.CraftItemStack.asBukkitCopy(tmp);
            
            return rocket;
        }
    }
        
    public static ItemStack get(Random rand, int stackSize) {
        if(Main.version == MultiVersion.Version.V1_14_R1) {
            return (new Firework114()).get(rand, stackSize);
        }
        if(Main.version == MultiVersion.Version.V1_15_R1) {
            return (new Firework115()).get(rand, stackSize);
        }
        if(Main.version == MultiVersion.Version.V1_16_R1) {
            return (new Firework116()).get(rand, stackSize);
        }
        if(Main.version == MultiVersion.Version.V1_16_R2) {
            return (new Firework116R2()).get(rand, stackSize);
        }
        if(Main.version == MultiVersion.Version.V1_16_R3) {
            return (new Firework116R3()).get(rand, stackSize);
        }

        return new ItemStack(Material.FIREWORK_ROCKET, stackSize);
    }
}

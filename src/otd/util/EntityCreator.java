/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.util;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.CreatureSpawnEvent;
import otd.Main;
import otd.MultiVersion;

public class EntityCreator {
    
    private static class EntityCreator114 {
        public Entity create(EntityType entityType, Location location) {
            org.bukkit.craftbukkit.v1_14_R1.CraftWorld cw = (org.bukkit.craftbukkit.v1_14_R1.CraftWorld) location.getWorld();
            net.minecraft.server.v1_14_R1.Entity entity = cw.createEntity(location, entityType.getEntityClass());
            return (Entity) entity.getBukkitEntity();
        }
        public void spawn(World world, Entity entity) {
            org.bukkit.craftbukkit.v1_14_R1.CraftWorld cw = (org.bukkit.craftbukkit.v1_14_R1.CraftWorld) world;
            org.bukkit.craftbukkit.v1_14_R1.entity.CraftEntity ce = (org.bukkit.craftbukkit.v1_14_R1.entity.CraftEntity) entity;
            cw.addEntity(ce.getHandle(), CreatureSpawnEvent.SpawnReason.CUSTOM);
        }
    }
    private static class EntityCreator115 {
        public Entity create(EntityType entityType, Location location) {
            org.bukkit.craftbukkit.v1_15_R1.CraftWorld cw = (org.bukkit.craftbukkit.v1_15_R1.CraftWorld) location.getWorld();
            net.minecraft.server.v1_15_R1.Entity entity = cw.createEntity(location, entityType.getEntityClass());
            
            return (Entity) entity.getBukkitEntity();
        }
        public void spawn(World world, Entity entity) {
            org.bukkit.craftbukkit.v1_15_R1.CraftWorld cw = (org.bukkit.craftbukkit.v1_15_R1.CraftWorld) world;
            org.bukkit.craftbukkit.v1_15_R1.entity.CraftEntity ce = (org.bukkit.craftbukkit.v1_15_R1.entity.CraftEntity) entity;
            cw.addEntity(ce.getHandle(), CreatureSpawnEvent.SpawnReason.CUSTOM);
        }
    }
    private static class EntityCreator116R1 {
        public Entity create(EntityType entityType, Location location) {
            org.bukkit.craftbukkit.v1_16_R1.CraftWorld cw = (org.bukkit.craftbukkit.v1_16_R1.CraftWorld) location.getWorld();
            net.minecraft.server.v1_16_R1.Entity entity = cw.createEntity(location, entityType.getEntityClass());
            
            return (Entity) entity.getBukkitEntity();
        }
        public void spawn(World world, Entity entity) {
            org.bukkit.craftbukkit.v1_16_R1.CraftWorld cw = (org.bukkit.craftbukkit.v1_16_R1.CraftWorld) world;
            org.bukkit.craftbukkit.v1_16_R1.entity.CraftEntity ce = (org.bukkit.craftbukkit.v1_16_R1.entity.CraftEntity) entity;
            cw.addEntity(ce.getHandle(), CreatureSpawnEvent.SpawnReason.CUSTOM);
        }
    }
    private static class EntityCreator116R2 {
        public Entity create(EntityType entityType, Location location) {
            org.bukkit.craftbukkit.v1_16_R2.CraftWorld cw = (org.bukkit.craftbukkit.v1_16_R2.CraftWorld) location.getWorld();
            net.minecraft.server.v1_16_R2.Entity entity = cw.createEntity(location, entityType.getEntityClass());
            
            return (Entity) entity.getBukkitEntity();
        }
        public void spawn(World world, Entity entity) {
            org.bukkit.craftbukkit.v1_16_R2.CraftWorld cw = (org.bukkit.craftbukkit.v1_16_R2.CraftWorld) world;
            org.bukkit.craftbukkit.v1_16_R2.entity.CraftEntity ce = (org.bukkit.craftbukkit.v1_16_R2.entity.CraftEntity) entity;
            cw.addEntity(ce.getHandle(), CreatureSpawnEvent.SpawnReason.CUSTOM);
        }
    }
    private static class EntityCreator116R3 {
        public Entity create(EntityType entityType, Location location) {
            org.bukkit.craftbukkit.v1_16_R3.CraftWorld cw = (org.bukkit.craftbukkit.v1_16_R3.CraftWorld) location.getWorld();
            net.minecraft.server.v1_16_R3.Entity entity = cw.createEntity(location, entityType.getEntityClass());
            
            return (Entity) entity.getBukkitEntity();
        }
        public void spawn(World world, Entity entity) {
            org.bukkit.craftbukkit.v1_16_R3.CraftWorld cw = (org.bukkit.craftbukkit.v1_16_R3.CraftWorld) world;
            org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity ce = (org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity) entity;
            cw.addEntity(ce.getHandle(), CreatureSpawnEvent.SpawnReason.CUSTOM);
        }
    }
    
    public static Entity create(EntityType entityType, Location location) {
        if(Main.version == MultiVersion.Version.V1_14_R1) {
            return (new EntityCreator114()).create(entityType, location);
        }
        if(Main.version == MultiVersion.Version.V1_15_R1) {
            return (new EntityCreator115()).create(entityType, location);
        }
        if(Main.version == MultiVersion.Version.V1_16_R1) {
            return (new EntityCreator116R1()).create(entityType, location);
        }
        if(Main.version == MultiVersion.Version.V1_16_R2) {
            return (new EntityCreator116R2()).create(entityType, location);
        }
        if(Main.version == MultiVersion.Version.V1_16_R3) {
            return (new EntityCreator116R3()).create(entityType, location);
        }
        return null;
    }
    
    public static void spawn(World world, Entity entity) {
        if(Main.version == MultiVersion.Version.V1_14_R1) {
            (new EntityCreator114()).spawn(world, entity);
        }
        if(Main.version == MultiVersion.Version.V1_15_R1) {
            (new EntityCreator115()).spawn(world, entity);
        }
        if(Main.version == MultiVersion.Version.V1_16_R1) {
            (new EntityCreator116R1()).spawn(world, entity);
        }
        if(Main.version == MultiVersion.Version.V1_16_R2) {
            (new EntityCreator116R2()).spawn(world, entity);
        }
        if(Main.version == MultiVersion.Version.V1_16_R3) {
            (new EntityCreator116R3()).spawn(world, entity);
        }
    }
}

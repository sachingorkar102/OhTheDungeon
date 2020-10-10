/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zhehe.util.config;

import forge_sandbox.jaredbgreat.dldungeons.Difficulty;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import zhehe.util.config.EnumType.ChestType;

/**
 *
 * @author
 */
public class SimpleWorldConfig {
    public class Roguelike {
        public Set<String> biomeExclusions = new HashSet<>();
//        public boolean doBuiltinSpawn = true;
        public boolean doNaturalSpawn = false;
        public boolean encase = true;
        public boolean generous = true;
        public double looting = 0.0;
        public int lowerLimit = 60;
        public boolean random = false;
        public int upperLimit = 100;
        public boolean builtinLoot = true;
        
        public List<RoguelikeLootNode> loots = new ArrayList<>();
    }
    public Roguelike roguelike = new Roguelike();
    
    public class Doomlike {
        public Set<String> biomeExclusions = new HashSet<>();
        public boolean builtinLoot = true;
        public boolean doNaturalSpawn = false;
        public boolean easyFind = true;
        public boolean singleEntrance = true;
        public boolean thinSpawners = true;
        public Difficulty difficulty = Difficulty.NORM;
        
        public List<LootNode> loots = new ArrayList<>();
    }
    
    public Doomlike doomlike = new Doomlike();
    
    public class BattleTower {
        public Set<String> biomeExclusions = new HashSet<>();
        public boolean doNaturalSpawn = false;
        public boolean builtinLoot = true;
        
        public List<LootNode> loots = new ArrayList<>();
        public ChestType chest = ChestType.BOX;
    }
    
    public BattleTower battletower = new BattleTower();
    
    public class SmoofyDungeon {
        public Set<String> biomeExclusions = new HashSet<>();
        public boolean doNaturalSpawn = false;
        public boolean builtinLoot = true;
        
        public List<LootNode> loots = new ArrayList<>();
        
        public boolean Skeleton = true;
        public boolean Blaze = true;
        public boolean Spider = true;
        public boolean PigZombie = true;
        public boolean Zombie = true;
        public boolean Ghast = true;
        public boolean Creeper = true;
    }
    
    public SmoofyDungeon smoofydungeon = new SmoofyDungeon();
    
    
    public int roguelike_weight = 3;
    public int doomlike_weight = 3;
    public int battle_tower_weight = 2;
    public int smoofy_weight = 3;
    public int draylar_weight = 3;
    public int antman_weight = 3;
    
    public int distance = 22;
    
    public boolean egg_change_spawner = true;
    public boolean silk_vanilla_spawner = false;
    public boolean silk_dungeon_spawner = false;
    public boolean mob_drop_in_vanilla_spawner = true;
    public boolean mob_drop_in_dungeon_spawner = false;
    public float disappearance_rate_vanilla = 0;
    public float disappearance_rate_dungeon = 0;
    
    public void initSmoofyDungeon() {
        this.smoofydungeon = new SmoofyDungeon();
    }
    
    public class DraylarBattleTower {
        public Set<String> biomeExclusions = new HashSet<>();
        public boolean doNaturalSpawn = false;
        public boolean builtinLoot = true;
        
        public List<LootNode> loots = new ArrayList<>();
    }
    
    public DraylarBattleTower draylar_battletower = new DraylarBattleTower();
    
    public void initDraylarBattleTower() {
        this.draylar_battletower = new DraylarBattleTower();
    }
    
    public class AntManDungeon {
        public Set<String> biomeExclusions = new HashSet<>();
        public boolean doNaturalSpawn = false;
        public boolean builtinLoot = true;
        
        public List<LootNode> loots = new ArrayList<>();
    }
    
    public AntManDungeon ant_man_dungeon = new AntManDungeon();
    
    public void initAntManDungeon() {
        this.ant_man_dungeon = new AntManDungeon();
    }
}

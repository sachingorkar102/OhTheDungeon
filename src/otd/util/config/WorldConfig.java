/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.util.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import otd.Main;
import zhehe.util.I18n;
import otd.util.config.EnumType.ChestType;
import static otd.util.config.WorldConfig.wc;
import otd.world.WorldDefine;

/**
 *
 * @author
 */
public class WorldConfig {
    public Map<String, SimpleWorldConfig> dict = new HashMap<>();
    
    public boolean furniture = true;
    public boolean preciousBlocks = true;
    public boolean rogueSpawners = true;
    public boolean disableAPI = false;
    public boolean noMobChanges = false;
    public int version = 10;
    
    public int rollCoolDownInSecond = 10;
    public int rollRange = 15;
    
    public String diceUUID = "afbe4c67-a6a5-4559-ad06-78a6ed2ab4e9";
    public String diceTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTE1ZjdjMzEzYmNhOWMyZjk1OGU2OGFiMTRhYjM5Mzg2N2Q2NzUwM2FmZmZmOGYyMGNiMTNmYmU5MTdmZDMxIn19fQ==";
    
    public DungeonWorldConfig dungeon_world = new DungeonWorldConfig();
    
    public static WorldConfig wc;
    static {
        wc = new WorldConfig();
        addDungeonPlotConfig();
        
    }
    
    public WorldConfig() {
    }
    
    private void initDungeonWorld() {
        addDungeonPlotConfig();
    }
    
    private static boolean addDungeonPlotConfig() {
        if(!wc.dict.containsKey(WorldDefine.WORLD_NAME)) {
            SimpleWorldConfig swc = new SimpleWorldConfig();
            swc.aether_dungeon.doNaturalSpawn = true;
            swc.ant_man_dungeon.doNaturalSpawn = true;
            swc.battletower.doNaturalSpawn = true;
            swc.doomlike.doNaturalSpawn = true;
            swc.draylar_battletower.doNaturalSpawn = true;
            swc.lich_tower.doNaturalSpawn = true;
            swc.roguelike.doNaturalSpawn = true;
            swc.smoofydungeon.doNaturalSpawn = true;
            
            wc.dict.put(WorldDefine.WORLD_NAME, swc);
            return true;
        }
        return false;
    }
    
    public static void handleRoguelikePatch() {
        
    }
    
    public static void save() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(wc);
        JavaPlugin plugin = Main.instance;
        // make sure file exists
        File configDir = plugin.getDataFolder();
	if(!configDir.exists()){
            configDir.mkdir();
	}
        
        String world_config_file = configDir.toString() + File.separator + "world_config.json";
        File file = new File(world_config_file);
        try(OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "utf-8")) {
            oStreamWriter.append(json);
            oStreamWriter.close();
        } catch (IOException ex) {
            Bukkit.getLogger().log(Level.SEVERE, I18n.instance.Load_Config_Err);
        }
    }
    
    public static void loadWorldConfig() {
        JavaPlugin plugin = Main.instance;
        // make sure file exists
        File configDir = plugin.getDataFolder();
	if(!configDir.exists()){
            configDir.mkdir();
	}
        
        String world_config_file = configDir.toString() + File.separator + "world_config.json";
        File cfile = new File(world_config_file);
        if(!cfile.exists()){
            try {
                wc.dict.put("example_otd", new SimpleWorldConfig());
                cfile.createNewFile();
                save();
            } catch (IOException ex) {
                Bukkit.getLogger().log(Level.SEVERE, I18n.instance.Load_Config_Err);
            }
        }
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(world_config_file), "UTF8"))) {
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                line = reader.readLine();
            }
            wc = (new Gson()).fromJson(sb.toString(), WorldConfig.class);
        } catch (IOException ex) {
            wc = new WorldConfig();
        }
        update();
    }
    
    public static void update() {
        boolean saves = false;
        if(wc.version == 0) {
            for(Map.Entry<String, SimpleWorldConfig> entry : wc.dict.entrySet()) {
                entry.getValue().battletower.chest = ChestType.BOX;
            }
            wc.version = 1;
            saves = true;
        }
        if(wc.version == 1) {
            for(Map.Entry<String, SimpleWorldConfig> entry : wc.dict.entrySet()) {
                entry.getValue().smoofy_weight = 3;
                entry.getValue().initSmoofyDungeon();
            }
            wc.version = 2;
            saves = true;
        }
        if(wc.version == 2) {
            for(Map.Entry<String, SimpleWorldConfig> entry : wc.dict.entrySet()) {
                entry.getValue().egg_change_spawner = true;
            }
            wc.rollCoolDownInSecond = 10;
            wc.rollRange = 15;
            wc.diceUUID = "afbe4c67-a6a5-4559-ad06-78a6ed2ab4e9";
            wc.diceTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTE1ZjdjMzEzYmNhOWMyZjk1OGU2OGFiMTRhYjM5Mzg2N2Q2NzUwM2FmZmZmOGYyMGNiMTNmYmU5MTdmZDMxIn19fQ==";
            wc.version = 3;
            saves = true;
        }
        if(wc.version == 3) {
            for(Map.Entry<String, SimpleWorldConfig> entry : wc.dict.entrySet()) {
                entry.getValue().roguelike.builtinLoot = true;
            }
            wc.version = 4;
            saves = true;
        }
        if(wc.version == 4) {
            for(Map.Entry<String, SimpleWorldConfig> entry : wc.dict.entrySet()) {
                entry.getValue().draylar_weight = 3;
                entry.getValue().initDraylarBattleTower();
            }
            wc.version = 5;
            saves = true;
        }
        if(wc.version == 5) {
            for(Map.Entry<String, SimpleWorldConfig> entry : wc.dict.entrySet()) {
                entry.getValue().silk_vanilla_spawner = false;
                entry.getValue().silk_dungeon_spawner = false;
                entry.getValue().mob_drop_in_vanilla_spawner = true;
                entry.getValue().mob_drop_in_dungeon_spawner = false;
                entry.getValue().disappearance_rate_vanilla = 0;
                entry.getValue().disappearance_rate_dungeon = 0;
            }
            wc.version = 6;
            saves = true;
        }
        if(wc.version == 6) {
            for(Map.Entry<String, SimpleWorldConfig> entry : wc.dict.entrySet()) {
                entry.getValue().antman_weight = 3;
                entry.getValue().initAntManDungeon();
            }
            wc.version = 7;
            saves = true;
        }
        if(wc.version == 7) {
            for(Map.Entry<String, SimpleWorldConfig> entry : wc.dict.entrySet()) {
                entry.getValue().aether_weight = 3;
                entry.getValue().prevent_spawner_dropping = false;
                entry.getValue().prevent_spawner_breaking = false;
                entry.getValue().initAetherDungeon();
            }
            wc.version = 8;
            saves = true;
        }
        if(wc.version == 8) {
            for(Map.Entry<String, SimpleWorldConfig> entry : wc.dict.entrySet()) {
                entry.getValue().lich_weight = 3;
                entry.getValue().initLichTower();
            }
            wc.version = 9;
            saves = true;
        }
        if(wc.version == 9) {
            wc.initDungeonWorld();
            wc.version = 10;
            saves = true;
        }
        if(addDungeonPlotConfig()) {
            saves = true;
        }
        if(wc.dungeon_world.dungeon_count > 30) {
            wc.dungeon_world.dungeon_count = 30;
            saves = true;
        }
        if(saves) save();
    }
}

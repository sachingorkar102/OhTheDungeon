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

/**
 *
 * @author
 */
public class PluginConfig {
    public Map<String, String> config;
    private PluginConfig() {
        config = new HashMap<>();
    }
    
    public static PluginConfig instance = new PluginConfig();
    private boolean check() {
        boolean save = false;
        if(config == null) config = new HashMap<>();
        if(!config.containsKey("updater")) {
            config.put("updater", "true");
            save = true;
        }
        if(!config.containsKey("bstats")) {
            config.put("bstats", "true");
            save = true;
        }
        return save;
    }
    
    public void update() {
        if(check()) saveConfig();
    }
    
    public static void saveConfig() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(instance);
        JavaPlugin plugin = Main.instance;
        // make sure file exists
        File configDir = plugin.getDataFolder();
	if(!configDir.exists()){
            configDir.mkdir();
	}
        
        String world_config_file = configDir.toString() + File.separator + "settings.json";
        File file = new File(world_config_file);
        try(OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "utf-8")) {
            oStreamWriter.append(json);
            oStreamWriter.close();
        } catch (IOException ex) {
            Bukkit.getLogger().log(Level.SEVERE, I18n.instance.Load_Config_Err);
        }
        
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(world_config_file), "UTF8"))) {
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                line = reader.readLine();
            }
            instance = (new Gson()).fromJson(sb.toString(), PluginConfig.class);
        } catch (IOException ex) {
            instance = new PluginConfig();
            Bukkit.getLogger().log(Level.SEVERE, I18n.instance.Load_Config_Err);
        }
    }
    
    public void init() {
        JavaPlugin plugin = Main.instance;
        // make sure file exists
        File configDir = plugin.getDataFolder();
	if(!configDir.exists()){
            configDir.mkdir();
	}
        
        String config_file = configDir.toString() + File.separator + "settings.json";
        File cfile = new File(config_file);
        if(!cfile.exists()){
            try {
                check();
                cfile.createNewFile();
                saveConfig();
            } catch (IOException ex) {
                Bukkit.getLogger().log(Level.SEVERE, I18n.instance.Load_Config_Err);
            }
        }
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(config_file), "UTF8"))) {
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                line = reader.readLine();
            }
            instance = (new Gson()).fromJson(sb.toString(), PluginConfig.class);
        } catch (IOException ex) {
            instance = new PluginConfig();
        }
        update();
    }
}

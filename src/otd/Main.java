/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd;

import forge_sandbox.com.someguyssoftware.dungeons2.config.ModConfig;
import forge_sandbox.com.someguyssoftware.dungeons2.spawner.SpawnSheetLoader;
import forge_sandbox.com.someguyssoftware.dungeons2.style.StyleSheetLoader;
import otd.commands.Otd_Place;
import otd.commands.Otd_Cp;
import otd.commands.Otd;
import forge_sandbox.Sandbox;
import forge_sandbox.greymerk.roguelike.dungeon.Dungeon;
import forge_sandbox.jaredbgreat.dldungeons.themes.ThemeReader;
import forge_sandbox.jaredbgreat.dldungeons.themes.ThemeType;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.logging.Level;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.UnknownDependencyException;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import otd.dungeon.draylar.BattleTowerSchematics;
import otd.event.Event;
import otd.event.SpawnerEvent;
import otd.generator.DungeonPopulator;
import otd.update.UpdateChecker;
import otd.util.WorldDiagnostic;
import shadow_lib.async.io.papermc.lib.PaperLib;
import shadow_lib.bstats.Metrics;
import shadow_manager.DungeonWorldManager;
import zhehe.util.I18n;
import zhehe.util.Logging;
import otd.util.config.PluginConfig;
import otd.util.config.WorldConfig;
import otd.util.gui.AetherDungeonConfig;
import otd.util.gui.AntManDungeonConfig;
import otd.util.gui.BattleTowerConfig;
import otd.util.gui.BiomeSetting;
import otd.util.gui.DoomlikeConfig;
import otd.util.gui.DraylarBattleTowerConfig;
import otd.util.gui.DungeonSpawnSetting;
import otd.util.gui.LootItem;
import otd.util.gui.LootManager;
import otd.util.gui.RoguelikeConfig;
import otd.util.gui.RoguelikeLootItem;
import otd.util.gui.RoguelikeLootManager;
import otd.util.gui.SmoofyConfig;
import otd.util.gui.WorldEditor;
import otd.util.gui.WorldManager;
import otd.util.gui.WorldSpawnerManager;
import otd.util.lang.LanguageUtil;
import forge_sandbox.twilightforest.structures.lichtower.boss.Lich;
import otd.util.gui.LichTowerConfig;
import otd.world.WorldDefine;

/**
 *
 * @author
 */
public class Main extends JavaPlugin {
    public static JavaPlugin instance;
    public static boolean disabled = false;
    private static Integer api_version = 6;
    public static MultiVersion.Version version = MultiVersion.Version.UNKNOWN;
    private Metrics metrics;
    private final static int metric_pluginId = 9213;

    
    public Main() {
        if(MultiVersion.is114()) {
            version = MultiVersion.Version.V1_14_R1;
            Bukkit.getLogger().log(Level.INFO, "{0}[Oh The Dungeons You''ll Go] MC Version: 1.14.x", ChatColor.GREEN);
        }
        else if(MultiVersion.is115()) {
            version = MultiVersion.Version.V1_15_R1;
            Bukkit.getLogger().log(Level.INFO, "{0}[Oh The Dungeons You''ll Go] MC Version: 1.15.x", ChatColor.GREEN);
        }
        else if(MultiVersion.is116R1()) {
            version = MultiVersion.Version.V1_16_R1;
            Bukkit.getLogger().log(Level.INFO, "{0}[Oh The Dungeons You''ll Go] MC Version: 1.16.[0-1]", ChatColor.GREEN);
        }
        else if(MultiVersion.is116R2()) {
            version = MultiVersion.Version.V1_16_R2;
            Bukkit.getLogger().log(Level.INFO, "{0}[Oh The Dungeons You''ll Go] MC Version: 1.16.[2-3]", ChatColor.GREEN);
        }
        else if(MultiVersion.is116R3()) {
            version = MultiVersion.Version.V1_16_R3;
            Bukkit.getLogger().log(Level.INFO, "{0}[Oh The Dungeons You''ll Go] MC Version: 1.16.4", ChatColor.GREEN);
        }
        else version = MultiVersion.Version.UNKNOWN;
    }
    
    @Override
    public void onDisable() {
        Bukkit.getLogger().log(Level.WARNING, "[Oh The Dungeons You'll Go] Plugin is disabled");
        disabled = true;
    }
    
    @Override
    public void onEnable() {
        if(version == MultiVersion.Version.UNKNOWN) {
            Bukkit.getLogger().log(Level.SEVERE, "[Oh The Dungeons You'll Go] Unsupported MC Version");
            throw new UnsupportedOperationException("Unsupported MC Version");
        }
        
        //PaperLib.suggestPaper(this);
        disabled = false;
        instance = this;
        
        Sandbox.mkdir();
        I18n.init();
        WorldConfig.loadWorldConfig();
        
        ThemeReader.setConfigDir();
        ThemeReader.setThemesDir();
        ThemeReader.readSpecialChest();
    	ThemeReader.readThemes(); 
        ThemeType.SyncMobLists();
        
        
        StyleSheetLoader.exposeStyleSheet(ModConfig.styleSheetFile);
        SpawnSheetLoader.exposeSpawnSheet(ModConfig.spawnSheetFile);
        
        Dungeon.init = true;        
        
        getServer().getPluginManager().registerEvents(new DLDWorldListener(), this);
        
        getServer().getPluginManager().registerEvents(WorldEditor.instance, this);
        getServer().getPluginManager().registerEvents(WorldManager.instance, this);
        getServer().getPluginManager().registerEvents(RoguelikeConfig.instance, this);
        getServer().getPluginManager().registerEvents(LootManager.instance, this);
        getServer().getPluginManager().registerEvents(LootItem.instance, this);
        getServer().getPluginManager().registerEvents(RoguelikeLootManager.instance, this);
        getServer().getPluginManager().registerEvents(RoguelikeLootItem.instance, this);
        getServer().getPluginManager().registerEvents(BiomeSetting.instance, this);
        getServer().getPluginManager().registerEvents(DoomlikeConfig.instance, this);
        getServer().getPluginManager().registerEvents(BattleTowerConfig.instance, this);
        getServer().getPluginManager().registerEvents(DungeonSpawnSetting.instance, this);
        getServer().getPluginManager().registerEvents(SmoofyConfig.instance, this);
        getServer().getPluginManager().registerEvents(DraylarBattleTowerConfig.instance, this);
        getServer().getPluginManager().registerEvents(WorldSpawnerManager.instance, this);
        getServer().getPluginManager().registerEvents(AntManDungeonConfig.instance, this);
        getServer().getPluginManager().registerEvents(AetherDungeonConfig.instance, this);
        getServer().getPluginManager().registerEvents(LichTowerConfig.instance, this);
        
        getServer().getPluginManager().registerEvents(new Event(), this);
        getServer().getPluginManager().registerEvents(new SpawnerEvent(), this);
        getServer().getPluginManager().registerEvents(new Lich(), this);

        PluginConfig.instance.init();
        PluginConfig.instance.update();
        
        String update = PluginConfig.instance.config.get("updater");
        if(update != null && update.equalsIgnoreCase("TRUE")) {
            Bukkit.getLogger().log(Level.INFO, "[Oh The Dungeons You'll Go] Update checking...");
            asyncUpdateChecker();
        }
        
        String bstats = PluginConfig.instance.config.get("bstats");
        if(bstats != null && bstats.equalsIgnoreCase("TRUE")) {
            metrics = new Metrics(this, metric_pluginId);
        }
        
        registerCommand();
        BattleTowerSchematics.init(this);
        
        LanguageUtil.init();
        Lich.init();
        
        Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
            WorldDiagnostic.diagnostic();
            {
                try {
                    InputStream stream = this.getResource("logo.txt");
                    InputStreamReader isr = new InputStreamReader(stream);
                    BufferedReader reader = new BufferedReader(isr);
                    String line;

                    while( (line=reader.readLine())!=null) {
                        Bukkit.getLogger().log(Level.INFO, "{0}{1}", new Object[]{ChatColor.BLUE, line});
                    }
                } catch (IOException ex) {

                }
            }
        }, 1L);
        
        Bukkit.getScheduler().runTaskLater(this, () -> {
            PaperLib.suggestPaper(Main.instance);
        }, 2L);
    }
    
    private void loadAdvancement() {
        File out = new File(Main.instance.getDataFolder(), "OhTheDungeonAdvancement.jar");
        try(InputStream in = Main.instance.getResource("OhTheDungeonAdvancement.jar");
           OutputStream writer = new BufferedOutputStream(
               new FileOutputStream(out, false))) {
            // Step 3
            byte[] buffer = new byte[1024 * 4];
            int length;
            while((length = in.read(buffer)) >= 0) {
                writer.write(buffer, 0, length);
            }
        } catch(Exception ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Load Advancements error...");
            return;
        }
        try {
            getServer().getPluginManager().loadPlugin(out);
        } catch(InvalidDescriptionException | InvalidPluginException | UnknownDependencyException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Load Advancements error...");
        }
    }
    
    private BukkitRunnable update_check_task_id;
    private final int RESOURCE_ID = 76437;
    private void asyncUpdateChecker() {
        update_check_task_id = new BukkitRunnable() {
            @Override
            public void run() {
                UpdateChecker.CheckUpdate(instance, RESOURCE_ID);
            }
        };
        update_check_task_id.runTaskTimerAsynchronously(this, 200, 20 * 3600 * 1);
    }
    
    private class DLDWorldListener implements Listener {
        @EventHandler(priority = EventPriority.LOW)
        public void onWorldInit(WorldInitEvent event) {
            String world_name = event.getWorld().getName();
            if(world_name.equals(DungeonWorldManager.WORLD_NAME)) return;
            if(world_name.equalsIgnoreCase(WorldDefine.WORLD_NAME)) return;
            Logging.logInfo(ChatColor.BLUE + "[Oh The Dungeons You'll Go] Found world: " + world_name);
            event.getWorld().getPopulators().add(new DungeonPopulator());
        }
    }
    
    private void registerCommand() {
        Otd otd = new Otd();
        Otd_Place otd_place = new Otd_Place();
        Otd_Cp otd_cp = new Otd_Cp();
        
        PluginCommand command;
        command = this.getCommand("oh_the_dungeons");
        if(command != null) {
            command.setExecutor(otd);
            command.setTabCompleter(otd);
        }
        command = this.getCommand("oh_the_dungeons_place");
        if(command != null) {
            command.setExecutor(otd_place);
            command.setTabCompleter(otd_place);
        }
        command = this.getCommand("oh_the_dungeons_cp");
        if(command != null) {
            command.setExecutor(otd_cp);
            command.setTabCompleter(otd_cp);
        }
    }
}

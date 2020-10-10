package forge_sandbox.greymerk.roguelike.dungeon.settings;

import java.util.Collection;
import java.util.Random;

//import forge_sandbox.greymerk.roguelike.config.RogueConfig;
import forge_sandbox.greymerk.roguelike.util.WeightedChoice;
import forge_sandbox.greymerk.roguelike.util.WeightedRandomizer;
import forge_sandbox.greymerk.roguelike.worldgen.Coord;
import forge_sandbox.greymerk.roguelike.worldgen.IWorldEditor;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import zhehe.util.config.WorldConfig;

public class SettingsResolver {

	private ISettingsContainer settings;
        
        public SettingsContainer getSettings() {
            return (SettingsContainer) settings;
        }
	
	public SettingsResolver(ISettingsContainer settings) throws Exception{
		this.settings = settings;
	}
        
        public ISettings generateSettings(DungeonSettings chosen, IWorldEditor editor, Random rand, Coord pos) throws Exception{
            DungeonSettings exclusive = processInheritance(chosen, settings);
            DungeonSettings complete = applyInclusives(exclusive, editor, rand, pos); 
            return complete;
        }
	
	// called from Dungeon class
	public ISettings getSettings(IWorldEditor editor, Random rand, Coord pos) throws Exception{
                boolean random = false;
                String world_name = editor.getWorldName();
                if(WorldConfig.wc.dict.containsKey(world_name) && WorldConfig.wc.dict.get(world_name).roguelike.random) random = true;
//		if(random) Bukkit.getLogger().log(Level.SEVERE, "!!!");
                if(random) return new SettingsRandom(rand);
		
		DungeonSettings builtin = this.getBuiltin(editor, rand, pos);
		DungeonSettings custom = this.getCustom(editor, rand, pos);
		
		// there are no valid dungeons for this location
		if(builtin == null && custom == null) return null;
                
		DungeonSettings exclusive = (custom != null) ? custom : builtin;
		
		DungeonSettings complete = applyInclusives(exclusive, editor, rand, pos); 
		
		return complete;
	}
	
	public ISettings getWithName(String name, IWorldEditor editor, Random rand, Coord pos) throws Exception{
		if(name.equals("random")) return new SettingsRandom(rand);
		
		DungeonSettings byName = this.getByName(name);
		
		if(byName == null) return null;
		DungeonSettings withInclusives = applyInclusives(byName, editor, rand, pos);
		
		return new DungeonSettings(new SettingsBlank(), withInclusives);
	}
	
	public static DungeonSettings processInheritance(DungeonSettings toProcess, ISettingsContainer settings) throws Exception{
		DungeonSettings setting = new SettingsBlank();
		
		if(toProcess == null) throw new Exception("Process Inheritance called with null settings object");
		
		for(SettingIdentifier id : toProcess.getInherits()){
			if(settings.contains(id)){
				DungeonSettings inherited = new DungeonSettings(settings.get(id));
				
				if(!inherited.getInherits().isEmpty()){
					inherited = processInheritance(inherited, settings);
				}
				
				setting = new DungeonSettings(setting, inherited);
				
			} else {
				throw new Exception("Setting not found: " + id.toString());
			}
		}
		
		return new DungeonSettings(setting, toProcess);
	}
	
	public ISettings getDefaultSettings(){
		return new DungeonSettings(settings.get(new SettingIdentifier(SettingsContainer.BUILTIN_NAMESPACE, "base")));
	}
	
	private DungeonSettings getByName(String name) throws Exception{
		SettingIdentifier id;
		
		try{
			id = new SettingIdentifier(name);
		} catch (Exception e){
			throw new Exception("Malformed Setting ID String: " + name);
		}
		
		if(!this.settings.contains(id)) return null;
		DungeonSettings setting = new DungeonSettings(this.settings.get(id));
		
		return processInheritance(setting, this.settings);
	}
	
	private DungeonSettings getBuiltin(IWorldEditor editor, Random rand, Coord pos) throws Exception{
//                boolean doBuiltinSpawn = true;
//                String world_name = editor.getWorldName();
                /*if(WorldConfig.wc.dict.containsKey(world_name) && WorldConfig.wc.dict.get(world_name).roguelike.doBuiltinSpawn)*/
//                doBuiltinSpawn = true;
//                if(!doBuiltinSpawn) return null;
		
		WeightedRandomizer<DungeonSettings> settingsRandomizer = new WeightedRandomizer<>();

		for(DungeonSettings setting : settings.getBuiltinSettings()){			
			if(setting.isValid(editor, pos)){
				settingsRandomizer.add(new WeightedChoice<>(setting, setting.criteria.weight));
			}
		}
		
		if(settingsRandomizer.isEmpty()) return null;
		
		DungeonSettings chosen = settingsRandomizer.get(rand);
		
		return processInheritance(chosen, settings);
	}
	
	private DungeonSettings getCustom(IWorldEditor editor, Random rand, Coord pos) throws Exception{
		
		WeightedRandomizer<DungeonSettings> settingsRandomizer = new WeightedRandomizer<>();
		
		for(DungeonSettings setting : settings.getCustomSettings()){
//                    Bukkit.getLogger().log(Level.SEVERE, "~~~~~~~~~");
//                    Bukkit.getLogger().log(Level.SEVERE, Boolean.toString(setting.isValid(editor, pos)));
//                    Bukkit.getLogger().log(Level.SEVERE, Boolean.toString(setting.isExclusive()));
			if(setting.isValid(editor, pos) && setting.isExclusive()){
				settingsRandomizer.add(new WeightedChoice<>(setting, setting.criteria.weight));
			}
		}
		
		DungeonSettings chosen = settingsRandomizer.get(rand);
		
		if(chosen == null) return null;
		
		return processInheritance(chosen, settings);
	}
	
	private DungeonSettings applyInclusives(DungeonSettings setting, IWorldEditor editor, Random rand, Coord pos) throws Exception{
		
		DungeonSettings toReturn = new DungeonSettings(setting);
		
		for(DungeonSettings s : settings.getCustomSettings()){
			if(!s.isValid(editor, pos)) continue;
			if(s.isExclusive()) continue;
			toReturn = new DungeonSettings(toReturn, processInheritance(s, settings));
		}
		
		return toReturn;
	}
	
	public String toString(String namespace){
		Collection<DungeonSettings> byNamespace = this.settings.getByNamespace(namespace);
		if(byNamespace.isEmpty()) return "";
		
		String toReturn = "";
		for(DungeonSettings setting : byNamespace){
			toReturn += setting.id.toString() + " ";
		}
		
		return toReturn;
	}
	
	@Override
	public String toString(){
		return this.settings.toString();		
	}
}

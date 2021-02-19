package me.alexalien;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class MainClass extends JavaPlugin {

	public static MainClass instance;
	

	
	
	@Override
	public void onEnable() {
		
		File WandLevels = new File("plugins/Spells/", "WandLevels.yml");
		
		FileConfiguration WandLevel = YamlConfiguration.loadConfiguration(WandLevels);
		
		Collection<? extends Player> players = getServer().getOnlinePlayers();
		
		Iterator<? extends Player> Playerlist = players.iterator();
		
		while(Playerlist.hasNext()) { //Keep going until it has been applied to all playeres
			Player p = Playerlist.next();
			String UUID = p.getUniqueId().toString();
			if(WandLevel.contains(UUID)) {
				@SuppressWarnings("unchecked")
				List<String> StringList = (List<String>) WandLevel.get(UUID);
				HashMap<String, Double> Levels = EventListener.StringListToHashMap(StringList);
				EventListener.PlayerLevels.put(UUID, Levels);
				
			} else {
				
				HashMap<String, Double> BlankLevels = EventListener.BlankWandLevels();
				List<String> BlanklevelsString = EventListener.HashMapToStringList(BlankLevels);
				WandLevel.set(UUID, BlanklevelsString);
				EventListener.PlayerLevels.put(UUID, BlankLevels);
				try {
					WandLevel.save(WandLevels); //Saves config
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
			}
		}
		
		File Altars = new File("plugins/Spells/", "Altars.yml");
		
		FileConfiguration AltarsLocations = YamlConfiguration.loadConfiguration(Altars);
		
		getCommand("spells").setExecutor(new CommandExecuter());
		
		FileConfiguration config = getConfig();
		//Put Defaults in here!
		
		//Put Defaults in here!
		config.options().copyDefaults(true);
		saveConfig();
		
		
		try {
			WandLevel.save(WandLevels);
			AltarsLocations.save(Altars);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		getServer().getPluginManager().registerEvents(new EventListener(), this);
		
		new Recipes(true);
		
		getLogger().info("Enabled");
	}
	
	@Override
	public void onDisable() {
		
		
		
	}
	
	
	
	
	
	
}



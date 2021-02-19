package me.alexalien;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandSender.Spigot;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class CommandExecuter implements CommandExecutor {
	

	

	
	@Override
	public boolean onCommand(CommandSender Sender, Command Command, String label, String[] args) {
		
		
		if(Sender instanceof Player) {
			Player player = (Player) Sender;
			if(Command.getName().equalsIgnoreCase("spells")) {
				player.sendMessage(ChatColor.RED + "Checkpoint 1");
				if(args.length >= 1) {
					
					String args1 = args[0]; // This is our firt argument for the command
					player.sendMessage(ChatColor.RED + "Checkpoint " + args1);
					
					if(args1.equalsIgnoreCase("altar")) {
						if(args.length >= 2) {
							String args2 = args[1];
							player.sendMessage(ChatColor.RED + "Checkpoint " + args2);
							if(args2.equalsIgnoreCase("remove")) {
								player.sendMessage(ChatColor.RED + "Checkpoint " + args2);
								
								File Altars = new File("plugins/Spells/", "Altars.yml");
								FileConfiguration AltarsLocations = YamlConfiguration.loadConfiguration(Altars);
								if(AltarsLocations.get(player.getUniqueId().toString()) == null) {
									player.sendMessage(ChatColor.RED + "You don't have a altar to remove.");
								} else {
									AltarsLocations.set(player.getUniqueId().toString(), null);
									try {
										AltarsLocations.save(Altars);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									player.sendMessage(ChatColor.RED + "You removed your altar.");
									
									
								}
								

							} else if(args2.equalsIgnoreCase("help")) {
								player.sendMessage(" Coming soon ");
								//I wil use this to give myself wand parts for now just to test
							}
							
						} else {
							player.sendMessage("Incorrect usage do /spells altar help");
						}
					} else if(args1.equalsIgnoreCase("levels")) {
						player.sendMessage(EventListener.PlayerLevels.toString());
						
						
					} else if(args1.equalsIgnoreCase("reload")) {
						
					}
					
					
				} else {
					
				}
			}
		}
		
		// TODO Auto-generated method stub
		return true;
	}

}

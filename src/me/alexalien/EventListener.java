package me.alexalien;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.channels.AcceptPendingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Colorable;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_12_R1.ItemNameTag;
import net.minecraft.server.v1_12_R1.Items;






public class EventListener implements Listener {
	
	//Air Spells
	
	ItemStack Gust = CreateSpell(ChatColor.GRAY + "Gust", Material.QUARTZ, ChatColor.GRAY + "Make your enemies go flying with this spell", "0", "20");
	
	ItemStack ArrowStorm = CreateSpell(ChatColor.GRAY + "ArrowStorm", Material.ARROW, ChatColor.GRAY + "Spawn falling arrows above you", "2-3", "60");
	
	ItemStack AntiProj = CreateSpell(ChatColor.GRAY + "AntiProj", Material.SHIELD, ChatColor.GRAY + "Disregard all Projectile damage for 3 seconds", "0", "65");
	
	ItemStack Airbubble = CreateSpell(ChatColor.GRAY + "Airbubble", Material.IRON_HELMET, ChatColor.GRAY +  "Breath underwater for 4 seconds", "0", "50");
	
	ItemStack Disappear = CreateSpell(ChatColor.GRAY + "Disappear", Material.GLOWSTONE_DUST, ChatColor.GRAY +  "Move 10 blocks in the faced direction and vanish from sight ", "0", "70");
	
	ItemStack Tornado = CreateSpell(ChatColor.GRAY + "Tornado", Material.FEATHER, ChatColor.GRAY +  "Whip up a storm to kill your enemies", "5", "120");
	
	//Blood Spells
	
	ItemStack Thorns = CreateSpell(ChatColor.DARK_RED + "Thorns", Material.CACTUS, "All damage redirected to damager for 2 seconds", "0-10", "100" );
	
	ItemStack BloodRage = CreateSpell(ChatColor.DARK_RED + "Bloodrage", Material.REDSTONE_BLOCK, "Take damage to gain strength", "0", "60");
	
	ItemStack ViperStrike = CreateSpell(ChatColor.DARK_RED + "ViperStrikes", Material.SPIDER_EYE, "Gain posion one, if any players hit you they get posion 2", "0-4", "65");
	
	ItemStack SapLife = CreateSpell(ChatColor.DARK_RED + "SapLife", Material.BONE, "Saps life out of enemy", "3", "55");
	
	ItemStack BloodTrial = CreateSpell(ChatColor.DARK_RED + "BloodTrial", Material.DIAMOND_SWORD, "Lowers both you and your enemies health", "0-7", "75");
	
	//Nature Spells
	
	ItemStack Lightning = CreateSpell(ChatColor.GREEN + "Lightning", Material.IRON_BLOCK, "Summon a Lightning Tesla", "0-4", "50");
	
	ItemStack Regrowth = CreateSpell(ChatColor.GREEN + "Regrowth", Material.SAPLING, "Immobilises you for 3 seconds and heals 5 hearts", "0" , "60");
	
	ItemStack FeatherFall = CreateSpell(ChatColor.GREEN + "Featherfall", Material.DIAMOND_BOOTS, "Ignore all fall damage for 10 seconds", "0", "50");
	
	ItemStack Levitation = CreateSpell(ChatColor.GREEN + "Levitation", Material.GLASS, "Walk on air for 10 seconds", "0", "50");
	
	ItemStack RapidGrowth = CreateSpell(ChatColor.GREEN + "RapidGrowth", Material.LEAVES, "Grow rapidly increases health, speed and damage", "0-10", "100");
	
	//Fire Spells
	
	
	public static HashMap<String, HashMap<String, Double> > PlayerLevels = new HashMap<>();
	//The string is a UUID but in a string format
	
	public static HashMap<UUID, Inventory> Inventories = new HashMap<>();
	
	
	public Inventory invent = Bukkit.createInventory(null, 9, "Altar Crafting."); //Create a public inventory we use this later a lot
	
	public static ItemStack Crafter = new ItemStack(Material.STAINED_GLASS,1, (short)1);
	
	static {
	//We have to make it static and define it all here so everywhere can access it and it is constant
	ItemMeta CrafterMeta = Crafter.getItemMeta();
	CrafterMeta.setDisplayName(ChatColor.AQUA + "Craft");
	List<String> Lore = new ArrayList<String>();
	Lore.add(ChatColor.DARK_AQUA + "Add items in the left slots and click this to craft");
	CrafterMeta.setLore(Lore);
	Crafter.setItemMeta(CrafterMeta);
	
	//Air Spells

	//End of Air Spells
	
	
	}
	
	
	@EventHandler
	public void OnJoin(PlayerJoinEvent e) {
		File WandLevels = new File("plugins/Spells/", "WandLevels.yml");
		FileConfiguration WandLevel = YamlConfiguration.loadConfiguration(WandLevels);
		Player player = e.getPlayer();
		String UUID = player.getUniqueId().toString();
		if(WandLevel.contains(UUID)) { //Check if player is already in config file
			if(!(PlayerLevels.containsKey(UUID))) { //Check if they are already in hashmap
				@SuppressWarnings("unchecked")
				List<String> list = (List<String>) WandLevel.get(UUID);
				HashMap<String, Double> HashLevels = StringListToHashMap(list);
				PlayerLevels.put(UUID, HashLevels);
				
			}
		} else { //If not already in config file adds it
			
			HashMap<String, Double> BlankLevels = BlankWandLevels();
			List<String> BlanklevelsString = HashMapToStringList(BlankLevels);
			WandLevel.set(UUID, BlanklevelsString);
			PlayerLevels.put(UUID, BlankLevels);
			try {
				WandLevel.save(WandLevels); //Saves config
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}

	@EventHandler
	public void OnInventoryClose(InventoryCloseEvent e) {
		Player player = (Player) e.getPlayer();
		if(e.getInventory() != null) {
			Inventory inventclosed = e.getInventory();
			Inventory invent = Bukkit.createInventory(null, 9, "Altar Crafting.");
			if(inventclosed.getName().equals(invent.getName())) {
				ItemStack[] items = e.getInventory().getContents();
				for(int i = 0; i < items.length; i ++) {
					ItemStack item = items[i];
					if(item != null) {
						if(!(item.equals(Crafter))) {
							player.sendMessage("Items are not the same");
							
							//Lets see if they are the same
							player.getInventory().addItem(item);
						}
					}
				}
			} else {
				if(Inventories.containsKey(player.getUniqueId())) {
					Inventories.remove(player.getUniqueId());
				}
			}
		}
	}
	
	@EventHandler 
	public void OnInventoryClick(InventoryClickEvent e) {
		if(!(e.isCancelled())) {
			Player player = (Player) e.getWhoClicked();
			if(e.getInventory() != null) {
				Inventory Invent = e.getClickedInventory();
				if(Invent instanceof AnvilInventory) {
					InventoryView InventView = e.getView();
					int rawSlot = e.getRawSlot();
					if(rawSlot == InventView.convertSlot(rawSlot)){
						if(rawSlot == 2) {
							ItemStack item = Invent.getItem(rawSlot);
							if(item != null) {
								ItemStack leftslotitem = Invent.getItem(0);
								ItemMeta mete = item.getItemMeta();
								List<String> Lore = new ArrayList<String>();
								Lore = mete.getLore();
								String LoreLine = Lore.get(0);
								LoreLine = ChatColor.stripColor(LoreLine);
								player.sendMessage(LoreLine);
								if(LoreLine.contains("wand")) {
									e.setCancelled(true);
									player.sendMessage(ChatColor.RED + "You can't rename a " + leftslotitem.getItemMeta().getDisplayName());
								}
							}
						}
					}
				} else {
					if(!Inventories.containsKey(player.getUniqueId())) {
						if(Invent != null) {
							if(Invent.getName().equals(invent.getName())) {
								if(e.getCurrentItem() != null) {
								ItemStack clickedItem = e.getCurrentItem();
									if(clickedItem.hasItemMeta()) {
										//GOT IT
										ItemMeta clickedItemMeta = clickedItem.getItemMeta();
										String Itemname = ChatColor.stripColor(clickedItemMeta.getDisplayName());
										player.sendMessage(Itemname);
										if(Itemname.equalsIgnoreCase("Craft")) {
											e.setCancelled(true);
											//oops we need to make slots have items in them or we will get nullpointer exception
											
											List<ItemStack> CraftingItems = new ArrayList<ItemStack>(); //Order does not matter the crafting is shapeless
											
											if(Invent.getItem(0) != null) {
												ItemStack item1 = Invent.getItem(0);
												CraftingItems.add(item1);
											}
											if(Invent.getItem(1) != null) {
												ItemStack item2 = Invent.getItem(1);
												CraftingItems.add(item2);
											}
											if(Invent.getItem(2) != null) {
												ItemStack item3 = Invent.getItem(2);
												CraftingItems.add(item3);
											}
											player.sendMessage("Checkpoint 5");
											
											if(Craft(CraftingItems) != null) {
												Invent.setItem(0, null);
												Invent.setItem(1, null);
												Invent.setItem(2, null);
												ItemStack Crafteditem = Craft(CraftingItems);
												//We can now put this in a slot for the user to remove but we will get back to that later.
												Invent.setItem(4, Crafteditem);
												
											}
										}
									}
								}
							}
						}
					} else {
						if(Invent != null) {
							if(Invent.equals(Inventories.get(player.getUniqueId()))) {
								e.setCancelled(true);
								if(e.getCurrentItem() != null) {
									ItemStack Clicked = e.getCurrentItem();
									if(Clicked.hasItemMeta()) {
										String ItemName = Clicked.getItemMeta().getDisplayName();
										ItemName = ChatColor.stripColor(ItemName);
										if(!ItemName.contains("Locked")) {
											ItemStack ItemInHand = player.getInventory().getItemInMainHand();
											ItemMeta ItemInHandMeta = ItemInHand.getItemMeta();
											List<String> ItemInHandLore = ItemInHandMeta.getLore();
											ItemInHandLore.set(1,ItemName);
											ItemInHandMeta.setLore(ItemInHandLore);
											ItemInHand.setItemMeta(ItemInHandMeta);
											player.closeInventory();
										} else {
											player.closeInventory();
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	
	
	@EventHandler
	public void OnBlockPlace(BlockPlaceEvent e) {
		Block b = e.getBlock();
		Material material = b.getType();
		if(material.equals(Material.IRON_BLOCK) | material.equals(Material.SMOOTH_BRICK))  {
			if(material.equals(Material.IRON_BLOCK)) { //if last block placed is iron, finds each of the blocks around it
				List<Location> Locationlist = new ArrayList<Location>();
				int Blockx = (int) b.getLocation().getX();
				int Blockz = (int) b.getLocation().getZ();
				for(int x = Blockx - 1; x < Blockx + 2; x++) {
					for(int z = Blockz - 1; z < Blockz + 2; z++) {
						//Gets all locations centered around iron block
						//Does not work
						Locationlist.add(new Location(b.getWorld(), x, b.getY(), z)); //adds locations to the list
					}
				}
				Locationlist.remove(b.getLocation()); //removes the iron block location
				for(int i = 0; i < Locationlist.size(); i++) {
					Location BlockLocation = Locationlist.get(i);
					if(!(BlockLocation.getBlock().getType() == Material.SMOOTH_BRICK)) { //determines NOT one of the blocks is stonebrick
						return; //Ends BlockPlaceEvent if Stonebrick not found in location.
					} else {
						
					}
				}
				
				String UUID = e.getPlayer().getUniqueId().toString();
				
				File Altars = new File("plugins/Spells/", "Altars.yml");
				
				FileConfiguration AltarsLocations = YamlConfiguration.loadConfiguration(Altars);
				
				if(AltarsLocations.get(e.getPlayer().getUniqueId().toString()) == null) {
					AltarsLocations.set(UUID, b.getLocation());
					try {
						AltarsLocations.save(Altars);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.getPlayer().sendMessage(ChatColor.BLUE + "You placed a Alter");
					
					
					b.getLocation().getWorld().spawnParticle(Particle.FLAME, b.getLocation(), 100);
					b.getLocation().getWorld().playSound(b.getLocation(), Sound.BLOCK_NOTE_CHIME, 100, 100);					
				} else {
					e.getPlayer().sendMessage(ChatColor.RED + "You can only have one Alter!");
					e.getPlayer().sendMessage(ChatColor.RED + "If you have lost your altar you can do /spells altar remove to delete it");
				}
				
				
				
				
				

				
			} else {
			//Use of other placed blocks	
			}
			
			
		}
		
		
	}
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			File Altars = new File("plugins/Spells/", "Altars.yml");
			FileConfiguration AltarsLocations = YamlConfiguration.loadConfiguration(Altars);
			if(e.getItem() != null) {
				if(e.getItem().hasItemMeta()) {
					List<String> Itemlore = e.getItem().getItemMeta().getLore();
					String FirstLore = ChatColor.stripColor(Itemlore.get(0));
					if(!(FirstLore.contains("Magic"))) {
						if(AltarsLocations.get(player.getUniqueId().toString()) == null) {
							return;
						} else {
							Location loc = (Location) AltarsLocations.get(player.getUniqueId().toString());
							Block b = e.getClickedBlock();
							Location BlockLocation = b.getLocation();
							if(loc.equals(BlockLocation)) {
								Inventory invent = Bukkit.createInventory(player, 9, "Altar Crafting.");
								invent.setItem(3, Crafter);
								player.openInventory(invent);
								
							} else {
								return;
							}
						}
					} else {
						OpenSpellDialog(e.getItem(), e.getPlayer());
					}
				}
			} else {
						if(AltarsLocations.get(player.getUniqueId().toString()) == null) {
							return;
						} else {
							Location loc = (Location) AltarsLocations.get(player.getUniqueId().toString());
							Block b = e.getClickedBlock();
							Location BlockLocation = b.getLocation();
							if(loc.equals(BlockLocation)) {
								Inventory invent = Bukkit.createInventory(player, 9, "Altar Crafting.");
								invent.setItem(3, Crafter);
								player.openInventory(invent);
								
							} else {
								return;
							}
						}
			}
			
		} else if(e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
			if(e.getItem() != null) {
				if(e.getItem().hasItemMeta()) {
					List<String> Itemlore = e.getItem().getItemMeta().getLore();
					String FirstLore = Itemlore.get(0);
					if(FirstLore.contains("Magic")) {
						OpenSpellDialog(e.getItem(), e.getPlayer());
					}
				}
			}
			
			
			
			
		} else if(e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_AIR)) {
			
			if(e.getItem() != null) {
				if(e.getItem().hasItemMeta()) {
					List<String> Itemlore = e.getItem().getItemMeta().getLore();
					String FirstLore = Itemlore.get(0);
					if(FirstLore.contains("Magic")) {
						CastSpell(e.getItem(), e.getPlayer());
					}
				}
			}
			
			
			
			
			
		}
	}
	@EventHandler
	public void OnCraft(CraftItemEvent e) {
		ItemStack[] item = e.getInventory().getMatrix();
		for(int i = 0; 9 > i; i++) {
			ItemStack Ingredient = item[i];
			if(Ingredient != null) {
				if(Ingredient.hasItemMeta()) {
					ItemMeta meta = Ingredient.getItemMeta();
					String FirstLine = meta.getLore().get(0);
					FirstLine = ChatColor.stripColor(FirstLine);
					Player player = (Player) e.getWhoClicked();
					if(FirstLine.contains("wand")) {
						player.sendMessage(ChatColor.RED + "You may not use this item in a crafting table!");
						e.setCancelled(true);
					}
				}
			}
		}
		
		
	}
	
	public static ItemStack Craft(List<ItemStack> CraftingItems) {

		
		//Set Ingredients for FireWand
		AltarRecipe FireRecipe = new AltarRecipe();
		
		AltarRecipe AirRecipe = new AltarRecipe();
		
		AltarRecipe BloodRecipe = new AltarRecipe();
		
		AltarRecipe NatureRecipe = new AltarRecipe();
		
		AltarRecipe DarkRecipe = new AltarRecipe();
		
		AltarRecipe LightRecipe = new AltarRecipe();
		
		AltarRecipe EarthRecipe = new AltarRecipe();
		
		AltarRecipe WaterRecipe = new AltarRecipe();
		
		Recipes recipes = new Recipes(false);
		
		ItemStack AirCore = recipes.AirCore;
		
		ItemStack BloodCore = recipes.BloodCore;
		
		ItemStack NatureCore = recipes.NatureCore;
		
		ItemStack EarthCore = recipes.EarthCore;
		
		ItemStack FireCore = recipes.FireCore;
		
		ItemStack WaterCore = recipes.WaterCore;
		
		ItemStack DarkCore = recipes.DarkCore;
		
		ItemStack LightCore = recipes.LightCore;
		
		ItemStack WandPart = recipes.WandPart;
		
		ItemStack AirWand = recipes.AirWand;
		
		ItemStack BloodWand = recipes.BloodWand;
		
		ItemStack NatureWand = recipes.NatureWand;
		
		ItemStack EarthWand = recipes.EarthWand;
		
		ItemStack FireWand = recipes.FireWand;
		
		ItemStack WaterWand = recipes.WaterWand;
		
		ItemStack DarkWand = recipes.DarkWand;
		
		ItemStack LightWand = recipes.LightWand;
		
		WandPart.setAmount(1); //We only need one as we will add it twice.
		//We now create our recipes
		FireRecipe.AddIngredient(FireCore, 1);
		FireRecipe.AddIngredient(WandPart, 2);
		
		WaterRecipe.AddIngredient(WaterCore, 1);
		WaterRecipe.AddIngredient(WandPart, 2);
		
		AirRecipe.AddIngredient(AirCore, 1);
		AirRecipe.AddIngredient(WandPart, 2);
		
		BloodRecipe.AddIngredient(BloodCore, 1);
		BloodRecipe.AddIngredient(WandPart, 2);
		
		EarthRecipe.AddIngredient(EarthCore, 1);
		EarthRecipe.AddIngredient(WandPart, 2);
		
		LightRecipe.AddIngredient(LightCore, 1);
		LightRecipe.AddIngredient(WandPart, 2);
		
		NatureRecipe.AddIngredient(NatureCore, 1);
		NatureRecipe.AddIngredient(WandPart, 2);
		
		DarkRecipe.AddIngredient(DarkCore, 1);
		DarkRecipe.AddIngredient(WandPart, 2);
		//End of creation
		
		
		if(listEqualsNoOrder(CraftingItems, FireRecipe.Ingredients)) {
			return FireWand;
		} else if(listEqualsNoOrder(CraftingItems, WaterRecipe.Ingredients)) {
			return WaterWand;
		} else if(listEqualsNoOrder(CraftingItems, AirRecipe.Ingredients)) {
			return AirWand;
		} else if(listEqualsNoOrder(CraftingItems, BloodRecipe.Ingredients)) {
			return BloodWand;
		} else if(listEqualsNoOrder(CraftingItems, EarthRecipe.Ingredients)) {
			return EarthWand;
		} else if(listEqualsNoOrder(CraftingItems, LightRecipe.Ingredients)) {
			return LightWand;
		} else if(listEqualsNoOrder(CraftingItems, NatureRecipe.Ingredients)) {
			return NatureWand;
		} else if(listEqualsNoOrder(CraftingItems, DarkRecipe.Ingredients)) {
			return DarkWand;
		}
			
		
		return null;
	}
	
	
	public static <T> boolean listEqualsNoOrder(List<T> l1, List<T> l2) { //Copied this it just removes order
	    final Set<T> s1 = new HashSet<>(l1);
	    final Set<T> s2 = new HashSet<>(l2);

	    return s1.equals(s2);
	}
	
	
	public static HashMap<String, Double> BlankWandLevels() {
		
		HashMap<String, Double> Levels = new HashMap<>();
		
		Levels.put("FireLevel", (double) 1);
		Levels.put("WaterLevel",(double) 1);
		Levels.put("BloodLevel",(double) 1);
		Levels.put("AirLevel",(double) 1);
		Levels.put("DarkLevel",(double) 1);
		Levels.put("LightLevel",(double) 1);
		Levels.put("NatureLevel",(double) 1);
		Levels.put("EarthLevel", (double) 1);
		
		return Levels;
		
	}
	
	
	public static List<String> HashMapToStringList(HashMap<String, Double> blankLevels)  {
		
		Set<String> key = blankLevels.keySet();
		
		Iterator<String> Keys = key.iterator();
		
		
		List<String> Values = new ArrayList<String>();
		
		
		while(Keys.hasNext()) {
			String NextKey = Keys.next();
			Values.add(NextKey + ": " + blankLevels.get(NextKey));
		}
		
		
		return Values;
		
	}
	
	
	public static HashMap<String, Double> StringListToHashMap(List<String> List) {
		
		HashMap<String, Double> hashMap = new HashMap<>();
		
		for (int i = 0; i < List.size(); i++) {
			
			String item = List.get(i);
			
			String[] Items = item.split(":");
			
			String LevelName = Items[0];
			
			String LevelNumber = Items[1];
			
			LevelNumber = LevelNumber.replaceAll(" ", "");
			
			LevelName = LevelName.replaceAll(" ", "");
			
			
			
			hashMap.put(LevelName, Double.parseDouble(LevelNumber));
			
		}
		
		return hashMap;
		
	}
	
	
	public void OpenSpellDialog(ItemStack item, Player player) {
		if(item.getType().equals(Material.STICK)) {
			
			String MagicType = "";
			
			Recipes recipes = new Recipes(false);
			
			ItemStack AirWand = recipes.AirWand;
			
			ItemStack BloodWand = recipes.BloodWand;
			
			ItemStack NatureWand = recipes.NatureWand;
			
			ItemStack EarthWand = recipes.EarthWand;
			
			ItemStack FireWand = recipes.FireWand;
			
			ItemStack WaterWand = recipes.WaterWand;
			
			ItemStack DarkWand = recipes.DarkWand;
			
			ItemStack LightWand = recipes.LightWand;
			
			List<String> AirLore = AirWand.getItemMeta().getLore();
			
			List<String> BloodLore = BloodWand.getItemMeta().getLore();
			
			List<String> NatureLore = NatureWand.getItemMeta().getLore();
			
			List<String> EarthLore = EarthWand.getItemMeta().getLore();
			
			List<String> FireLore = FireWand.getItemMeta().getLore();
			
			List<String> WaterLore = WaterWand.getItemMeta().getLore();
			
			List<String> DarkLore = DarkWand.getItemMeta().getLore();
			
			List<String> LightLore = LightWand.getItemMeta().getLore();
			
			if(item.hasItemMeta()) {
				if(item.getItemMeta().hasLore()) {
					List<String> ItemLore = item.getItemMeta().getLore();
					String Firstline = ItemLore.get(0);
					if(Firstline.equals(AirLore.get(0))) {
						MagicType = "Air";
						CreateInventory(MagicType, player);
					} else if (Firstline.equals(BloodLore.get(0))) {
						MagicType = "Blood";
						CreateInventory(MagicType, player);
					} else if (Firstline.equals(NatureLore.get(0))) {
						MagicType = "Nature";
						CreateInventory(MagicType, player);
					} else if (Firstline.equals(EarthLore.get(0))) {
						MagicType = "Earth";
						CreateInventory(MagicType, player);
					} else if (Firstline.equals(FireLore.get(0))) {
						MagicType = "Fire";
						CreateInventory(MagicType, player);
					} else if (Firstline.equals(WaterLore.get(0))) {
						MagicType = "Water";
						CreateInventory(MagicType, player);
					} else if (Firstline.equals(DarkLore.get(0))) {
						MagicType = "Dark";
						CreateInventory(MagicType, player);
					} else if (Firstline.equals(LightLore.get(0))) {
						MagicType = "Light";
						CreateInventory(MagicType, player);
					}
				}
			}
		}
	}
	
	public void CastSpell(ItemStack item, Player player) {
		
		
		
		
		
		
		
		
		
		
	}
	
	
	public void CreateInventory(String MagicType, Player player) {
		Inventory SpellInventory = Bukkit.createInventory(null, 54, MagicType + " Spells");
		
		String UUID = player.getUniqueId().toString();
		
		HashMap<String, Double> PlayerLevel = PlayerLevels.get(UUID);
		
		String MagicLevel = MagicType + "Level";
		
		player.sendMessage(MagicLevel);
		
		double Level = PlayerLevel.get(MagicLevel);
		
		if(MagicType == "Air") {
			//Okay i will create a HASHMAP!!! We can then set the required level for each spell making life 100% easier
			
			HashMap<Integer,ItemStack > AirSpells = new HashMap<>();
			int LevelNeeded = 1;
			AirSpells.put(LevelNeeded,Gust);
			LevelNeeded++;
			AirSpells.put(LevelNeeded,ArrowStorm);
			LevelNeeded++;
			AirSpells.put(LevelNeeded,AntiProj);
			LevelNeeded++;
			AirSpells.put(LevelNeeded, Airbubble);
			LevelNeeded++;
			AirSpells.put(LevelNeeded,Disappear);
			LevelNeeded++;
			AirSpells.put(LevelNeeded,Tornado);
			LevelNeeded++;
			player.sendMessage("Checkpoint 1");
			
			for(int i = 1; i < AirSpells.size()+1; i++) {
				ItemStack Spell = AirSpells.get(i);
				
				if(i <= Level) {
					SpellInventory.setItem(i-1, Spell);
					player.sendMessage("Checkpoint 2");
				} else {
					ItemMeta SpellMeta = Spell.getItemMeta();
					ItemStack Locked = new ItemStack(Material.BARRIER);
					ItemMeta LockedMeta = Locked.getItemMeta();
					List<String> ItemLore = SpellMeta.getLore();
					LockedMeta.setLore(ItemLore);
					LockedMeta.setDisplayName(ChatColor.RED + "Locked " + ChatColor.stripColor(SpellMeta.getDisplayName()));
					Locked.setItemMeta(LockedMeta);
					SpellInventory.setItem(i-1, Locked);
					player.sendMessage("Checkpoint 3");
				}
				
			}	
		} else if(MagicType == "Blood") {
			
//Okay i will create a HASHMAP!!! We can then set the required level for each spell making life 100% easier
			
			HashMap<Integer,ItemStack > BloodSpells = new HashMap<>();
			int LevelNeeded = 1;
			BloodSpells.put(LevelNeeded,Thorns);
			LevelNeeded++;
			BloodSpells.put(LevelNeeded,BloodRage);
			LevelNeeded++;
			BloodSpells.put(LevelNeeded,ViperStrike);
			LevelNeeded++;
			BloodSpells.put(LevelNeeded, SapLife);
			LevelNeeded++;
			BloodSpells.put(LevelNeeded,BloodTrial);;
			player.sendMessage("Checkpoint 1");
			
			for(int i = 1; i < BloodSpells.size()+1; i++) {
				ItemStack Spell = BloodSpells.get(i);
				
				if(i <= Level) {
					SpellInventory.setItem(i-1, Spell);
					player.sendMessage("Checkpoint 2");
				} else {
					ItemMeta SpellMeta = Spell.getItemMeta();
					ItemStack Locked = new ItemStack(Material.BARRIER);
					ItemMeta LockedMeta = Locked.getItemMeta();
					List<String> ItemLore = SpellMeta.getLore();
					LockedMeta.setLore(ItemLore);
					LockedMeta.setDisplayName(ChatColor.RED + "Locked " + ChatColor.stripColor(SpellMeta.getDisplayName()));
					Locked.setItemMeta(LockedMeta);
					SpellInventory.setItem(i-1, Locked);
					player.sendMessage("Checkpoint 3");
				}
				
			}		
			
		} else if(MagicType == "Nature") {
			
			HashMap<Integer,ItemStack > NatureSpells = new HashMap<>();
			int LevelNeeded = 1;
			NatureSpells.put(LevelNeeded,Gust);
			LevelNeeded++;
			NatureSpells.put(LevelNeeded,ArrowStorm);
			LevelNeeded++;
			NatureSpells.put(LevelNeeded,AntiProj);
			LevelNeeded++;
			NatureSpells.put(LevelNeeded, Airbubble);
			LevelNeeded++;
			NatureSpells.put(LevelNeeded,Disappear);
			LevelNeeded++;
			NatureSpells.put(LevelNeeded,Tornado);
			LevelNeeded++;
			player.sendMessage("Checkpoint 1");
			
			for(int i = 1; i < NatureSpells.size()+1; i++) {
				ItemStack Spell = NatureSpells.get(i);
				
				if(i <= Level) {
					SpellInventory.setItem(i-1, Spell);
					player.sendMessage("Checkpoint 2");
				} else {
					ItemMeta SpellMeta = Spell.getItemMeta();
					ItemStack Locked = new ItemStack(Material.BARRIER);
					ItemMeta LockedMeta = Locked.getItemMeta();
					List<String> ItemLore = SpellMeta.getLore();
					LockedMeta.setLore(ItemLore);
					LockedMeta.setDisplayName(ChatColor.RED + "Locked " + ChatColor.stripColor(SpellMeta.getDisplayName()));
					Locked.setItemMeta(LockedMeta);
					SpellInventory.setItem(i-1, Locked);
					player.sendMessage("Checkpoint 3");
				}
				
			}	

			
		} else if (MagicType == "Earth") {
			
			

			HashMap<Integer,ItemStack > EarthSpells = new HashMap<>();
			int LevelNeeded = 1;
			EarthSpells.put(LevelNeeded,Gust);
			LevelNeeded++;
			EarthSpells.put(LevelNeeded,ArrowStorm);
			LevelNeeded++;
			EarthSpells.put(LevelNeeded,AntiProj);
			LevelNeeded++;
			EarthSpells.put(LevelNeeded, Airbubble);
			LevelNeeded++;
			EarthSpells.put(LevelNeeded,Disappear);
			LevelNeeded++;
			EarthSpells.put(LevelNeeded,Tornado);
			LevelNeeded++;
			player.sendMessage("Checkpoint 1");
			
			for(int i = 1; i < EarthSpells.size()+1; i++) {
				ItemStack Spell = EarthSpells.get(i);
				
				if(i <= Level) {
					SpellInventory.setItem(i-1, Spell);
					player.sendMessage("Checkpoint 2");
				} else {
					ItemMeta SpellMeta = Spell.getItemMeta();
					ItemStack Locked = new ItemStack(Material.BARRIER);
					ItemMeta LockedMeta = Locked.getItemMeta();
					List<String> ItemLore = SpellMeta.getLore();
					LockedMeta.setLore(ItemLore);
					LockedMeta.setDisplayName(ChatColor.RED + "Locked " + ChatColor.stripColor(SpellMeta.getDisplayName()));
					Locked.setItemMeta(LockedMeta);
					SpellInventory.setItem(i-1, Locked);
					player.sendMessage("Checkpoint 3");
				}
				
			}	
			
			
		} else if(MagicType == "Fire") {
			
		} else if(MagicType == "Water") {
			
		} else if (MagicType ==  "Dark") {
			
		} else if (MagicType == "Light") {
			
		}
		
		
		player.sendMessage("Checkpoint 4");
		
		player.openInventory(SpellInventory);
		Inventories.put(player.getUniqueId(), SpellInventory);
		
	}
	
	public boolean AwardMagicXP() {
		return true;
	}
	
	public ItemStack CreateSpell(String SpellName, Material Material, String Description, String Damage, String Mana) {
		ItemStack Spell = new ItemStack(Material);
		ItemMeta ItemMeta = Spell.getItemMeta();
		ItemMeta.setDisplayName(SpellName);
		List<String> ItemLore = new ArrayList<String>();
		ItemLore.add(Description);
		ItemLore.add(ChatColor.RED + "Hearts Damage: " + Damage);
		ItemLore.add(ChatColor.AQUA + "Mana: " + Mana);
		ItemMeta.setLore(ItemLore);
		Spell.setItemMeta(ItemMeta);
		return Spell;
		
		 
	}
	
	
}

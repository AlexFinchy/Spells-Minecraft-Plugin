package me.alexalien;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Recipes {
	
	public ItemStack FireCore = new ItemStack(Material.NETHER_STAR);
	
	public ItemStack WaterCore = new ItemStack(Material.NETHER_STAR);
	
	public ItemStack EarthCore = new ItemStack(Material.NETHER_STAR);
	
	public ItemStack BloodCore = new ItemStack(Material.NETHER_STAR);
	
	public ItemStack AirCore = new ItemStack(Material.NETHER_STAR);
	
	public ItemStack NatureCore = new ItemStack(Material.NETHER_STAR);
	
	public ItemStack DarkCore = new ItemStack(Material.NETHER_STAR);
	
	public ItemStack LightCore = new ItemStack(Material.NETHER_STAR);
	
	public ItemStack WandPart = new ItemStack(Material.STICK);
	
	public ItemStack FireWand = new  ItemStack(Material.STICK);
	
	public ItemStack DarkWand = new  ItemStack(Material.STICK);
	
	public ItemStack BloodWand = new  ItemStack(Material.STICK);
	
	public ItemStack LightWand = new  ItemStack(Material.STICK);
	
	public ItemStack WaterWand = new  ItemStack(Material.STICK);
	
	public ItemStack AirWand = new  ItemStack(Material.STICK);
	
	public ItemStack EarthWand = new  ItemStack(Material.STICK);
	
	public ItemStack NatureWand = new  ItemStack(Material.STICK);
	
	public Recipes(boolean MainClass) {
		
		
		
		

		
		ItemMeta FireCoreMeta = FireCore.getItemMeta();
		FireCoreMeta.setDisplayName(ChatColor.AQUA + "FireCore");
		List<String> FireCoreLore = new ArrayList<String>();
		FireCoreLore.add(ChatColor.RED + "Use with staff to create Firewand");
		FireCoreMeta.setLore(FireCoreLore);
		FireCore.setItemMeta(FireCoreMeta);
		
		
		ItemMeta WaterCoreMeta = WaterCore.getItemMeta();
		WaterCoreMeta.setDisplayName(ChatColor.AQUA + "WaterCore");
		List<String> WaterCoreLore = new ArrayList<String>();
		WaterCoreLore.add(ChatColor.AQUA + "Use with staff to create Waterwand");
		WaterCoreMeta.setLore(WaterCoreLore);
		WaterCore.setItemMeta(WaterCoreMeta);
		
		
		ItemMeta EarthCoreMeta = EarthCore.getItemMeta();
		EarthCoreMeta.setDisplayName(ChatColor.AQUA + "EarthCore");
		List<String> EarthCoreLore = new ArrayList<String>();
		EarthCoreLore.add(ChatColor.DARK_GREEN + "Use with staff to create Earthwand");
		EarthCoreMeta.setLore(EarthCoreLore);
		EarthCore.setItemMeta(EarthCoreMeta);
		
		

		ItemMeta BloodCoreMeta = BloodCore.getItemMeta();
		BloodCoreMeta.setDisplayName(ChatColor.AQUA + "BloodCore");
		List<String> BloodCoreLore = new ArrayList<String>();
		BloodCoreLore.add(ChatColor.DARK_RED + "Use with staff to create Bloodwand");
		BloodCoreMeta.setLore(BloodCoreLore);
		BloodCore.setItemMeta(BloodCoreMeta);
		
		ItemMeta AirCoreMeta = AirCore.getItemMeta();
		AirCoreMeta.setDisplayName(ChatColor.AQUA + "AirCore");
		List<String> AirCoreLore = new ArrayList<String>();
		AirCoreLore.add(ChatColor.GRAY + "Use with staff to create Airwand");
		AirCoreMeta.setLore(AirCoreLore);
		AirCore.setItemMeta(AirCoreMeta);
		
		ItemMeta NatureCoreMeta = NatureCore.getItemMeta();
		NatureCoreMeta.setDisplayName(ChatColor.AQUA + "NatureCore");
		List<String> NatureCoreLore = new ArrayList<String>();
		NatureCoreLore.add(ChatColor.GREEN + "Use with staff to create Naturewand");
		NatureCoreMeta.setLore(NatureCoreLore);
		NatureCore.setItemMeta(NatureCoreMeta);
		
		ItemMeta DarkCoreMeta = DarkCore.getItemMeta();
		DarkCoreMeta.setDisplayName(ChatColor.AQUA + "DarkCore");
		List<String> DarkCoreLore = new ArrayList<String>();
		DarkCoreLore.add(ChatColor.BLACK + "Use with staff to create Darkwand");
		DarkCoreMeta.setLore(DarkCoreLore);
		DarkCore.setItemMeta(DarkCoreMeta);
		
		ItemMeta LightCoreMeta = LightCore.getItemMeta();
		LightCoreMeta.setDisplayName(ChatColor.AQUA + "LightCore");
		List<String> LightCoreLore = new ArrayList<String>();
		LightCoreLore.add(ChatColor.WHITE + "Use with staff to create Lightwand");
		LightCoreMeta.setLore(LightCoreLore);
		LightCore.setItemMeta(LightCoreMeta);
		
		
		//Core Recipes Done
		NamespacedKey Lightkey = NamespacedKey.minecraft("LightCore");
		ShapedRecipe LightCoreRecipe = new ShapedRecipe(Lightkey, LightCore);
		LightCoreRecipe.shape("$%$","%^%","$%$").setIngredient('$', Material.GLOWSTONE).setIngredient('%', Material.GOLD_BLOCK).setIngredient('^', Material.SEA_LANTERN);
		NamespacedKey Darkkey = NamespacedKey.minecraft("DarkCore");
		ShapedRecipe DarkCoreRecipe = new ShapedRecipe(Darkkey, DarkCore);
		DarkCoreRecipe.shape("$!$","%^%","$!$").setIngredient('$', Material.COAL_BLOCK).setIngredient('%', Material.EYE_OF_ENDER ).setIngredient('^', Material.ENDER_CHEST).setIngredient('!', Material.OBSIDIAN);
		NamespacedKey Naturekey = NamespacedKey.minecraft("NatureCore");
		ShapedRecipe NatureCoreRecipe = new ShapedRecipe(Naturekey, NatureCore);
		NatureCoreRecipe.shape("$%$","%^%","$!$").setIngredient('$', Material.VINE).setIngredient('%', Material.GRASS).setIngredient('^', Material.GOLDEN_APPLE).setIngredient('!', Material.STONE);
		NamespacedKey Firekey = NamespacedKey.minecraft("FireCore");
		ShapedRecipe FireCoreRecipe = new ShapedRecipe(Firekey, FireCore);
		FireCoreRecipe.shape("$%$","%^%","$%$").setIngredient('$', Material.BLAZE_ROD).setIngredient('%', Material.FIREBALL).setIngredient('^', Material.MAGMA_CREAM);
		NamespacedKey Airkey = NamespacedKey.minecraft("AirCore");
		ShapedRecipe AirCoreRecipe = new ShapedRecipe(Airkey, AirCore);
		AirCoreRecipe.shape("$%$","%^%","$%$").setIngredient('$', Material.QUARTZ_BLOCK).setIngredient('%', Material.GLASS).setIngredient('^', Material.GHAST_TEAR);
		NamespacedKey Waterkey = NamespacedKey.minecraft("WaterCore");
		ShapedRecipe WaterCoreRecipe = new ShapedRecipe(Waterkey, WaterCore);
		WaterCoreRecipe.shape("$%$","%^%","$%$").setIngredient('$', Material.PRISMARINE).setIngredient('%', Material.WATER_BUCKET).setIngredient('^', Material.BEACON);
		NamespacedKey Bloodkey = NamespacedKey.minecraft("BloodCore");
		ShapedRecipe BloodCoreRecipe = new ShapedRecipe(Bloodkey, BloodCore);
		BloodCoreRecipe.shape("$%^","%@%","^%$").setIngredient('$', Material.REDSTONE_BLOCK).setIngredient('%', Material.FERMENTED_SPIDER_EYE).setIngredient('^', Material.NETHER_BRICK).setIngredient('@', Material.SKULL);
		NamespacedKey Earthkey = NamespacedKey.minecraft("EarthCore");
		ShapedRecipe EarthCoreRecipe = new ShapedRecipe(Earthkey, EarthCore);
		EarthCoreRecipe.shape("$%^","%@%","^%$").setIngredient('$', Material.COAL_ORE).setIngredient('%', Material.SMOOTH_BRICK).setIngredient('^', Material.EMERALD).setIngredient('@', Material.SKULL);
		//End of Core Recipes
		
		
		
		
		//Wand Part
		ItemMeta WandPartMeta = WandPart.getItemMeta();
		WandPartMeta.setDisplayName(ChatColor.AQUA + "Wand Part");
		List<String> WandPartLore = new ArrayList<String>();
		WandPartLore.add(ChatColor.DARK_GRAY + "A part of a wand");
		WandPartMeta.setLore(WandPartLore);

		WandPart.setAmount(2);
		WandPart.setItemMeta(WandPartMeta);
		
		NamespacedKey Wandkey = NamespacedKey.minecraft("WandPart");
		ShapedRecipe WandPartRecipe = new ShapedRecipe(Wandkey, WandPart);
		WandPartRecipe.shape("  $"," & ","&  ").setIngredient('$', Material.NETHER_STAR).setIngredient('&', Material.STICK);
		
		//End Wand Part
		
		
		
		
		
		
		
		
		

		
		//Creating Craftable Items-Have to update old code.
		ItemMeta DarkMeta = DarkWand.getItemMeta();
		DarkMeta.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Dark Wand");
		List<String> DarkLore = new ArrayList<String>();
		DarkLore.add(ChatColor.DARK_GRAY + "Wand to use Dark Magic");
	    DarkLore.add("SinisterArrow");
	    DarkMeta.setLore(DarkLore);
	    DarkWand.setItemMeta(DarkMeta);
	    
		//
		ItemMeta BloodMeta = BloodWand.getItemMeta();
		BloodMeta.setDisplayName(ChatColor.DARK_RED + "" +ChatColor.BOLD + "Blood Wand");
		List<String> BloodLore = new ArrayList<String>();
		BloodLore.add(ChatColor.DARK_RED + "Wand to use Blood Magic");
		BloodLore.add("Bloodrage");
		BloodMeta.setLore(BloodLore);
		BloodWand.setItemMeta(DarkMeta);
		
		//
		ItemMeta LightMeta = LightWand.getItemMeta();
		LightMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Light Wand");
		List<String> LightLore = new ArrayList<String>();
		LightLore.add(ChatColor.WHITE + "Wand to use Light Magic");
		LightLore.add("Holyarrow");
		LightMeta.setLore(LightLore);
		LightWand.setItemMeta(LightMeta);
		
		//
		ItemMeta WaterMeta = WaterWand.getItemMeta();
		WaterMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Water Wand");
		List<String> WaterLore = new ArrayList<String>();
	    WaterLore.add(ChatColor.AQUA + "Wand to use Water Magic");
	    WaterLore.add("Freeze");
	    WaterMeta.setLore(WaterLore);
	    WaterWand.setItemMeta(WaterMeta);
	    
		//
		ItemMeta AirMeta = AirWand.getItemMeta();
		AirMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Air Wand");
		List<String> AirLore = new ArrayList<String>();
	    AirLore.add(ChatColor.GRAY + "Wand to use Air Magic");
	    AirLore.add("Gust");
	    AirMeta.setLore(AirLore);
	    AirWand.setItemMeta(AirMeta);
	    
		//
		ItemMeta EarthMeta = EarthWand.getItemMeta();
		EarthMeta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Earth Wand");
		List<String> EarthLore = new ArrayList<String>();
	    EarthLore.add(ChatColor.DARK_GREEN + "Wand to use Earth Magic");
	    EarthLore.add("Earthquake");
	    EarthMeta.setLore(EarthLore);
	    EarthWand.setItemMeta(EarthMeta);
	    
		//
		ItemMeta NatureMeta = NatureWand.getItemMeta();
		NatureMeta.setDisplayName(ChatColor.GREEN + "" +ChatColor.BOLD + "Nature Wand");
		List<String> NatureLore = new ArrayList<String>();
	    NatureLore.add(ChatColor.GREEN + "Wand to use Nature Magic");
	    NatureLore.add("Thunder");
	    NatureMeta.setLore(NatureLore);
	    NatureWand.setItemMeta(NatureMeta);
	    
	    
	    ItemMeta FireMeta = FireWand.getItemMeta();
	    FireMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Fire Wand");
	    List<String> FireLore = new ArrayList<String>();
	    FireLore.add(ChatColor.RED + "Wand to use Fire Magic");
	    FireLore.add("Fireball");
	    FireMeta.setLore(FireLore);
	    FireWand.setItemMeta(FireMeta);
		
		//
		
		//We create the new recipe type but do not set shape
		
		//Where actual recipes are
		// Add them to the server ^^ This is a test
		//Normal Craftable Items
	    if(MainClass) {
			Bukkit.getServer().addRecipe(FireCoreRecipe);
			Bukkit.getServer().addRecipe(WaterCoreRecipe);
			Bukkit.getServer().addRecipe(BloodCoreRecipe);
			Bukkit.getServer().addRecipe(EarthCoreRecipe);
			Bukkit.getServer().addRecipe(LightCoreRecipe);
			Bukkit.getServer().addRecipe(DarkCoreRecipe);
			Bukkit.getServer().addRecipe(NatureCoreRecipe);
			Bukkit.getServer().addRecipe(AirCoreRecipe);
			Bukkit.getServer().addRecipe(WandPartRecipe);
	    }

		
	}	
	
	
	
	
}


package me.alexalien;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

public class AltarRecipe {

	public List<ItemStack> Ingredients = new ArrayList<ItemStack>();
	

	public void AddIngredient(ItemStack Ingredient, int Amount) {
		for(int i = 0; i < Amount; i++) {
			Ingredients.add(Ingredient);
		}	
	}
	
	
}

package net.lsoffice.extarmour.armour;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class ArmourSet implements Listener {
	public ItemStack helmet;
	public ItemStack chestplate;
	public ItemStack leggings;
	public ItemStack boots;
	public Player player;
	public ShapedRecipe recipeHelmet;
	public ShapedRecipe recipeChestplate;
	public ShapedRecipe recipeLeggings;
	public ShapedRecipe recipeBoots;
	
	public ArmourSet() {
		// define helmet, chestplate, leggings & boots
	}
	
	public ItemStack getHelmet() {
		return helmet;
	}
	
	public ItemStack getChesplate() {
		return chestplate;
	}
	
	public ItemStack getLeggings() {
		return leggings;
	}
	
	public ItemStack getBoots() {
		return boots;
	}
	
	public boolean hasFullSet() {
		if (!this.player.getInventory().getHelmet().getItemMeta().getLore().equals(this.helmet.getItemMeta().getLore())) return false;
		if (!this.player.getInventory().getChestplate().getItemMeta().getLore().equals(this.chestplate.getItemMeta().getLore())) return false;
		if (!this.player.getInventory().getLeggings().getItemMeta().getLore().equals(this.leggings.getItemMeta().getLore())) return false;
		if (!this.player.getInventory().getBoots().getItemMeta().getLore().equals(this.boots.getItemMeta().getLore())) return false;
		
		return true;
	}
	
	public void giveArmour() {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>(Arrays.asList(this.helmet, this.chestplate, this.leggings, this.boots));
		
		for (ItemStack i: list) {
			if (this.player.getInventory().firstEmpty() == -1) {
				this.player.getInventory().addItem(i);
			}
			else {
				this.player.getWorld().dropItemNaturally(this.player.getLocation(), i);
			}
		}
	}
	
	public ArrayList<ShapedRecipe> getRecipe() {
		return new ArrayList<ShapedRecipe>(Arrays.asList(this.recipeHelmet, this.recipeChestplate, this.recipeLeggings, this.recipeBoots));
	}
}
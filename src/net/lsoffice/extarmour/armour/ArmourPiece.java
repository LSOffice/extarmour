package net.lsoffice.extarmour.armour;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.lsoffice.extarmour.armour.ability.FullSetAbility;
import net.lsoffice.extarmour.armour.ability.PieceAbility;

public class ArmourPiece {
	
	Material material;
	String name;
	PieceAbility pieceAbility;
	FullSetAbility fullSetAbility;
	
	public ArmourPiece(Material material, String name, PieceAbility pieceAbility, FullSetAbility fullSetAbility) {
		this.material = material;
		this.name = name;
		this.pieceAbility = pieceAbility;
		this.fullSetAbility = fullSetAbility;
	}
	
	//return
	public Material getMaterial() {
		return material;
	}
	
	//return
	public String getName() {
		return name;
	}
	
	//return
	public PieceAbility getPieceAbility() {
		return pieceAbility;
	}
	
	//return
	public FullSetAbility getFullSetAbility() {
		return fullSetAbility;
	}
	
	//return
	public ItemStack getArmourPiece() {
		ItemStack result = new ItemStack(material);
		ItemMeta meta = result.getItemMeta();
		meta.setDisplayName(name);
		List<String> lore = new ArrayList<String>();
		if (fullSetAbility != null) {
			for (String s1: fullSetAbility.getLore()) {
				lore.add(s1);
			}
		}
		
		if (pieceAbility != null) {
			for (String s: pieceAbility.getLore()) {
				lore.add(s);
			}
		}
		
		meta.setLore(lore);
		result.setItemMeta(meta);
		return result;
	}
	
}

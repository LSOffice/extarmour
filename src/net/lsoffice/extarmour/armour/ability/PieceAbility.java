package net.lsoffice.extarmour.armour.ability;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PieceAbility implements Listener {

	String name;
	HashMap<String, ArrayList<String>> abilitiesList = new HashMap<String, ArrayList<String>>(); //Name, Description
	String colour = "§6§l";
	
	//get
	public PieceAbility addAbility(String name, ArrayList<String> description) {
		abilitiesList.put(name, description);
		
		return this;
	}
	
	//return
	public ArrayList<String> getLore() {
		ArrayList<String> lore = new ArrayList<String>();
		if (abilitiesList.size() > 1) {
			lore.add(colour + "PIECE ABILITY: ");
		}
		else {
			lore.add(colour + "PIECE ABILITIES: ");
		}
		
		for (String name: abilitiesList.keySet()) {
			lore.add("§7" + name);
			for (String s1: abilitiesList.get(name)) {
				lore.add("§7" + s1);
			}
			
			lore.add("§f");
		}
		
		return lore;
	}
	
	@EventHandler
	public void onBlockBreak(Runnable runnable) {
		runnable.run();
	}
	
}

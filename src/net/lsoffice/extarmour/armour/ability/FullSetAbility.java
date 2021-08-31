package net.lsoffice.extarmour.armour.ability;

import java.util.ArrayList;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FullSetAbility implements Listener {

	String name;
	ArrayList<String> description;
	String colour = "§6§l";

	public FullSetAbility(String name, ArrayList<String> description) {
		this.name = name;
		this.description = description;
	}

	//return
	public ArrayList<String> getLore() {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(colour + "FULL SET ABILITY: " + name);
		for (String s: description) {
			lore.add("§7" + s);
		}
		lore.add("§f");

		return lore;
	}

	@EventHandler
	public void onBlockBreak(Runnable runnable) {
		runnable.run();
	}
}

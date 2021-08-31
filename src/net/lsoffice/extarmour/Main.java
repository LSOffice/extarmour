package net.lsoffice.extarmour;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import net.lsoffice.extarmour.events.KillEvents;
import net.lsoffice.extarmour.events.ThrowawayEvents;
import resources.ArmorEquipEvent.ArmorListener;
import resources.ArmorEquipEvent.DispenserArmorListener;

public class Main extends JavaPlugin implements Listener {

	public static JavaPlugin INSTANCE;
	
	@Override
	public void onEnable() {
		INSTANCE = this;
		getCommand("ea").setExecutor(new Commands());

		getServer().getPluginManager().registerEvents(new ArmorListener(), this);
		getServer().getPluginManager().registerEvents(new DispenserArmorListener(), this);
		getServer().getPluginManager().registerEvents(new ThrowawayEvents(), this);
		getServer().getPluginManager().registerEvents(new KillEvents(), this);
	}
	
	@Override
	public void onDisable() {
		
	}
}

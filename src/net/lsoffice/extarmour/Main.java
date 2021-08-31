package net.lsoffice.extarmour;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.lsoffice.extarmour.armour.custom.UndeadArmour;
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
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (Commands.map.containsKey(event.getPlayer())) return;
		Commands.map.put(event.getPlayer(), new UndeadArmour(event.getPlayer()));
		getServer().getPluginManager().registerEvents(Commands.map.get(event.getPlayer()), Main.INSTANCE);
	}
}

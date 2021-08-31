package net.lsoffice.extarmour.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ShapedRecipe;

import net.lsoffice.extarmour.Commands;
import net.lsoffice.extarmour.Main;
import net.lsoffice.extarmour.armour.custom.UndeadArmour;

public class ThrowawayEvents implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (Commands.map.containsKey(event.getPlayer())) return;
		Commands.map.put(event.getPlayer(), new UndeadArmour(event.getPlayer()));
		Main.INSTANCE.getServer().getPluginManager().registerEvents(Commands.map.get(event.getPlayer()), Main.INSTANCE);
		
		for (ShapedRecipe s: Commands.map.get(event.getPlayer()).getRecipe()) {
			Main.INSTANCE.getServer().addRecipe(s);
		}
	}
	
}

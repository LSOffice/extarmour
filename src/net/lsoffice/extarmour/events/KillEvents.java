package net.lsoffice.extarmour.events;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import net.lsoffice.extarmour.Commands;
import resources.Random;

public class KillEvents implements Listener {
	
	@EventHandler
	public void getUndeadArmourEvent(EntityDeathEvent event) {
		if (!(event.getEntity().getKiller() instanceof Player)) return;
		if (!(event.getEntity().getType().equals(EntityType.ZOMBIE))) return;
		Player player = (Player) event.getEntity().getKiller();
		
		int randInt = Random.randInt(1, 4);
		if (randInt == 1) {
			int randInt2 = Random.randInt(1, 10);
			if (randInt2 == 1) {
				player.getWorld().dropItemNaturally(event.getEntity().getLocation(), Commands.map.get(player).helmet);
				player.sendMessage("§aYou killed a zombie and it dropped a piece of undead armour");
			}
			
		}
		else if (randInt == 2) {
			int randInt2 = Random.randInt(1, 10);
			if (randInt2 == 1) {
				player.getWorld().dropItemNaturally(event.getEntity().getLocation(), Commands.map.get(player).chestplate);
				player.sendMessage("§aYou killed a zombie and it dropped a piece of undead armour");
			}
			
		}
		else if (randInt == 3) {
			int randInt2 = Random.randInt(1, 10);
			if (randInt2 == 1) {
				player.getWorld().dropItemNaturally(event.getEntity().getLocation(), Commands.map.get(player).leggings);
				player.sendMessage("§aYou killed a zombie and it dropped a piece of undead armour");
			}
			
		}
		else if (randInt == 4) {
			int randInt2 = Random.randInt(1, 10);
			if (randInt2 == 1) {
				player.getWorld().dropItemNaturally(event.getEntity().getLocation(), Commands.map.get(player).boots);
				player.sendMessage("§aYou killed a zombie and it dropped a piece of undead armour");
			}
			
		}
	}
	
}

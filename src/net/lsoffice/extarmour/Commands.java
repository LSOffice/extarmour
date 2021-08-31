package net.lsoffice.extarmour;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.lsoffice.extarmour.armour.ArmourSet;
import net.lsoffice.extarmour.armour.custom.UndeadArmour;

public class Commands implements CommandExecutor {
	
	public static HashMap<Player, ArmourSet> map = new HashMap<Player, ArmourSet>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("[ExtArmour] Only players can use this command!");
			return true;
		}
		if (!label.equalsIgnoreCase("ea")) return false;
		Player player = (Player) sender;
		
		if (args[0].equals("undead")) {
			
			if (args[1].equals("give")) {
				player.sendMessage(map.toString());
				map.get(player).giveArmour();
			}
		}
		
		return false;
		
	}
}

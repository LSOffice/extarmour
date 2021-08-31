package net.lsoffice.extarmour.armour.custom;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.lsoffice.extarmour.Main;
import net.lsoffice.extarmour.armour.ArmourPiece;
import net.lsoffice.extarmour.armour.ArmourSet;
import net.lsoffice.extarmour.armour.ability.FullSetAbility;
import net.lsoffice.extarmour.armour.ability.PieceAbility;
import resources.ArmorEquipEvent.ArmorEquipEvent;

public class UndeadArmour extends ArmourSet {

	ArrayList<ItemStack> hasEquipped = new ArrayList<ItemStack>();
	ArrayList<String> onCooldown = new ArrayList<String>();

	public UndeadArmour(Player player) {
		this.player = player;

		FullSetAbility undeadArmourAbility = new FullSetAbility("Necromancer", new ArrayList<String>(Arrays.asList("You are able to summon the power of necromancy.", "On crouch, summon zombies to fight for you & gain Regeneration", "(20 seconds cooldown)")));

		this.helmet = new ArmourPiece(Material.CHAINMAIL_HELMET, "§fUndead Helmet", null, undeadArmourAbility).getArmourPiece();
		this.chestplate = new ArmourPiece(Material.CHAINMAIL_CHESTPLATE, "§fUndead Chestplate", new PieceAbility().addAbility("Vampiricy", new ArrayList<String>(Arrays.asList("When attacking an enemy, steal", "2.5 hearts from them", "(12 seconds cooldown)"))), undeadArmourAbility).getArmourPiece();
		this.leggings = new ArmourPiece(Material.CHAINMAIL_LEGGINGS, "§fUndead Leggings", new PieceAbility().addAbility("Undying", new ArrayList<String>(Arrays.asList("When you have below 2.5 hearts", "immediately heal to 85% health", "(17 seconds cooldown)"))), undeadArmourAbility).getArmourPiece();
		this.boots = new ArmourPiece(Material.CHAINMAIL_BOOTS, "§fUndead Boots", null, undeadArmourAbility).getArmourPiece();
		
		this.recipeHelmet = new ShapedRecipe(this.helmet);
		this.recipeHelmet.shape("RRR", "B B", "");
		this.recipeHelmet.setIngredient('R', Material.ROTTEN_FLESH);
		this.recipeHelmet.setIngredient('B', Material.DIAMOND);
		
		this.recipeChestplate = new ShapedRecipe(this.chestplate);
		this.recipeChestplate.shape("D D", "RRR", "BBB");
		this.recipeChestplate.setIngredient('R', Material.ROTTEN_FLESH);
		this.recipeChestplate.setIngredient('D', Material.DIAMOND_BLOCK);
		this.recipeChestplate.setIngredient('B', Material.DIAMOND);
		
		this.recipeLeggings = new ShapedRecipe(this.leggings);
		this.recipeLeggings.shape("RRR", "D D", "B B");
		this.recipeLeggings.setIngredient('R', Material.ROTTEN_FLESH);
		this.recipeLeggings.setIngredient('D', Material.DIAMOND_BLOCK);
		this.recipeLeggings.setIngredient('B', Material.DIAMOND);
		
		this.recipeBoots = new ShapedRecipe(this.boots);
		this.recipeBoots.shape("", "R R", "B B");
		this.recipeBoots.setIngredient('R', Material.ROTTEN_FLESH);
		this.recipeBoots.setIngredient('B', Material.DIAMOND);
	}

	@EventHandler
	public void onEquip(ArmorEquipEvent event) {
		Player player = event.getPlayer();
		if (event.getNewArmorPiece() == null) {
			if (event.getOldArmorPiece() == null) return;
			if (event.getOldArmorPiece().equals(this.helmet)) {
				event.getPlayer().sendMessage("off undead helm");
			}
		}
		
		if (event.getNewArmorPiece().equals(this.helmet)) {
			event.getPlayer().sendMessage("have undead helm");
		}
	}

	@EventHandler
	public void onSneak(PlayerToggleSneakEvent event) {
		Player player = event.getPlayer();
		if (!player.equals(this.player)) return;
		if (!this.hasFullSet()) return;
		
		if (player.isSneaking()) {
			if (onCooldown.contains("necromancy")) return;
			
			player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5*20, 0, true, false, true));
			ArrayList<Zombie> zombieList = new ArrayList<Zombie>();
			
			int i = 1;
			while (true) {
				if (i == 6) {
					break;
				}
				
				Zombie zombie = (Zombie) player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
				zombie.setInvulnerable(true);
				zombie.setAdult();
				for (Entity e: player.getNearbyEntities(10, 10, 10)) {
					if (((LivingEntity) e).getHealth() <= 10) {
						if (!((LivingEntity) e).equals(player)) {
							zombie.setTarget((LivingEntity) e);
							break;
						}
					}
				}
				zombie.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));
				zombieList.add(zombie);
				
				i++;
			}
			
			onCooldown.add("necromancy");
			this.player.sendMessage("§aYou activated the ability of the Undead Set and used the power of Necromancy");
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.INSTANCE, () -> onCooldown.remove("necromancy"), 20*20);
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.INSTANCE, new Runnable() {

				@Override
				public void run() {
					for (Zombie z: zombieList) {
						z.setHealth(0D);
					}
				}
				
			}, 5*20);
		}
	}
	
	@EventHandler
	public void onDamageEntity1(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player)) return;
		Player player = (Player) event.getEntity();
		
		if (!this.player.equals(player)) return;
		if (player.getInventory().getLeggings() == null) return;
		if (!player.getInventory().getLeggings().getItemMeta().getLore().equals(this.leggings.getItemMeta().getLore())) return;
		
		player.sendMessage(Double.toString(player.getHealth()));
		player.sendMessage(Double.toString(3D));
		player.sendMessage(onCooldown.toString());
		if (player.getHealth() <= 5D) {
			if (onCooldown.contains("undying")) return;
			
			player.sendMessage("§aYou activated the ability of the Undead Leggings and regenerated back to 8.5 hearts");
			player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1, 0));
			player.setHealth(15D);
			onCooldown.add("undying");
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.INSTANCE, () -> onCooldown.remove("undying"), 17*20);
			return;
		}
	}
	
	@EventHandler
	public void onDamageEntity(EntityDamageByEntityEvent event) {
		if (!(event.getDamager() instanceof Player)) return;
		Player damager = (Player) event.getDamager();
		if (onCooldown.contains("vampiricy")) return;
		
		if (!this.player.equals(damager)) return;
		if (!this.player.getInventory().getChestplate().getItemMeta().getLore().equals(this.chestplate.getItemMeta().getLore())) return;
		if (!(event.getEntity() instanceof LivingEntity)) return;
		damager.setHealth(damager.getHealth()+5D);
		((LivingEntity) event.getEntity()).setHealth(((LivingEntity) event.getEntity()).getHealth()-5D);
		
		this.player.sendMessage("§aYou activated the ability of the Undead Chestplate and stole 2.5 hearts from your enemy");
		onCooldown.add("vampiricy");
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.INSTANCE, () -> onCooldown.remove("vampiricy"), 12*20);
	}
}

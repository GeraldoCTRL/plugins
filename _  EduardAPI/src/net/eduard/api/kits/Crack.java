package net.eduard.api.kits;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

import net.eduard.api.API;
import net.eduard.api.game.Potions;
import net.eduard.api.gui.Kit;

public class Crack extends Kit {
	public double chance = 0.25;

	public Crack() {
		setIcon(Material.SPIDER_EYE, "�fVicie seus inimigos");
		getPotions().add(new Potions(PotionEffectType.CONFUSION, 0, 20 * 5));
	}

	@EventHandler
	public void event(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			if (hasKit(p)) {
				if (e.getEntity() instanceof LivingEntity) {
					LivingEntity livingEntity = (LivingEntity) e.getEntity();
					if (API.getChance(chance)) {
						givePotions(livingEntity);
					}
				}
				
			}

		}
	}
}
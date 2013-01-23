package com.timvisee.tvnlib.handler;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;

import com.timvisee.tvnlib.TVNLib;
import com.timvisee.tvnlib.entity.LivingEntityTargeted;
import com.timvisee.tvnlib.event.entity.TVNLibEntityLostTargetEvent;
import com.timvisee.tvnlib.event.entity.TVNLibEntityReachedTargetEvent;

public class EntityHandler {
	
	private List<LivingEntityTargeted> leTargeted = new ArrayList<LivingEntityTargeted>();
	
	public EntityHandler() {
		TVNLib.instance.getServer().getScheduler().scheduleSyncRepeatingTask(TVNLib.instance, new Runnable() { public void run() { updateLivingEntitiesTargeted(); } }, 5, 5);
	}
	
	public void updateLivingEntitiesTargeted() {
		List<LivingEntityTargeted> remove = new ArrayList<LivingEntityTargeted>();
		for(LivingEntityTargeted entry : leTargeted) {
			if(entry.isAtTarget()) {
				TVNLibEntityReachedTargetEvent event = new TVNLibEntityReachedTargetEvent(entry.getLivingEntity());
				Bukkit.getServer().getPluginManager().callEvent(event);
				remove.add(entry);
			} else {
				//boolean canReachTarget = false;
				boolean lostTarget = entry.lostTarget();
				if(!entry.getLivingEntity().isDead())
					//canReachTarget = entry.move();
				if(lostTarget) {
					TVNLibEntityLostTargetEvent event = new TVNLibEntityLostTargetEvent(entry.getLivingEntity());
					Bukkit.getServer().getPluginManager().callEvent(event);
					remove.add(entry);
				}
			}
		}
		leTargeted.removeAll(remove);
	}
	
	/**
	 * Target a living entity to a location
	 * @param livingEntity the living entity
	 * @param x the x coord
	 * @param y the y coord
	 * @param z the z coord
	 * @return boolean
	 */
	public boolean livingEntityTargetTo(LivingEntity livingEntity, double x, double y, double z) {
		LivingEntityTargeted target = new LivingEntityTargeted(livingEntity, x, y, z);
		target.setLoseTargetDelay(50);
		boolean canReach = target.move();
		if(canReach)
			leTargeted.add(target);
		return canReach;
    }
	
	/**
	 * Target a living entity to a location
	 * @param livingEntity the living entity
	 * @param x the x coord
	 * @param y the y coord
	 * @param z the z coord
	 * @param speed the walking speed
	 * @return boolean
	 */
	public boolean livingEntityTargetTo(LivingEntity livingEntity, double x, double y, double z, float speed) {
		LivingEntityTargeted target = new LivingEntityTargeted(livingEntity, x, y, z, speed);
		target.setLoseTargetDelay(50);
		boolean canReach = target.move();
		if(canReach)
			leTargeted.add(target);
		return canReach;
    }
}

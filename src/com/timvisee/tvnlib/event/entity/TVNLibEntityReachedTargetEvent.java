package com.timvisee.tvnlib.event.entity;

import org.bukkit.entity.LivingEntity;

import com.timvisee.tvnlib.event.TVNLibEvent;

public class TVNLibEntityReachedTargetEvent extends TVNLibEvent {
	
	private LivingEntity le;
	
	public TVNLibEntityReachedTargetEvent(LivingEntity le) {
		this.le = le;
	}
	
	public LivingEntity getLivingEntity() {
		return this.le;
	}
}

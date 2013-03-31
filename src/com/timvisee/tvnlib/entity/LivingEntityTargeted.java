package com.timvisee.tvnlib.entity;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class LivingEntityTargeted {
	
	private LivingEntity le;
	double x, y, z = 0;
	float speed = .25F;
	int loseTargetAt = -1;
	
	public LivingEntityTargeted(LivingEntity le, double x, double y, double z) {
		this.le = le;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public LivingEntityTargeted(LivingEntity le, Location target, int loseTargetDelay) {
		this.le = le;
		this.x = target.getX();
		this.y = target.getY();
		this.z = target.getZ();
	}
	
	public LivingEntityTargeted(LivingEntity le, double x, double y, double z, float speed) {
		this.le = le;
		this.x = x;
		this.y = y;
		this.z = z;
		this.speed = speed;
	}

	public LivingEntity getLivingEntity() {
		return le;
	}

	public void setLivingEntity(LivingEntity le) {
		this.le = le;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
	public void setTarget(Location loc) {
		this.x = loc.getX();
		this.y = loc.getY();
		this.z = loc.getZ();
	}
	
	public Location getTarget() {
		return new Location(this.le.getWorld(),
				this.x,
				this.y,
				this.z);
	}
	
	public float getSpeed() {
		return this.speed;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public boolean move() {
		return ((org.bukkit.craftbukkit.v1_5_R2.entity.CraftLivingEntity) this.le).getHandle().getNavigation().a(this.x, this.y, this.z, this.speed);
	}
	
	public double getDistance() {
		return getTarget().distance(this.le.getLocation());
	}
	
	public void setLoseTargetDelay(int delay) {
		this.loseTargetAt = (int) ((System.currentTimeMillis()/1000) + delay);
	}
	
	public boolean lostTarget() {
		if(this.loseTargetAt == -1)
			return false;
		return (this.loseTargetAt <= (System.currentTimeMillis()/1000));
	}
	
	public boolean isAtTarget() {
		return (getDistance() <= 1);
	}
}

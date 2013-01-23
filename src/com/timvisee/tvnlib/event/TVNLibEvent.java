package com.timvisee.tvnlib.event;

import org.bukkit.Server;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TVNLibEvent extends Event {
	
	private static final HandlerList handlers = new HandlerList();
	private static Server s;

	public HandlerList getHandlers() {
		return handlers;
	}
	
    public static HandlerList getHandlerList() {
        return handlers;
    }

	public static Server getServer() {
		return s;
	}
}
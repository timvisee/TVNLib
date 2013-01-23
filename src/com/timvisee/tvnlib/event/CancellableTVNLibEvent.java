package com.timvisee.tvnlib.event;

public class CancellableTVNLibEvent extends TVNLibEvent {

	private boolean isCancelled;
	
	public boolean isCancelled() {
		return this.isCancelled;
	}
	
	public void setCancelled(boolean cancelled) {
		this.isCancelled = cancelled;
	}
}

package net.redstoneore.rson.tasks;

import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("unchecked")
public abstract class ARsonTask<T extends ARsonTask<T>> extends TimerTask {

	public abstract String getTaskName();
	
	private Timer timer = new Timer();
	
	public T start() {
		this.timer.schedule(this, 0, 2 * 1000);
		return (T) this;
	}
	
	public T stop() {
		this.timer.cancel();
		return (T) this;
	}
	
}

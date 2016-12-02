package net.redstoneore.rson.tasks;

import java.io.File;
import java.lang.reflect.Method;

import net.redstoneore.rson.Rson;

/*
 * Provides a class use by the Watch Task
 */
public class RsonWatch extends ARsonTask<RsonWatch> {
	
	public RsonWatch(Rson<?> parent, File file, Method method) {
		this.file = file;
		this.method = method;
		this.parent = parent;
	}
	
	private final File file;
	private final Method method;
	private final Rson<?> parent;
	
	private Long lastMod = null;
	
	@Override
	public void run() {		
		if (this.lastMod != null) {
			if (this.lastMod != file.lastModified()) {
				try {
					method.invoke(parent);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		this.lastMod = file.lastModified();
	}
	
	@Override
	public String getTaskName() {
		return "RsonWatch";
	}

}

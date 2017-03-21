package net.redstoneore.rson;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.redstoneore.rson.tasks.ARsonTask;
import net.redstoneore.rson.tasks.RsonWatch;
import net.redstoneore.rson.tasks.identifiers.WatchTask;

@SuppressWarnings("unchecked")
public abstract class Rson<T> {
		
	// Meta
	private transient Path path;
	private transient Charset charset;
	
	// Tasks
	private transient List<ARsonTask<?>> tasks = new ArrayList<ARsonTask<?>>();
	
	public final T setup(Path path, Charset charset) {
		this.path = path;
		this.charset = charset;
		
		// Ensure Rson tool is setup
		RsonTool.isSetup();
		
		// Look over for annotation tasks
		final List<Method> methods = new ArrayList<Method>(Arrays.asList(this.getClass().getDeclaredMethods())); 
		
		for (final Method method : methods) {			
			if (method.isAnnotationPresent(WatchTask.class)) {
				tasks.add(new RsonWatch(this, this.path.toFile(), method).start()); 
			}
		}
		
		return (T) this;
	}
	
	public final T save() throws Exception {
		byte[] data = RsonTool.toJSON(this).getBytes(this.charset);
		
		Files.write(this.path, data);
		
		return (T) this;
	}
	
	public final T load() throws Exception {
		// If the path doesn't exist then there is nothing to load
		if ( ! this.ensurePathExists()) return (T) this;
		
		// Load json into new class
		String rawJson = new String(Files.readAllBytes(path));
		Rson<?> rsonLoaded = (Rson<?>) RsonTool.fromJson(rawJson, getClass());
		
		// Update fields in our class
		for (Field field : rsonLoaded.getClass().getFields()) {
			Field thisField = this.getClass().getField(field.getName());
			if (thisField == null) continue;
			
			thisField.set(this, field.get(rsonLoaded));
		}
		
		return (T) this;
	}
	
	public final List<ARsonTask<?>> getEnabledTasks() {
		return Collections.unmodifiableList(this.tasks);
	}
	
	@Override
	public final String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (Field field : this.getClass().getFields()) {
			if (sb.length() > 1) sb.append(", ");
			
			try {
				sb.append(field.getName() + "=" + field.get(this.getClass()).toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return this.getClass().getName() + " [" + sb.toString() + "]";
	}
	
	private Boolean ensurePathExists() throws Exception {
		if ( ! Files.exists(this.path)) {
			Files.createDirectories(this.path.getParent());
			return false;
		}
		
		return true;
	}

}

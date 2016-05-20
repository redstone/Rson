package net.redstoneore.rson;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class Rson<T extends Rson<T>> {
	
	public Rson(Path path, Charset charset) {
		this.path = path;
		this.charset = charset;
		
		// Ensure Rson tool is setup
		RsonTool.get();
	}
	
	private Path path;
	private Charset charset;
	
	@SuppressWarnings("unchecked")
	public final T save() throws Exception {
		byte[] data = RsonTool.get().toJSON(this).getBytes(this.charset);
		
		Files.write(this.path, data);
		
		return (T) this;
	}
	
	@SuppressWarnings("unchecked")
	public final T load() throws Exception {
		
		// If the path doesn't exist then there is nothing to load
		if ( ! this.ensurePathExists()) return (T) this;
		
		// Load json into new class
		String rawJson = new String(Files.readAllBytes(path));
		Rson<?> rsonLoaded = (Rson<?>) RsonTool.get().fromJson(rawJson, getClass());
		
		// Update fields in our class
		for (Field field : rsonLoaded.getClass().getFields()) {
			Field thisField = this.getClass().getField(field.getName());
			if (thisField == null) continue;
			
			thisField.set(this, field.get(rsonLoaded));
		}
		
		return (T) this;
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

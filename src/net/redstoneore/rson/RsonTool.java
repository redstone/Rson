package net.redstoneore.rson;

import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.redstoneore.rson.typeadapter.TypeAdapterUUID;

public class RsonTool {

	private static RsonTool i;
	public static RsonTool get() {
		if (i == null) i = new RsonTool();
		return i;
	}
	
	public RsonTool() {
		this.setup();
	}
	
	// fields
	
	private GsonBuilder gsonBuilder;
	private Gson gson;
	
	// methods
	
	public String toJSON(Rson rson) {
		return this.gson.toJson(rson);
	}
	
	public Object fromJson(String string, Class<?> clazz) {
		return this.gson.fromJson(string, clazz);
	}
	
	private void setup() {
		this.gsonBuilder = new GsonBuilder().setPrettyPrinting();
		
		this.gsonBuilder.registerTypeAdapter(UUID.class, new TypeAdapterUUID());
		
		this.gson = this.gsonBuilder.create();
		
		this.addOptionals();
	}
	
	private void addOptionals() {
		if (this.isBukkit()) {
			
		}
		
		if (this.isSpigot()) {
			
		}
		
		if (this.isSponge()) {
			
		}
	}
	
	public Boolean isBukkit() {
		try {
			Class.forName("org.bukkit.Bukkit");
			return true;
		} catch (Exception e) { }
		
		return false;
	}
	
	public Boolean isSpigot() {
		try {
			Class.forName("net.md_5.bungee.api.ChatColor");
			return true;
		} catch (Exception e) { }
		
		return false;
	}
	
	public Boolean isSponge() {
		try {
			Class.forName("org.spongepowered.api.plugin.Plugin");
			return true;
		} catch (Exception e) { }
		
		return false;
	}
	
}

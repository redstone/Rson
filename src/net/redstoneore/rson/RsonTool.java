package net.redstoneore.rson;

import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.redstoneore.rson.adapter.BukkitAdapter;
import net.redstoneore.rson.adapter.SpigotAdapter;
import net.redstoneore.rson.adapter.SpongeAdapter;
import net.redstoneore.rson.typeadapter.TypeAdapterUUID;

/*
 * Provides a toolset for Rson
 */
public class RsonTool {
	
	// ---------------------------------------- //
	// SINGLETON
	// ---------------------------------------- //
	
	private static RsonTool i;
	
	/**
	 * Fetch the Rson tool
	 * @return RsonTool
	 */
	public static RsonTool get() {
		if (i == null) i = new RsonTool();
		return i;
	}
	
	// ---------------------------------------- //
	// CONSTRUCT
	// ---------------------------------------- //
	
	public RsonTool() {
		// Call the setup on construct
		this.setup();
	}
	
	// ---------------------------------------- //
	// FIELDS
	// ---------------------------------------- //
	
	private GsonBuilder gsonBuilder;
	private Gson gson;
	
	// ---------------------------------------- //
	// METHODS
	// ---------------------------------------- //
	
	/**
	 * Convert Rson object to JSON string
	 * @param Rson object
	 * @return JSON representation
	 */
	public String toJSON(Rson<?> rson) {
		return this.gson.toJson(rson);
	}
	
	/**
	 * Convert JSON string to class of object
	 * @param raw JSON string
	 * @param class type
	 * @return class from json
	 */
	public Object fromJson(String json, Class<?> classOfT) {
		return this.gson.fromJson(json, classOfT);
	}
	
	/**
	 * Sets up type adapters for Gson
	 */
	private void setup() {
		this.gsonBuilder = new GsonBuilder().setPrettyPrinting();
		
		this.gsonBuilder.registerTypeAdapter(UUID.class, new TypeAdapterUUID());
		
		this.gson = this.gsonBuilder.create();
		
		this.addOptionals();
	}
	
	/**
	 * Add in optional adapters
	 */
	private void addOptionals() {
		if (this.isBukkit()) {
			new BukkitAdapter().addAdaptersOn(this.gsonBuilder);
		}
		
		if (this.isSpigot()) {
			new SpigotAdapter().addAdaptersOn(this.gsonBuilder);
		}
		
		if (this.isSponge()) {
			new SpongeAdapter().addAdaptersOn(this.gsonBuilder);
		}
	}
	
	/**
	 * Detect Bukkit type
	 * @return true if Bukkit
	 */
	public Boolean isBukkit() {
		try {
			Class.forName("org.bukkit.Bukkit");
			return true;
		} catch (Exception e) { }
		
		return false;
	}
	
	/**
	 * Detect Spigot type
	 * @return true if Spigot
	 */
	public Boolean isSpigot() {
		try {
			Class.forName("org.spigotmc.CustomTimingsHandler");
			return true;
		} catch (Exception e) { }
		
		return false;
	}
	
	/**
	 * Detect Sponge type
	 * @return true if Sponge
	 */
	public Boolean isSponge() {
		try {
			Class.forName("org.spongepowered.api.plugin.Plugin");
			return true;
		} catch (Exception e) { }
		
		return false;
	}
	
}

package net.redstoneore.rson;

import java.lang.reflect.Type;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.redstoneore.rson.adapter.BukkitAdapter;
import net.redstoneore.rson.adapter.SpigotAdapter;
import net.redstoneore.rson.adapter.SpongeAdapter;
import net.redstoneore.rson.adapter.type.TypeAdapterUUID;

public final class RsonTool {
	
	// ---------------------------------------- //
	// DEPRECATED
	// ---------------------------------------- //
	
	@Deprecated 
	private static RsonTool i = new RsonTool();
	
	@Deprecated 
	public static RsonTool get() { return i; }
	
	// ---------------------------------------- //
	// FIELDS
	// ---------------------------------------- //
	
	private static GsonBuilder gsonBuilder;
	private static Gson gson;
	private static Boolean isSetup = isSetup();
	
	// ---------------------------------------- //
	// METHODS
	// ---------------------------------------- //
	
	/**
	 * Convert Rson object to JSON string
	 * @param Rson object
	 * @return JSON representation
	 */
	public static String toJSON(Rson<?> rson) {
		return gson.toJson(rson);
	}
	
	/**
	 * Convert JSON string to class of object
	 * @param raw JSON string
	 * @param class type
	 * @return class from json
	 */
	public static Object fromJson(String json, Class<?> classOfT) {
		return gson.fromJson(json, classOfT);
	}
	
	/**
	 * Add a Rson adapter
	 * @param what type it is for
	 * @param class for adapter
	 */
	public static void addAdapter(Type type, Object adapter) {
		gsonBuilder.registerTypeAdapter(type, adapter);
	}
	
	/**
	 * Sets up type adapters for Gson
	 */
	private static Boolean setup() {
		gsonBuilder = new GsonBuilder().setPrettyPrinting();
		
		// Global Java adapters 
		gsonBuilder.registerTypeAdapter(UUID.class, new TypeAdapterUUID());
		
		// Add optional adapters
		addOptionals();
		
		// Grab Gson
		gson = gsonBuilder.create();
		
		return true;
		
	}
	
	/**
	 * Add in optional adapters
	 */
	private static void addOptionals() {
		if (isBukkit()) {
			new BukkitAdapter().addAdaptersOn(gsonBuilder);
		}
		
		if (isSpigot()) {
			new SpigotAdapter().addAdaptersOn(gsonBuilder);
		}
		
		if (isSponge()) {
			new SpongeAdapter().addAdaptersOn(gsonBuilder);
		}
	}
	
	/**
	 * Detect Bukkit type
	 * @return true if Bukkit
	 */
	public static Boolean isBukkit() {
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
	public static Boolean isSpigot() {
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
	public static Boolean isSponge() {
		try {
			Class.forName("org.spongepowered.api.plugin.Plugin");
			return true;
		} catch (Exception e) { }
		
		return false;
	}
	
	public static Boolean isSetup() {
		if (isSetup == false) {
			setup();
		}
		
		return isSetup;
	}
	
}

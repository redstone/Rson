package net.redstoneore.rson.typeadapter.bukkit;

import java.lang.reflect.Type;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.redstoneore.rson.typeadapter.TypeAdapter;

public class TypeAdapterLocation extends TypeAdapter<Location> {
	
	@Override
	public JsonElement toJsonElement(Location src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject jsonLocation = new JsonObject();
		
		jsonLocation.addProperty("world", src.getWorld().getUID().toString());
		
		jsonLocation.addProperty("x", src.getX());
		jsonLocation.addProperty("y", src.getY());
		jsonLocation.addProperty("z", src.getZ());
		
		jsonLocation.addProperty("pitch", src.getPitch());
		jsonLocation.addProperty("yaw", src.getYaw());
		
		return jsonLocation;
	}

	@Override
	public Location valueOf(JsonElement value) {
		if ( ! value.isJsonObject()) return null;
		
		JsonObject jsonLocation = value.getAsJsonObject();
		
		Location bukkitLocation = null;
		
		World world = Bukkit.getWorld(UUID.fromString(jsonLocation.get("world").getAsString()));
		
		if (jsonLocation.has("pitch")) {
			Double x = jsonLocation.get("x").getAsDouble();
			Double y = jsonLocation.get("y").getAsDouble();
			Double z = jsonLocation.get("z").getAsDouble();
			
			Float pitch = jsonLocation.get("pitch").getAsFloat();
			Float yaw = jsonLocation.get("yaw").getAsFloat();
			
			bukkitLocation = new Location(world, x, y, z, yaw, pitch);
		} else {
			Double x = jsonLocation.get("x").getAsDouble();
			Double y = jsonLocation.get("y").getAsDouble();
			Double z = jsonLocation.get("z").getAsDouble();
			
			bukkitLocation = new Location(world, x, y, z);
		}

		return bukkitLocation;
	}
	
}

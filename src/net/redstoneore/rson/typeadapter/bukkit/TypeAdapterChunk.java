package net.redstoneore.rson.typeadapter.bukkit;

import java.lang.reflect.Type;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.redstoneore.rson.typeadapter.TypeAdapter;

public class TypeAdapterChunk extends TypeAdapter<Chunk> {
	
	@Override
	public JsonElement toJsonElement(Chunk src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject jsonLocation = new JsonObject();
		
		jsonLocation.addProperty("world", src.getWorld().getUID().toString());
		
		jsonLocation.addProperty("x", src.getX());
		jsonLocation.addProperty("z", src.getZ());
		
		return jsonLocation;
	}

	@Override
	public Chunk valueOf(JsonElement value) {
		if ( ! value.isJsonObject()) return null;
		
		JsonObject jsonChunk = value.getAsJsonObject();
		
		UUID worldUUID = UUID.fromString(jsonChunk.get("world").getAsString());
		
		World world = Bukkit.getWorld(worldUUID);
		
		return world.getChunkAt(jsonChunk.get("x").getAsInt(), jsonChunk.get("z").getAsInt());
	}
	
}
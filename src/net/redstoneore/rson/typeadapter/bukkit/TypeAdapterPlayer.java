package net.redstoneore.rson.typeadapter.bukkit;

import java.lang.reflect.Type;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;

import net.redstoneore.rson.typeadapter.TypeAdapter;

public class TypeAdapterPlayer extends TypeAdapter<Player>  {	
	
	@Override
	public JsonElement toJsonElement(Player src, Type typeOfSrc, JsonSerializationContext context) {
		return this.createPrimitive(src.getUniqueId().toString());
	}

	@Override
	public Player valueOf(JsonElement value) {
		return Bukkit.getPlayer(UUID.fromString(value.getAsString()));
	}

}

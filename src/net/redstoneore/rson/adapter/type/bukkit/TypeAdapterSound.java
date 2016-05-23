package net.redstoneore.rson.adapter.type.bukkit;

import java.lang.reflect.Type;

import org.bukkit.Sound;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;

import net.redstoneore.rson.adapter.type.TypeAdapter;

public class TypeAdapterSound extends TypeAdapter<Sound> {
	
	@Override
	public JsonElement toJsonElement(Sound src, Type typeOfSrc, JsonSerializationContext context) {
		return this.createPrimitive(src.name());
	}

	@Override
	public Sound valueOf(JsonElement value) {
		return Sound.valueOf(value.getAsString());
	}
	
}

package net.redstoneore.rson.adapter.type;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import net.redstoneore.rson.Rson;

public abstract class RsonAndTypeAdapter<T> extends Rson<T> implements JsonDeserializer<T>, JsonSerializer<T> {

	public final JsonPrimitive createPrimitive(String value) {
		return new JsonPrimitive(value);
	}
	
	@Override
	public final JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
		if (src == null) return JsonNull.INSTANCE;
		return this.toJsonElement(src, typeOfSrc, context);
	}
	
	@Override
	public final T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return this.valueOf(json);
	}

	public abstract JsonElement toJsonElement(T src, Type typeOfSrc, JsonSerializationContext context);
	public abstract T valueOf(JsonElement value);
	
}

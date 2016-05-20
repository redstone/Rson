package net.redstoneore.rson.typeadapter;

import java.lang.reflect.Type;
import java.util.UUID;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;

public class TypeAdapterUUID extends TypeAdapter<UUID>  {	
	
	@Override
	public JsonElement toJsonElement(UUID src, Type typeOfSrc, JsonSerializationContext context) {
		return this.createPrimitive(src.toString());
	}

	@Override
	public UUID valueOf(JsonElement value) {
		return UUID.fromString(value.getAsString());
	}

}

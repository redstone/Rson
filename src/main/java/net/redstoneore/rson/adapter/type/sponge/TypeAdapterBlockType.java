package net.redstoneore.rson.adapter.type.sponge;

import java.lang.reflect.Type;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockType;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;

import net.redstoneore.rson.adapter.type.TypeAdapter;

public class TypeAdapterBlockType extends TypeAdapter<BlockType> {
	
	@Override
	public JsonElement toJsonElement(BlockType src, Type typeOfSrc, JsonSerializationContext context) {
		return this.createPrimitive(src.getName());
	}

	@Override
	public BlockType valueOf(JsonElement value) {
		for (BlockType type : Sponge.getRegistry().getAllOf(BlockType.class)) {
			if (type.getName().equalsIgnoreCase(value.getAsString())) return type;
		}
		
		return null;
	}
	
}

package net.redstoneore.rson.typeadapter.bukkit;

import java.lang.reflect.Type;

import org.bukkit.ChatColor;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;

import net.redstoneore.rson.typeadapter.TypeAdapter;

public class TypeAdapterChatColor extends TypeAdapter<ChatColor>  {	

	@Override
	public JsonElement toJsonElement(ChatColor src, Type typeOfSrc,JsonSerializationContext context) {
		return this.createPrimitive(src.toString());
	}

	@Override
	public ChatColor valueOf(JsonElement value) {
		return ChatColor.valueOf(value.getAsString());
	}

}

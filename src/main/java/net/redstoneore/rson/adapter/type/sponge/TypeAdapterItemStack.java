package net.redstoneore.rson.adapter.type.sponge;

import java.lang.reflect.Type;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.redstoneore.rson.adapter.type.TypeAdapter;

public class TypeAdapterItemStack extends TypeAdapter<ItemStack> {

	// ----------------------------------------
	// STATIC METHODS
	// ----------------------------------------
	
	public static ItemStack from(JsonElement value) {
		JsonObject jsonItemStack = value.getAsJsonObject();
		String itemId = jsonItemStack.get("type").getAsString();
		
		for (ItemType itemType : Sponge.getRegistry().getAllOf(ItemType.class)) {
			if (itemType.getId() == itemId) {
				Integer quantity = jsonItemStack.get("quantity").getAsInt();
				ItemStack is = ItemStack.of(itemType, quantity);
				return is;
			}
		}
		
		return null;
	}
	
	public static JsonElement from(ItemStack src) {
		JsonObject jsonIS = new JsonObject();
		
		jsonIS.addProperty("quantity", src.getQuantity());
		jsonIS.addProperty("type", src.getItem().getId());
		
		return jsonIS;
	}
	
	// ----------------------------------------
	// METHODS
	// ----------------------------------------
	
	@Override
	public JsonElement toJsonElement(ItemStack src, Type typeOfSrc, JsonSerializationContext context) {
		return from(src);
	}

	@Override
	public ItemStack valueOf(JsonElement value) {
		return from(value);
	}
	
}

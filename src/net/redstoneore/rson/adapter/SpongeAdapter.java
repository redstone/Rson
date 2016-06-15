package net.redstoneore.rson.adapter;

import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.item.inventory.ItemStack;

import com.google.gson.GsonBuilder;

import net.redstoneore.rson.adapter.type.sponge.TypeAdapterBlockType;
import net.redstoneore.rson.adapter.type.sponge.TypeAdapterItemStack;

public class SpongeAdapter {

	private static SpongeAdapter i;
	public static SpongeAdapter get() {
		if (i == null) i = new SpongeAdapter();
		return i;
	}
	
	public void addAdaptersOn(GsonBuilder gsonBuilder) {
		gsonBuilder.registerTypeAdapter(BlockType.class, new TypeAdapterBlockType());
		gsonBuilder.registerTypeAdapter(ItemStack.class, new TypeAdapterItemStack());
	}
	
}

package net.redstoneore.rson.adapter;

import org.spongepowered.api.block.BlockType;

import com.google.gson.GsonBuilder;

import net.redstoneore.rson.typeadapter.sponge.TypeAdapterBlockType;

public class SpongeAdapter {

	private static SpongeAdapter i;
	public static SpongeAdapter get() {
		if (i == null) i = new SpongeAdapter();
		return i;
	}
	
	public void addAdaptersOn(GsonBuilder gsonBuilder) {
		gsonBuilder.registerTypeAdapter(BlockType.class, new TypeAdapterBlockType());
	}
	
}

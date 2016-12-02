package net.redstoneore.rson.adapter;

import com.google.gson.GsonBuilder;

public class SpigotAdapter {
	
	private static SpigotAdapter i;
	public static SpigotAdapter get() {
		if (i == null) i = new SpigotAdapter();
		return i;
	}
	
	public void addAdaptersOn(GsonBuilder gsonBuilder) {

	}
	
}

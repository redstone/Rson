package net.redstoneore.rson.adapter;

public class SpigotAdapter {
	
	private static SpigotAdapter i;
	public static SpigotAdapter get() {
		if (i == null) i = new SpigotAdapter();
		return i;
	}
	
}

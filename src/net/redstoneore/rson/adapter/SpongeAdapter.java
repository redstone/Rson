package net.redstoneore.rson.adapter;

public class SpongeAdapter {

	private static SpongeAdapter i;
	public static SpongeAdapter get() {
		if (i == null) i = new SpongeAdapter();
		return i;
	}

}

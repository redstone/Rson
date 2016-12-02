package net.redstoneore.rson.adapter;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Sound;

import com.google.gson.GsonBuilder;

import net.redstoneore.rson.adapter.type.bukkit.*;

public class BukkitAdapter {

	private static BukkitAdapter i;
	public static BukkitAdapter get() {
		if (i == null) i = new BukkitAdapter();
		return i;
	}
	
	public void addAdaptersOn(GsonBuilder gsonBuilder) {
		gsonBuilder.registerTypeAdapter(ChatColor.class, new TypeAdapterChatColor());
		gsonBuilder.registerTypeAdapter(Chunk.class, new TypeAdapterChunk());
		gsonBuilder.registerTypeAdapter(Location.class, new TypeAdapterLocation());
		gsonBuilder.registerTypeAdapter(Sound.class, new TypeAdapterSound());
	}
	
}

# Rson
This library is not a plugin, but a component you could include.

This is made to work between CraftBukkit, Spigot, and Sponge.

This is under heavy development.

## Usage
Create a class that you want to be saveable 

```java
public class Config extends Rson<Config> {

	// Singleton, you don't have to do this - organise your creation and 
	// defining of path however you want 
	private static Rson i = new Config();
	public static Rson get() { return i; }
	public Rson() {
		super(Paths.get(Plugin.get().getDataFolder().toString(), "config.json"));
	}
	
	public Boolean configurationOption = true;
	public Boolean moreConfigurationOptions = false;
	
	public Double justSomeMoreConfigurationOptions = 55.10;
	
}
```

So now you can grab this config and load/save it. If the json file doesn't exist on load it simply loads the defaults.

```java
Config.get().load().save();
```

Now, we can access any of these fields:

```java
if (Config.get().configurationOption) {
	// .. do something
} else {
	// we can modify and save whenever we want
	Config.get().configurationOption = true;
	Config.get().save();
}
```
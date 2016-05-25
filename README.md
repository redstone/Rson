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
	private static transient Config i;
	public static Config get() {
		if (i == null) {
			// Create an instance
			i = new Config();
			
			// Call setup to set the path and the charset 
			i.setup(Paths.get(Plugin.get().getDataFolder().toString(), "config.json"), Charset.defaultCharset());
		}
		return i;
	}
	public Config() { }
	
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
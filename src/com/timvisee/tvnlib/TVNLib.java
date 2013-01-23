package com.timvisee.tvnlib;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.timvisee.tvnlib.api.TVNLibApi;
import com.timvisee.tvnlib.handler.EntityHandler;

public class TVNLib extends JavaPlugin {
	
	// Constants
	public final String[] SUPPORTED_MC_VERS = new String[]{"1.4.6", "1.4.7"};
	
	// Minecraft logger
	private static final Logger log = Logger.getLogger("Minecraft");	
	
	// Instance
	public static TVNLib instance;
	
	// Handlers
	private EntityHandler eHandler;
	
	public TVNLib() {
		TVNLib.instance = this;
	}
	
	public void onEnable() {
		// Make sure the current Minecraft version is supported
		if(!isSupportedMinecraftVersion()) {
			// Show a status/error message
			log.info("[TVNLib] [ERROR] Minecraft v" + getMinecraftVersion() + " is unsupported!");
			log.info("[TVNLib] [ERROR] TVNLib could not be enabled!");
			
			// Disable TVNLib imidietly
			Bukkit.getServer().getPluginManager().disablePlugin(this);
			return;
		}
		
		// Setup handlers
		setupEntityHandler();
		
		// Setup the API
		setupApi();
		
		log.info("[TVNLib] TVNLib v" + getVersion() + " Started for Minecraft v" + getMinecraftVersion() + "!");
	}

	public void onDisable() {
		log.info("[TVNLib] TVNLib Disabled!");
	}
	
	/*public void checkUpdates() {
		XMLReader myReader = XMLReaderFactory.createXMLReader();
		myReader.setContentHandler(handler);
		myReader.parse(new InputSource(new URL("").openStream()));
	}*/
	
	public void setupApi() {
		TVNLibApi.setPlugin(this);
	}
	
	public void setupEntityHandler() {
		this.eHandler = new EntityHandler();
	}
	
	public EntityHandler getEntityHandler() {
		return this.eHandler;
	}
	
	/**
	 * Get the current plugin version
	 * @return current plugin version
	 */
	public String getVersion() {
		return getDescription().getVersion();
	}
	
	/**
	 * Get the current Minecraft version the server is running
	 * @return current running Minecraft version
	 */
	public String getMinecraftVersion() {
		final String bukkitVer = Bukkit.getBukkitVersion().trim();
		final String mcVer = bukkitVer.split("-")[0];
		return mcVer;
	}
	
	/**
	 * Get the supported Minecraft versions
	 * @return supported Minecraft versions
	 */
	public String[] getSupportedMinecraftVersions() {
		return SUPPORTED_MC_VERS;
	}
	
	/**
	 * Get the supported Minecraft versions as a list
	 * @return supported Minecraft versions
	 */
	public List<String> getSupportedMinecraftVersionsList() {
		return Arrays.asList(SUPPORTED_MC_VERS);
	}
	
	/**
	 * Is the current version running on the same Minecraft version of the server
	 * @return false if not
	 */
	public boolean isSupportedMinecraftVersion() {
		// If no allowed version was specified, allow all versions
		if(SUPPORTED_MC_VERS.length == 0)
			return true;
		
		// Get the current Minecract version and compare them to all allowed versions
		final String mcVer = getMinecraftVersion();
		for(String reqVer : SUPPORTED_MC_VERS)
			if(reqVer.equalsIgnoreCase(mcVer))
				return true;
		return false;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		return false;
	}
}
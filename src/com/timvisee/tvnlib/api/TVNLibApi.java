package com.timvisee.tvnlib.api;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.timvisee.tvnlib.TVNLib;

public class TVNLibApi {
	
	private static TVNLib plugin;
	
	/**
	* Hook into Safe Creeper
	* @return SC instance
	*/
	public static TVNLib hookTVNativeLib() {
		Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("TVNativeLib");
		if (plugin == null && !(plugin instanceof TVNLib))
			return null;
		return (TVNLib) plugin;
    }
	
	/**
	 * Get the current Safe Creeper version running
	 * @return the current Safe Creeper version
	 */
	public static String getVersion() {
		return TVNLibApi.plugin.getVersion();
	}
	
	/**
	 * Is the plugin enabled
	 * @return true if enabled
	 */
	public static boolean isEnabled() {
		// TODO: Check if the plugin is enabled itself
		
		return TVNLibApi.plugin.isEnabled();
	}
	
	/**
	 * Target a living entity to a location
	 * @param livingEntity the living entity
	 * @param x the x coord
	 * @param y the y coord
	 * @param z the z coord
	 * @return boolean
	 */
	public static boolean livingEntityTargetTo(LivingEntity livingEntity, double x, double y, double z) {
        return TVNLibApi.plugin.getEntityHandler().livingEntityTargetTo(livingEntity, x, y, z);
    }
	
	/**
	 * Target a living entity to a location
	 * @param livingEntity the living entity
	 * @param x the x coord
	 * @param y the y coord
	 * @param z the z coord
	 * @param speed the walking speed
	 * @return boolean
	 */
	public static boolean livingEntityTargetTo(LivingEntity livingEntity, double x, double y, double z, float speed) {
		return TVNLibApi.plugin.getEntityHandler().livingEntityTargetTo(livingEntity, x, y, z, speed);
    }
	
	@Deprecated
    public static void setMonsterEquipment(LivingEntity monster, Material head, Material chest, Material legs, Material boots, Material weapon){
    	EntityEquipment equip = monster.getEquipment();
    	equip.setHelmet(new ItemStack(head));
    	equip.setChestplate(new ItemStack(chest));
    	equip.setLeggings(new ItemStack(legs));
    	equip.setBoots(new ItemStack(boots));
    	equip.setItemInHand(new ItemStack(weapon));
    }
    
	@Deprecated
	public static void setMonsterEquipment(LivingEntity monster, ItemStack head, ItemStack chest, ItemStack legs, ItemStack boots, ItemStack weapon) {
		EntityEquipment equip = monster.getEquipment();
		equip.setHelmet(head);
    	equip.setChestplate(chest);
    	equip.setLeggings(legs);
    	equip.setBoots(boots);
    	equip.setItemInHand(weapon);
	}
	
	/**
	 * Get the current Minecraft version the server is running
	 * @return current running Minecraft version
	 */
	public String getMinecraftVersion() {
		return TVNLibApi.plugin.getMinecraftVersion();
	}
	
	/**
	 * Set the plugin
	 * @param plugin the plugin
	 */
	public static void setPlugin(TVNLib plugin) {
		TVNLibApi.plugin = plugin;
	}
	
	/**
	 * Get the plugin
	 * @return the plugin
	 */
	public static TVNLib getPlugin() {
		return plugin;
	}
}

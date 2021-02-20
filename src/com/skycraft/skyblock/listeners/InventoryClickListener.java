package com.skycraft.skyblock.listeners;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.sk89q.worldedit.WorldEditException;
import com.skycraft.skyblock.Main;
import com.skycraft.skyblock.guis.BiomeGUI;
import com.skycraft.skyblock.guis.CreatedGUI;
import com.skycraft.skyblock.guis.ReloadGUI;
import com.skycraft.skyblock.guis.UpgradeGUI;
import com.skycraft.skyblock.island.CreateIsland;
import com.skycraft.skyblock.island.DeleteIsland;
import com.skycraft.skyblock.upgrades.BorderUpgrade;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class InventoryClickListener implements Listener {
	
	private Main plugin;
	
	public InventoryClickListener(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) throws FileNotFoundException, WorldEditException, IOException {
		
		Player p = (Player) e.getWhoClicked();
	       
		if (e.getView().getTitle().equalsIgnoreCase("Island viewer")) {
			e.setCancelled(true);
			if ((e.getCurrentItem() == null) || (e.getCurrentItem().getType().equals(Material.AIR))) {
				return;
			}
			
			
			if(e.getSlot() == 4 && (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("��6��lTeleport to your island"))){
				
				p.teleport(new Location(Bukkit.getWorld(p.getUniqueId().toString()), 0, 81, 0));
				p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN+"Teleported you to your island!"));
				
				
			} else if (e.getSlot() == 2 && (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("��6��lUpgrade your island"))){
				
				new UpgradeGUI(p);
				
			} else if (e.getSlot() == 0 && (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("��c��lReload island"))){
				
				new ReloadGUI(p);
				
			} else if (e.getSlot() == 8 && (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("��c��lDelete island"))){
				
				World world = Bukkit.getWorld(p.getUniqueId().toString());
				
				new DeleteIsland(world.getWorldFolder(), world, plugin, p);
				p.closeInventory();
				
			}
			
		} else if (e.getView().getTitle().equalsIgnoreCase("Create your island")) {
			e.setCancelled(true);
			if ((e.getCurrentItem() == null) || (e.getCurrentItem().getType().equals(Material.AIR))) {
				return;
			}
			
			
			if(e.getSlot() == 4 && (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("��6��lCreate your island"))){
				
				new BiomeGUI(p);
				
			}
			
		} else if (e.getView().getTitle().equalsIgnoreCase("Island Upgrades")) {
			e.setCancelled(true);
			if ((e.getCurrentItem() == null) || (e.getCurrentItem().getType().equals(Material.AIR))) {
				return;
			}
			
			
			if(e.getSlot() == 2 && (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("��6��lIncrease world border"))){
				
				new BorderUpgrade(p);
				
			}
			
		} else if (e.getView().getTitle().equalsIgnoreCase("Reload Island")) {
			e.setCancelled(true);
			if ((e.getCurrentItem() == null) || (e.getCurrentItem().getType().equals(Material.AIR))) {
				return;
			}
			
			
			if (e.getSlot() == 3 && (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("��a��lConfirm"))){
				
				new BiomeGUI(p);
				
			} else if (e.getSlot() == 5 && (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("��c��lCancel"))){
				
				new CreatedGUI(p);
				
			}
			
		} else if (e.getView().getTitle().equalsIgnoreCase("Biome Chooser")) {
			e.setCancelled(true);
			if ((e.getCurrentItem() == null) || (e.getCurrentItem().getType().equals(Material.AIR))) {
				return;
			}
			
			
			if (e.getSlot() == 1 && (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("��6��lJungle Wood Biome"))){
				
				new CreateIsland(p.getLocation(), new File(plugin.getDataFolder()+File.separator+"islands"+File.separator+"Jungle.schem"), p, plugin);
				p.closeInventory();
				
			} else if (e.getSlot() == 3 && (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("��6��lSpruce Wood Biome"))){
				
				new CreateIsland(p.getLocation(), new File(plugin.getDataFolder()+File.separator+"islands"+File.separator+"Spruce.schem"), p, plugin);
				p.closeInventory();
				
			} else if (e.getSlot() == 5 && (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("��6��lOak Wood Biome"))){
				
				new CreateIsland(p.getLocation(), new File(plugin.getDataFolder()+File.separator+"islands"+File.separator+"Oak.schem"), p, plugin);
				p.closeInventory();
				
			} else if (e.getSlot() == 7 && (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("��6��lAcacia Wood Biome"))){
				
				new CreateIsland(p.getLocation(), new File(plugin.getDataFolder()+File.separator+"islands"+File.separator+"Acacia.schem"), p, plugin);
				p.closeInventory();
				
			} else if (e.getSlot() == 11 && (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("��6��lClassic Island"))){
				
				new CreateIsland(p.getLocation(), new File(plugin.getDataFolder()+File.separator+"islands"+File.separator+"Classic.schem"), p, plugin);
				p.closeInventory();
				
			} else if (e.getSlot() == 15 && (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("��6��lHardcore Island"))){
				
				new CreateIsland(p.getLocation(), new File(plugin.getDataFolder()+File.separator+"islands"+File.separator+"Hardcore.schem"), p, plugin);
				p.closeInventory();
				
			}
			
		}
		
	}

}

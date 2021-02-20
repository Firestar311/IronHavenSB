package com.skycraft.skyblock.island;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.skycraft.skyblock.Main;
import com.skycraft.skyblock.generators.EmptyChunkGenerator;

public class CreateIsland {

	@SuppressWarnings("deprecation")
	public CreateIsland(Location loc, File file, Player p, Main plugin) throws WorldEditException, FileNotFoundException, IOException
	{
		
		if (Bukkit.getWorld(p.getUniqueId().toString()) != null) {
			
			try {
				
				ClipboardFormat format = ClipboardFormats.findByFile(file);
				World world = Bukkit.getWorld(p.getUniqueId().toString());
				
				try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {

					Clipboard clipboard = reader.read();

					try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(new BukkitWorld(world), -1)) {

						Operation operation = new ClipboardHolder(clipboard)
								.createPaste(editSession)
								.to(BlockVector3.at(0, 80, 0))
								.ignoreAirBlocks(false)
								.build();
						Operations.complete(operation);

					}

				} 
				p.teleport(new Location(Bukkit.getWorld(p.getUniqueId().toString()), 0, 81, 0));
				
			} catch (FileNotFoundException exc) {
				
				Bukkit.getLogger().severe("SEVERE ERROR DETECTED: Could not find island schematic!");
				new DeleteIsland(Bukkit.getWorld(p.getUniqueId().toString()).getWorldFolder(), Bukkit.getWorld(p.getUniqueId().toString()), plugin, p);
				
			}
			
		} else {
			try {
				
				ClipboardFormat format = ClipboardFormats.findByFile(file);
				
				WorldCreator wc = new WorldCreator(p.getUniqueId().toString());
				wc.generator(new EmptyChunkGenerator());
				wc.createWorld();
				
				World world = Bukkit.getWorld(p.getUniqueId().toString());
				WorldBorder border = world.getWorldBorder();
				border.setSize(51.0);
				border.setCenter(0.0, 0.0);
				
				try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
					
				   Clipboard clipboard = reader.read();
				   
				   try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(new BukkitWorld(world), -1)) {
					    
					   Operation operation = new ClipboardHolder(clipboard)
					            .createPaste(editSession)
					            .to(BlockVector3.at(0, 80, 0))
					            .ignoreAirBlocks(false)
					            .build();
					    Operations.complete(operation);
					    
					}
				   
				}
				
				p.teleport(new Location(Bukkit.getWorld(p.getUniqueId().toString()), 0, 81, 0));
				
			} catch (FileNotFoundException exc) {
				
				Bukkit.getLogger().severe("SEVERE ERROR DETECTED: Could not find island schematic!");
				new DeleteIsland(Bukkit.getWorld(p.getUniqueId().toString()).getWorldFolder(), Bukkit.getWorld(p.getUniqueId().toString()), plugin, p);
				
			}
			
		}
		
	}

}

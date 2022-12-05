/*
* Copyright 2022 BuiltByBit
* All rights reserved.
*/
package com.builtbybit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class DemoServerPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
		this.scheduleAnnouncement();
        this.registerEvent(new PlayerListener());
	}

	private final void scheduleAnnouncement() {
		new BukkitRunnable() {
			private int minutesRemaining = 30;
			private String prefix = ChatColor.AQUA + "[BUILTBYBIT] " + ChatColor.GRAY; 
			public void run() {
				switch (--minutesRemaining) {
					case 20:
					case 10:
					case 5:
					case 2:
						Bukkit.getServer().broadcastMessage(prefix + "Test Server shutting down in " + minutesRemaining + " minutes!");
						break;
					case 0:
						Bukkit.getServer().broadcastMessage(prefix + "Test Server shutting down momentarily!");
				}
			}
		}.runTaskTimerAsynchronously(this, 20 * 60, 20 * 60);
	}

	private final void registerEvent(Listener listener) {
		this.getServer().getPluginManager().registerEvents(listener, this);
	}
}

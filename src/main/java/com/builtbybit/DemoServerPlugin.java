/*
* Copyright 2022 BuiltByBit
* All rights reserved.
*/
package com.builtbybit;

import com.builtbybit.folia.FoliaCompat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class DemoServerPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
		this.getServer().setWhitelist(false);
		this.scheduleAnnouncement();
        this.registerEvent(new PlayerListener());
		this.registerEvent(new CommandListener(this));
	}

	private void scheduleAnnouncement() {
		Runnable announcementTask = new Runnable() {
			private int minutesRemaining = 30;
			private final String prefix = ChatColor.AQUA + "[BUILTBYBIT] " + ChatColor.GRAY;
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
		};
		FoliaCompat.runAsyncRepeating(this, announcementTask, 60, 60);
	}

	private void registerEvent(Listener listener) {
		this.getServer().getPluginManager().registerEvents(listener, this);
	}
}

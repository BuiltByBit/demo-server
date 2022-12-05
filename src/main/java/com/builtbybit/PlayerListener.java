/*
* Copyright 2022 BuiltByBit
* All rights reserved.
*/
package com.builtbybit;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerListPingEvent;

public class PlayerListener implements Listener {

	@EventHandler
    public void onPing(ServerListPingEvent e) {
		e.setMotd(ChatColor.AQUA + "BuiltByBit " + ChatColor.GRAY + "Resource Test Server \n" + ChatColor.GREEN + "Powered by PebbleHost");
	}
   
    @EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.getPlayer().setOp(true);
		new JsonMessage().append(ChatColor.AQUA + "[BUILTBYBIT]").setClickAsURL("https://builtbybit.com").save()
		.append(ChatColor.GRAY + " This is a BuiltByBit resource test server.").save()
		.send(e.getPlayer());
		e.getPlayer().sendMessage(ChatColor.GRAY + "You've been granted operator priveleges.");
		new JsonMessage().append(ChatColor.GRAY + "Hosting is provided in proud partnership with ").save()
		.append(ChatColor.GREEN + "PebbleHost").setClickAsURL("https://pebblehost.com").save()
		.send(e.getPlayer());
	}
} 

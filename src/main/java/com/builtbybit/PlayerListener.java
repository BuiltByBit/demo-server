/*
* Copyright 2022 BuiltByBit
* All rights reserved.
*/
package com.builtbybit;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerListPingEvent;

public class PlayerListener implements Listener {

	@EventHandler (priority = EventPriority.MONITOR)
    public void onPing(ServerListPingEvent e) {
		e.setMotd(ChatColor.AQUA + "BuiltByBit " + ChatColor.GRAY + "Resource Test Server \n" + ChatColor.GREEN + "Powered by PebbleHost");
	}
   
    @EventHandler (priority = EventPriority.MONITOR)
	public void onJoin(PlayerJoinEvent e) {
		e.getPlayer().setOp(true);

		BaseComponent[] msg = new ComponentBuilder()
			.append("[BUILTBYBIT]").color(net.md_5.bungee.api.ChatColor.AQUA).event(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://builtbybit.com"))
			.append(" This is a BuiltByBit resource test server.").reset().color(net.md_5.bungee.api.ChatColor.GRAY).create();
		e.getPlayer().spigot().sendMessage(msg);

		e.getPlayer().sendMessage(ChatColor.GRAY + "You have been granted operator permissions.");

		msg = new ComponentBuilder()
			.append("Hosting is provided in proud partnership with ").color(net.md_5.bungee.api.ChatColor.GRAY)
			.append("PebbleHost").color(net.md_5.bungee.api.ChatColor.GREEN).event(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://pebblehost.com")).create();
		e.getPlayer().spigot().sendMessage(msg);
	}

}

package com.builtbybit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

public class CommandListener implements Listener {

    private final BlockedCommands blockedCommands = new BlockedCommands();

    public CommandListener(Plugin plugin) {
        Bukkit.getScheduler().runTaskLater(plugin, blockedCommands::loadCommandAliases, 1);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onCommand(PlayerCommandPreprocessEvent e) {
        if (blockedCommands.isBlocked(e.getMessage())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.AQUA + "[BUILTBYBIT]" + ChatColor.GRAY + " This command is blocked on test servers.");
        }
    }
}

package com.builtbybit;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;

import java.util.*;

public class BlockedCommands {

    private final Map<String, String[]> blockedCommands = new HashMap<>();
    private final List<String> aliases = new ArrayList<>();

    public BlockedCommands() {
        addCommand("deluxemenus", "list", "dump");
        addCommand("essentials", "dump");
        addCommand("plugman", "disable", "reload", "restart", "unload");
        addCommand("download");
        addCommand("sudo");
    }

    private void addCommand(String command, String... subcommands) {
        blockedCommands.put(command, subcommands.length > 0 ? subcommands : new String[]{""});
    }

    public void loadCommandAliases() {
        for (String blockedCommand : blockedCommands.keySet()) {
            for (String subcommand : blockedCommands.get(blockedCommand)) {
                aliases.add((blockedCommand + " " + subcommand).trim());
            }
            PluginCommand pluginCommand = Bukkit.getPluginCommand(blockedCommand);
            if (pluginCommand == null) continue;
            pluginCommand.getAliases().forEach(alias -> {
                for (String subcommand : blockedCommands.get(blockedCommand)) {
                    aliases.add(alias + " " + subcommand);
                }
            });
        }
    }

    public boolean isBlocked(String command) {
        if (aliases.isEmpty()) return true;
        command = format(command);
        for (String blocked : aliases) {
            if (command.startsWith(blocked)) {
                return true;
            }
        }
        return false;
    }

    private String format(String command) {
        String[] sections = command.split("\\s+"); // Remove any extra spaces
        sections[0] = sections[0].substring(sections[0].indexOf(":") + 1); // Remove plugin namespace
        sections[0] = sections[0].replaceFirst("^/+", ""); // Remove leading slash(s)
        return String.join(" ", sections).toLowerCase(Locale.US); // Rejoin the sections in lowercase
    }
}

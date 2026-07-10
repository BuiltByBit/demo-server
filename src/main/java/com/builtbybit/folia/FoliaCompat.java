package com.builtbybit.folia;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class FoliaCompat {

    private static final boolean FOLIA = checkFolia();

    private static boolean checkFolia() {
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isFolia() {
        return FOLIA;
    }

    public static void runGlobalDelayed(Plugin plugin, Runnable task, long delay) {
        if (isFolia()) {
            FoliaScheduler.runGlobalDelayed(plugin, task, delay);
        } else {
            Bukkit.getScheduler().runTaskLater(plugin, task, delay);
        }
    }

    public static void runAsyncRepeating(Plugin plugin, Runnable task, long initialSecondsDelay, long periodSecondsDelay) {
        if (isFolia()) {
            FoliaScheduler.runAsyncRepeating(plugin, task, initialSecondsDelay, periodSecondsDelay);
        } else {
            Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, task, initialSecondsDelay * 20, periodSecondsDelay * 20);
        }
    }

}

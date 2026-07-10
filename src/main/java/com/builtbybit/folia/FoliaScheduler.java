/*
 * Copyright 2022 BuiltByBit
 * All rights reserved.
 */
package com.builtbybit.folia;

import org.bukkit.plugin.Plugin;

import java.util.concurrent.TimeUnit;

class FoliaScheduler {

    static void runGlobalDelayed(Plugin plugin, Runnable task, long delay) {
        plugin.getServer().getGlobalRegionScheduler().runDelayed(plugin, scheduledTask ->  task.run(), delay);
    }

    static void runAsyncRepeating(Plugin plugin, Runnable task, long initialDelaySeconds, long periodSeconds) {
        plugin.getServer().getAsyncScheduler().runAtFixedRate(plugin, scheduledTask -> task.run(), initialDelaySeconds, periodSeconds, TimeUnit.SECONDS);
    }
}

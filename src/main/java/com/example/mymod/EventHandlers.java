package com.example.mymod;

import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EventHandlers {

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        String playerName = event.getEntity().getName().getString();
        AnalyticsTracker.sendEvent("player_join", playerName);
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof net.minecraft.world.entity.player.Player) {
            String playerName = event.getEntity().getName().getString();
            AnalyticsTracker.sendEvent("player_death", playerName);
        }
    }
}

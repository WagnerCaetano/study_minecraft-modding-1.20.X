package br.com.wagnercaetano.spaceores.events;

import br.com.wagnercaetano.spaceores.item.custom.GalactiteStaff;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.player.Player;

@Mod.EventBusSubscriber
public class FallDamageHandler {

    @SubscribeEvent
    public static void onLivingFallEvent(LivingFallEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (GalactiteStaff.hasRecentlyUsedStaff(player)) {
                event.setCanceled(true);
            }
        }
    }
}

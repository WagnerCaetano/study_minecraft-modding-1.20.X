package br.com.wagnercaetano.spaceores.item.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

import static br.com.wagnercaetano.spaceores.util.TimerThreadUtils.startTimeThenExecute;
import static net.minecraft.core.particles.ParticleTypes.PORTAL;

public class GalactiteStaff extends Item {

    private static final Set<UUID> recentStaffUsers = new HashSet<>();

    public GalactiteStaff(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        int modifier = 5;
        pPlayer.getCooldowns().addCooldown(this, 20);
        pPlayer.setDeltaMovement(pPlayer.getLookAngle().x * modifier, pPlayer.getLookAngle().y * modifier, pPlayer.getLookAngle().z * modifier);
        pLevel.playSound(pPlayer, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 3.0F, 1.0F);

        recentStaffUsers.add(pPlayer.getUUID());
        removePlayerFromStaffUsersAfterSeconds(pPlayer);

        makePortalEffectsInSeparatedThread(pLevel, pPlayer);

        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }

    private static void removePlayerFromStaffUsersAfterSeconds(Player pPlayer) {
        startTimeThenExecute(pPlayer, 5000, (player) -> {
            recentStaffUsers.remove(pPlayer.getUUID());
        });
    }

    private static void makePortalEffectsInSeparatedThread(Level pLevel, Player pPlayer) {
        for (int i = 0; i < 100; i++) {
            final int delay = i;
            startTimeThenExecute(pPlayer, delay * 10,
                    (player) -> pLevel.addParticle(PORTAL, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                            1.0D, 1.0D, 1.0D));
        }
    }

    public static boolean hasRecentlyUsedStaff(Player player) {
        return recentStaffUsers.contains(player.getUUID());
    }
}

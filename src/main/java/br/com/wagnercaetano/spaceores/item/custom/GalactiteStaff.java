package br.com.wagnercaetano.spaceores.item.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import static net.minecraft.core.particles.ParticleTypes.PORTAL;

public class GalactiteStaff extends Item {

    public GalactiteStaff(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        int modifier = 5;
        pPlayer.getCooldowns().addCooldown(this, 20);
        pPlayer.setDeltaMovement(pPlayer.getLookAngle().x * modifier, pPlayer.getLookAngle().y * modifier, pPlayer.getLookAngle().z * modifier);
        pLevel.playSound(pPlayer, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 3.0F, 1.0F);

        makePortalEffectsInSeparatedThread(pLevel, pPlayer);

        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }

    private static void makePortalEffectsInSeparatedThread(Level pLevel, Player pPlayer) {
        for (int i = 0; i < 100; i++) {
            final int delay = i;
            new Thread(() -> {
                try {
                    Thread.sleep(delay * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pLevel.addParticle(PORTAL, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), 1.0D, 1.0D, 1.0D);
            }).start();
        }
    }
}

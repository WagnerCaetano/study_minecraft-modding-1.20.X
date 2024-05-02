package br.com.wagnercaetano.spaceores.item.custom;

import br.com.wagnercaetano.spaceores.item.ModArmorMaterials;
import com.google.common.collect.ImmutableMap;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.*;

public class ModArmorItem extends ArmorItem {

    private static final Map<ArmorMaterial, ModEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, ModEffectInstance>())
                    .put(ModArmorMaterials.GALACTITE,
                        new ModEffectInstance(MobEffects.SLOW_FALLING, 100, 0,
                                false, false, true, false, 0))
                    .put(ModArmorMaterials.CONSTELLARITE,
                        new ModEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 50, 0,
                                false, false, true, true, 0.001))
                    .build();

    public ModArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        if(!level.isClientSide() && hasFullSet(player)) {
            evaluateArmorEffects(player);
        }
    }

    private boolean hasFullSet(Player player) {
        return player.getInventory().armor.stream().noneMatch(ItemStack::isEmpty);
    }

    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<ArmorMaterial, ModEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            ModEffectInstance mapEffect = entry.getValue();

            if(hasCorrectArmorOn(mapArmorMaterial, player)) {
                if (mapEffect.isRandomize()){
                    if (Math.random() > mapEffect.getRandomOdds()) {
                        addStatusEffect(player, mapEffect);
                    }
                } else {
                    addStatusEffect(player, mapEffect);
                }
            }
        }
    }

    private void addStatusEffect(Player player, MobEffectInstance effect) {
        if (!player.hasEffect(effect.getEffect())) {
            player.addEffect(new MobEffectInstance(effect));
        }
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, Player player) {
        return player.getInventory().armor.stream().anyMatch(armorStack -> {
            if(!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
            ArmorItem armorItem = (ArmorItem) armorStack.getItem();
            return armorItem.getMaterial() == material;
        });
    }
}

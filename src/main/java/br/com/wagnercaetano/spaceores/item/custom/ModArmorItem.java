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

import java.util.Map;

public class ModArmorItem extends ArmorItem {

    private static final Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>()).put(ModArmorMaterials.GALACTITE,
                        new MobEffectInstance(MobEffects.SLOW_FALLING, 200, 0,
                                false, false, true)).build();

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
        for (Map.Entry<ArmorMaterial, MobEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            MobEffectInstance mapEffect = entry.getValue();

            if(hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffect(player, mapArmorMaterial, mapEffect);
            }
        }
    }

    private void addStatusEffect(Player player, ArmorMaterial material, MobEffectInstance effect) {
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

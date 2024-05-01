package br.com.wagnercaetano.spaceores.item;

import br.com.wagnercaetano.spaceores.block.ModBlocks;
import br.com.wagnercaetano.spaceores.item.custom.ModArmorItem;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;

public enum ModItemOreInfoTable {
    CONSTELLARITE("constellarite", ModItems.CONSTELLARITE, ModBlocks.CONSTELLARITE_BLOCK, null,
            ModToolsTiers.CONSTELLARITE, 3, -2f, 1,
            -2.6f, 4, -2.5f, 0,
            -2f, 0, 0),
    GALACTITE("galactite", ModItems.GALACTITE, ModBlocks.GALACTITE_BLOCK,null,
            ModToolsTiers.GALACTITE, 3, -2.2f, 1,
            -2.8f, 5, -3f, 0,
            -3f, 0, 0);


    private String name;
    private RegistryObject<Item> item;
    private RegistryObject<Block> block;
    private Item recipeItemSmithing;
    private Tier tier;
    private int swordDamage;
    private float swordAttackSpeed;
    private int pickaxeDamage;
    private float pickaxeAttackSpeed;
    private int axeDamage;
    private float axeAttackSpeed;
    private int shovelDamage;
    private float shovelAttackSpeed;
    private int hoeDamage;
    private float hoeAttackSpeed;

    ModItemOreInfoTable(String name, RegistryObject<Item> item, RegistryObject<Block> block, @Nullable Item recipeItemSmithing, Tier tier, int swordDamage,
                        float swordAttackSpeed, int pickaxeDamage, float pickaxeAttackSpeed, int axeDamage,
                        float axeAttackSpeed, int shovelDamage, float shovelAttackSpeed, int hoeDamage, float hoeAttackSpeed) {
        this.name = name;
        this.item = item;
        this.block = block;
        this.recipeItemSmithing = recipeItemSmithing;
        this.tier = tier;
        this.swordDamage = swordDamage;
        this.swordAttackSpeed = swordAttackSpeed;
        this.pickaxeDamage = pickaxeDamage;
        this.pickaxeAttackSpeed = pickaxeAttackSpeed;
        this.axeDamage = axeDamage;
        this.axeAttackSpeed = axeAttackSpeed;
        this.shovelDamage = shovelDamage;
        this.shovelAttackSpeed = shovelAttackSpeed;
        this.hoeDamage = hoeDamage;
        this.hoeAttackSpeed = hoeAttackSpeed;
    }

    public String getName() {
        return name;
    }

    public RegistryObject<Item> getItem() {
        return item;
    }

    public RegistryObject<Block> getBlock() {
        return block;
    }

    public static DeferredRegister<Item> generateMaterialItems(DeferredRegister<Item> items, ModItemOreInfoTable modItemOreInfoTable) {
        items.register(modItemOreInfoTable.name + "_sword", () -> new SwordItem(modItemOreInfoTable.tier, modItemOreInfoTable.swordDamage, modItemOreInfoTable.swordAttackSpeed, new Item.Properties()));
        items.register(modItemOreInfoTable.name + "_pickaxe", () -> new PickaxeItem(modItemOreInfoTable.tier, modItemOreInfoTable.pickaxeDamage, modItemOreInfoTable.pickaxeAttackSpeed, new Item.Properties()));
        items.register(modItemOreInfoTable.name + "_axe", () -> new AxeItem(modItemOreInfoTable.tier, modItemOreInfoTable.axeDamage, modItemOreInfoTable.axeAttackSpeed, new Item.Properties()));
        items.register(modItemOreInfoTable.name + "_shovel", () -> new ShovelItem(modItemOreInfoTable.tier, modItemOreInfoTable.shovelDamage, modItemOreInfoTable.shovelAttackSpeed, new Item.Properties()));
        items.register(modItemOreInfoTable.name + "_hoe", () -> new HoeItem(modItemOreInfoTable.tier, modItemOreInfoTable.hoeDamage, modItemOreInfoTable.hoeAttackSpeed, new Item.Properties()));

        items.register(modItemOreInfoTable.name + "_helmet", () -> new ModArmorItem(ModArmorMaterials.valueOf(modItemOreInfoTable.name()), ArmorItem.Type.HELMET, new Item.Properties()));
        items.register(modItemOreInfoTable.name + "_chestplate", () -> new ArmorItem(ModArmorMaterials.valueOf(modItemOreInfoTable.name()), ArmorItem.Type.CHESTPLATE, new Item.Properties()));
        items.register(modItemOreInfoTable.name + "_leggings", () -> new ArmorItem(ModArmorMaterials.valueOf(modItemOreInfoTable.name()), ArmorItem.Type.LEGGINGS, new Item.Properties()));
        items.register(modItemOreInfoTable.name + "_boots", () -> new ArmorItem(ModArmorMaterials.valueOf(modItemOreInfoTable.name()), ArmorItem.Type.BOOTS, new Item.Properties()));
        return items;
    }
}

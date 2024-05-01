package br.com.wagnercaetano.spaceores.item;

import br.com.wagnercaetano.spaceores.block.ModBlocks;
import br.com.wagnercaetano.spaceores.item.custom.ModArmorItem;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;

public enum ModOreInfoTable {
    GALACTITE("galactite", ModItems.GALACTITE.get(), ModBlocks.GALACTITE_BLOCK.get(),
            ModToolsTiers.GALACTITE, 3, -2.2f, 1,
            -2.6f, 5, -3f, 0,
            -3f, 0, 0);

    private String name;
    private Item item;
    private Block block;
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

    ModOreInfoTable(String name, Item item, Block block, Tier tier, int swordDamage,
                    float swordAttackSpeed, int pickaxeDamage, float pickaxeAttackSpeed, int axeDamage,
                    float axeAttackSpeed, int shovelDamage, float shovelAttackSpeed, int hoeDamage, float hoeAttackSpeed) {
        this.name = name;
        this.item = item;
        this.block = block;
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

    public Item getItem() {
        return item;
    }

    public Block getBlock() {
        return block;
    }

    public static void generateItems(DeferredRegister<Item> items, ModOreInfoTable modOreInfoTable) {
        items.register(modOreInfoTable.name + "_sword", () -> new SwordItem(modOreInfoTable.tier, modOreInfoTable.swordDamage, modOreInfoTable.swordAttackSpeed, new Item.Properties()));
        items.register(modOreInfoTable.name + "_pickaxe", () -> new PickaxeItem(modOreInfoTable.tier, modOreInfoTable.pickaxeDamage, modOreInfoTable.pickaxeAttackSpeed, new Item.Properties()));
        items.register(modOreInfoTable.name + "_axe", () -> new AxeItem(modOreInfoTable.tier, modOreInfoTable.axeDamage, modOreInfoTable.axeAttackSpeed, new Item.Properties()));
        items.register(modOreInfoTable.name + "_shovel", () -> new ShovelItem(modOreInfoTable.tier, modOreInfoTable.shovelDamage, modOreInfoTable.shovelAttackSpeed, new Item.Properties()));
        items.register(modOreInfoTable.name + "_hoe", () -> new HoeItem(modOreInfoTable.tier, modOreInfoTable.hoeDamage, modOreInfoTable.hoeAttackSpeed, new Item.Properties()));

        items.register(modOreInfoTable.name + "_helmet", () -> new ModArmorItem(ModArmorMaterials.GALACTITE, ArmorItem.Type.HELMET, new Item.Properties()));
        items.register(modOreInfoTable.name + "_chestplate", () -> new ArmorItem(ModArmorMaterials.GALACTITE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
        items.register(modOreInfoTable.name + "_leggings", () -> new ArmorItem(ModArmorMaterials.GALACTITE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
        items.register(modOreInfoTable.name + "_boots", () -> new ArmorItem(ModArmorMaterials.GALACTITE, ArmorItem.Type.BOOTS, new Item.Properties()));

        items.register(modOreInfoTable.name, () -> new Item(new Item.Properties()));
        items.register("raw_" + modOreInfoTable.name, () -> new Item(new Item.Properties()));
    }
}

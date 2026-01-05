package net.tobyaj.tokuheroes.item;

import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tobyaj.tokuheroes.TokuHeroes;
import net.tobyaj.tokuheroes.item.custom.SpiritRodItem;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ModItems
{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TokuHeroes.MOD_ID);

    //GENERIC
    public static final DeferredItem<Item> IMPERFECT_VOID_STONE = ITEMS.register("imperfect_void_stone", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> VOID_STONE = ITEMS.register("void_stone", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> REFINED_VOID_STONE = ITEMS.register("refined_void_stone", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> HARD_VOID_STONE = ITEMS.register("hard_void_stone", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FIRE_VOID_STONE = ITEMS.register("fire_void_stone", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> WATER_VOID_STONE = ITEMS.register("water_void_stone", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> EARTH_VOID_STONE = ITEMS.register("earth_void_stone", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> AIR_VOID_STONE = ITEMS.register("air_void_stone", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> LIGHTITE = ITEMS.register("lightite", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> VOIDITE = ITEMS.register("voidite", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> HARD_CRYSTAL = ITEMS.register("hard_crystal", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FIRE_CRYSTAL = ITEMS.register("fire_crystal", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> WATER_CRYSTAL = ITEMS.register("water_crystal", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> EARTH_CRYSTAL = ITEMS.register("earth_crystal", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> AIR_CRYSTAL = ITEMS.register("air_crystal", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> VOID_PEARL = ITEMS.register("void_pearl", () -> new Item(new Item.Properties().stacksTo(16)));
    public static final DeferredItem<Item> HARD_VOID_PEARL = ITEMS.register("hard_void_pearl", () -> new Item(new Item.Properties().stacksTo(16)));
    public static final DeferredItem<Item> FIRE_VOID_PEARL = ITEMS.register("fire_void_pearl", () -> new Item(new Item.Properties().stacksTo(16)));
    public static final DeferredItem<Item> WATER_VOID_PEARL = ITEMS.register("water_void_pearl", () -> new Item(new Item.Properties().stacksTo(16)));
    public static final DeferredItem<Item> EARTH_VOID_PEARL = ITEMS.register("earth_void_pearl", () -> new Item(new Item.Properties().stacksTo(16)));
    public static final DeferredItem<Item> AIR_VOID_PEARL = ITEMS.register("air_void_pearl", () -> new Item(new Item.Properties().stacksTo(16)));

    public static final DeferredItem<Item> VOID_GLASS_DOME = ITEMS.register("void_glass_dome", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> POWER_CORE = ITEMS.register("power_core", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BASIC_PROCESSOR = ITEMS.register("basic_processor", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> VOID_PROCESSOR = ITEMS.register("void_processor", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> REFINED_VOID_PROCESSOR = ITEMS.register("refined_void_processor", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LIGHTITE_PROCESSOR = ITEMS.register("lightite_processor", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> HARDLIGHT_PROJECTION_CORE = ITEMS.register("hardlight_projection_core", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> HARDLIGHT_PROJECTOR = ITEMS.register("hardlight_projector", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> VOID_ROD = ITEMS.register("void_rod", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BASE_MELEE_WEAPON = ITEMS.register("base_melee_weapon", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BASE_PROJECTILE_WEAPON = ITEMS.register("base_projectile_weapon", () -> new Item(new Item.Properties()));

    public static final DeferredItem<ArmorItem> BASE_POWER_ARMOUR_HELMET = ITEMS.register("base_power_armour_helmet",
            () -> new ArmorItem(ModArmourMaterials.REFINED_VOID_STONE_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(12))));
    public static final DeferredItem<ArmorItem> BASE_POWER_ARMOUR_CHESTPLATE = ITEMS.register("base_power_armour_chestplate",
            () -> new ArmorItem(ModArmourMaterials.REFINED_VOID_STONE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(12))));
    public static final DeferredItem<ArmorItem> BASE_POWER_ARMOUR_LEGGINGS = ITEMS.register("base_power_armour_leggings",
            () -> new ArmorItem(ModArmourMaterials.REFINED_VOID_STONE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(12))));
    public static final DeferredItem<ArmorItem> BASE_POWER_ARMOUR_BOOTS = ITEMS.register("base_power_armour_boots",
            () -> new ArmorItem(ModArmourMaterials.REFINED_VOID_STONE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(12))));

    //STEAMPUNK
    public static final DeferredItem<Item> RUBY_GEM_WATCH = ITEMS.register("ruby_gem_watch", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> AMETHYST_GEM_WATCH = ITEMS.register("amethyst_gem_watch", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> QUARTZ_GEM_WATCH = ITEMS.register("quartz_gem_watch", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> AMBER_GEM_WATCH = ITEMS.register("amber_gem_watch", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> EMERALD_GEM_WATCH = ITEMS.register("emerald_gem_watch", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> NANO_PROJECTION_CORE = ITEMS.register("nano_projection_core", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> NANO_PROJECTOR = ITEMS.register("nano_projector", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> NANO_PROCESSOR = ITEMS.register("nano_processor", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COPPER_PIPE = ITEMS.register("copper_pipe", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ARMOUR_NANITE = ITEMS.register("armour_nanite", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> WEAPON_NANITE = ITEMS.register("weapon_nanite", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BULLET_NANITE = ITEMS.register("bullet_nanite", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FIRE_NANITE = ITEMS.register("fire_nanite", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TELEPORT_NANITE = ITEMS.register("teleport_nanite", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> POISON_NANITE = ITEMS.register("poison_nanite", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> EXPLODE_NANITE = ITEMS.register("explode_nanite", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STEAMPUNK_KNUCKLE = ITEMS.register("steampunk_knuckle", () -> new Item(new Item.Properties()));

    //SPIRIT
    public static final DeferredItem<Item> SPIRIT_ROD = ITEMS.register("spirit_rod", () -> new SpiritRodItem(new Item.Properties()
            .durability(131).attributes(ItemAttributeModifiers.builder()
                    .add(
                            Attributes.ATTACK_DAMAGE,
                            new AttributeModifier(
                                    Item.BASE_ATTACK_DAMAGE_ID,
                                    2.0D,
                                    AttributeModifier.Operation.ADD_VALUE
                            ),
                            EquipmentSlotGroup.MAINHAND
                    )
                    .add(
                            Attributes.ATTACK_SPEED,
                            new AttributeModifier(
                                    Item.BASE_ATTACK_SPEED_ID,
                                    -3.2D,
                                    AttributeModifier.Operation.ADD_VALUE
                            ),
                            EquipmentSlotGroup.MAINHAND
                    )
                    .build())));

    public static final DeferredItem<Item> RAW_SPIRIT = ITEMS.register("raw_spirit", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TIGER_SPIRIT = ITEMS.register("tiger_spirit", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> EAGLE_SPIRIT = ITEMS.register("eagle_spirit", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SHARK_SPIRIT = ITEMS.register("shark_spirit", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BEAR_SPIRIT = ITEMS.register("bear_spirit", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SNAKE_SPIRIT = ITEMS.register("snake_spirit", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}

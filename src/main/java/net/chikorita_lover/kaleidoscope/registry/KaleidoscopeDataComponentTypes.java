package net.chikorita_lover.kaleidoscope.registry;

import com.mojang.serialization.Codec;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.KaleidoscopeConfig;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.DamageResistantComponent;
import net.minecraft.component.type.EnchantableComponent;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.UnaryOperator;

public class KaleidoscopeDataComponentTypes {
    public static final ComponentType<Integer> ANIMAL_HEALABLE = register("animal_healable", builder -> builder.codec(Codec.INT));
    private static final List<Class<? extends Item>> STACKABLE_ITEMS = List.of(ArmorStandItem.class, BannerItem.class, EggItem.class, SignItem.class, SnowballItem.class, WrittenBookItem.class);
    private static final Identifier ANIMAL_HEALABLE_PHASE = Kaleidoscope.of("animal_healable_phase");

    public static void register() {
        DefaultItemComponentEvents.MODIFY.register(context -> {
            context.modify(item -> STACKABLE_ITEMS.stream().anyMatch(aClass -> aClass.isInstance(item)), (builder, item) -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
            context.modify(item -> item.getComponents().contains(DataComponentTypes.JUKEBOX_PLAYABLE) && Registries.ITEM.getId(item).getPath().matches("music_disc_\\w+"), (builder, item) -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
            context.modify(item -> item.getComponents().contains(DataComponentTypes.PROVIDES_BANNER_PATTERNS) && Registries.ITEM.getId(item).getPath().matches("\\w+_banner_pattern"), (builder, item) -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
            context.modify(Items.COOKIE, builder -> builder.add(DataComponentTypes.CONSUMABLE, ConsumableComponents.DRIED_KELP));
            context.modify(List.of(Items.BLAZE_POWDER, Items.BLAZE_ROD, Items.MAGMA_CREAM), (builder, item) -> builder.add(DataComponentTypes.DAMAGE_RESISTANT, new DamageResistantComponent(DamageTypeTags.IS_FIRE)));
            context.modify(Items.SHEARS, builder -> builder.add(DataComponentTypes.ENCHANTABLE, new EnchantableComponent(KaleidoscopeConfig.SHEARS_ENCHANTABILITY.get())));
            context.modify(List.of(Items.GOLDEN_APPLE, Items.ENCHANTED_GOLDEN_APPLE), (builder, item) -> builder.add(KaleidoscopeDataComponentTypes.ANIMAL_HEALABLE, 10));
            context.modify(Items.HAY_BLOCK, builder -> builder.add(KaleidoscopeDataComponentTypes.ANIMAL_HEALABLE, 20));
        });
        DefaultItemComponentEvents.MODIFY.addPhaseOrdering(Event.DEFAULT_PHASE, ANIMAL_HEALABLE_PHASE);
        DefaultItemComponentEvents.MODIFY.register(ANIMAL_HEALABLE_PHASE, context -> {
            context.modify(item -> item.getComponents().contains(DataComponentTypes.FOOD) && !item.getComponents().contains(KaleidoscopeDataComponentTypes.ANIMAL_HEALABLE), (builder, item) -> builder.add(KaleidoscopeDataComponentTypes.ANIMAL_HEALABLE, builder.build().get(DataComponentTypes.FOOD).nutrition()));
        });
    }

    private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Kaleidoscope.of(id), builderOperator.apply(ComponentType.builder()).build());
    }
}

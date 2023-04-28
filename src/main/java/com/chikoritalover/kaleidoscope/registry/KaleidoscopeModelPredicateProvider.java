package com.chikoritalover.kaleidoscope.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class KaleidoscopeModelPredicateProvider {
    public KaleidoscopeModelPredicateProvider() {
    }

    static {
        ModelPredicateProviderRegistry.register(Items.BUNDLE, new Identifier("dyed"), (stack, world, entity, seed) -> ((DyeableItem)stack.getItem()).getColor(stack) == -1 ? 0.0F : 1.0F);
    }
}

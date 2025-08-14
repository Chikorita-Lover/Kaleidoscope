package net.chikorita_lover.kaleidoscope.mixin.item;

import net.chikorita_lover.kaleidoscope.registry.tag.KaleidoscopeBlockTags;
import net.minecraft.block.Block;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ShearsItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryEntryLookup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ShearsItem.class)
public class ShearsItemMixin extends Item {
    public ShearsItemMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "createToolComponent", at = @At("HEAD"), cancellable = true)
    private static void overrideToolComponent(CallbackInfoReturnable<ToolComponent> cir) {
        RegistryEntryLookup<Block> registryEntryLookup = Registries.createEntryLookup(Registries.BLOCK);
        cir.setReturnValue(new ToolComponent(List.of(ToolComponent.Rule.ofAlwaysDropping(registryEntryLookup.getOrThrow(KaleidoscopeBlockTags.SHEARS_MINEABLE), 6)), 1.0F, 1, true));
    }
}

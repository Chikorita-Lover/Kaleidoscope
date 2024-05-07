package net.chikorita_lover.kaleidoscope.mixin.item;

import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeBlockTags;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ShearsItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ShearsItem.class)
public class ShearsItemMixin extends Item {
    @Unique
    private static final ToolMaterial SHEARS_MATERIAL = ToolMaterials.IRON;

    public ShearsItemMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "createToolComponent", at = @At("HEAD"), cancellable = true)
    private static void overrideToolComponent(CallbackInfoReturnable<ToolComponent> cir) {
        cir.setReturnValue(new ToolComponent(List.of(ToolComponent.Rule.ofAlwaysDropping(KaleidoscopeBlockTags.SHEARS_MINEABLE, SHEARS_MATERIAL.getMiningSpeedMultiplier())), 1.0F, 1));
    }

    @Override
    public int getEnchantability() {
        return 1;
    }
}

package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DefaultedRegistry;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Objects;

@Mixin(DefaultedRegistry.class)
public class DefaultRegistryMixin {
    @ModifyVariable(method = "get(Lnet/minecraft/util/Identifier;)Ljava/lang/Object;", at = @At(value = "HEAD"), argsOnly = true)
    private Identifier migrateGet(Identifier value) {
        if ((Object)this == Registry.BLOCK) {
            if (Objects.equals(value.getNamespace(), "chikorita_lover")) {
                return new Identifier(Kaleidoscope.MODID, value.getPath());
            }
        }

        return value;
    }
}

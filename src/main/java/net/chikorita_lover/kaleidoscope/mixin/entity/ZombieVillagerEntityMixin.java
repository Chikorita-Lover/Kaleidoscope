package net.chikorita_lover.kaleidoscope.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.chikorita_lover.kaleidoscope.KaleidoscopeConfig;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeVillagerProfessions;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.village.VillagerProfession;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

@Mixin(ZombieVillagerEntity.class)
public class ZombieVillagerEntityMixin {
    @ModifyExpressionValue(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/registry/DefaultedRegistry;getRandom(Lnet/minecraft/util/math/random/Random;)Ljava/util/Optional;"))
    private Optional<RegistryEntry.Reference<VillagerProfession>> filterEnabledProfession(Optional<RegistryEntry.Reference<VillagerProfession>> original) {
        if (original.stream().anyMatch(profession -> profession.value() == KaleidoscopeVillagerProfessions.GLASSBLOWER) && !KaleidoscopeConfig.KILNS.get()) {
            return Optional.empty();
        }
        if (original.stream().anyMatch(profession -> profession.value() == KaleidoscopeVillagerProfessions.FIREWORKER) && !KaleidoscopeConfig.FIREWORK_IMPROVEMENTS.get()) {
            return Optional.empty();
        }
        return original;
    }
}

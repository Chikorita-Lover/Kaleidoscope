package net.chikorita_lover.kaleidoscope.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.chikorita_lover.kaleidoscope.KaleidoscopeConfig;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeVillagerProfessions;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.village.VillagerProfession;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

@Mixin(ZombieVillagerEntity.class)
public class ZombieVillagerEntityMixin {
    @ModifyExpressionValue(method = "initialize", at = @At(value = "INVOKE", target = "Lnet/minecraft/registry/DefaultedRegistry;getRandom(Lnet/minecraft/util/math/random/Random;)Ljava/util/Optional;"))
    private Optional<RegistryEntry.Reference<VillagerProfession>> filterEnabledProfession(Optional<RegistryEntry.Reference<VillagerProfession>> optional) {
        if (optional.stream().anyMatch(profession -> profession.value() == Registries.VILLAGER_PROFESSION.get(KaleidoscopeVillagerProfessions.FIREWORKER)) && !KaleidoscopeConfig.KILNS.get()) {
            return Optional.empty();
        }
        if (optional.stream().anyMatch(profession -> profession.value() == Registries.VILLAGER_PROFESSION.get(KaleidoscopeVillagerProfessions.GLASSBLOWER)) && !KaleidoscopeConfig.FIREWORK_IMPROVEMENTS.get()) {
            return Optional.empty();
        }
        return optional;
    }
}

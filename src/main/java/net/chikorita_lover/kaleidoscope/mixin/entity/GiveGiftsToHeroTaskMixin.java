package net.chikorita_lover.kaleidoscope.mixin.entity;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeLootTables;
import net.minecraft.entity.ai.brain.task.GiveGiftsToHeroTask;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.village.VillagerProfession;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(GiveGiftsToHeroTask.class)
public class GiveGiftsToHeroTaskMixin {
    @Mutable
    @Shadow
    @Final
    private static Map<VillagerProfession, RegistryKey<LootTable>> GIFTS;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void putGifts(CallbackInfo ci) {
        HashMap<VillagerProfession, RegistryKey<LootTable>> gifts = new HashMap<>(GIFTS);
        gifts.put(Kaleidoscope.FIREWORKER, KaleidoscopeLootTables.HERO_OF_THE_VILLAGE_FIREWORKER_GIFT_GAMEPLAY);
        gifts.put(Kaleidoscope.GLASSBLOWER, KaleidoscopeLootTables.HERO_OF_THE_VILLAGE_GLASSBLOWER_GIFT_GAMEPLAY);
        GIFTS = gifts;
    }
}

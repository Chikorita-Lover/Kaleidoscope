package net.chikorita_lover.kaleidoscope.mixin;

import com.google.common.collect.ImmutableMap;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.recipe.KilningRecipe;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerAdvancementLoader.class)
public abstract class ServerAdvancementLoaderMixin {
    @Unique
    private static Identifier createAdvancementId(RegistryKey<Recipe<?>> smelting, RegistryKey<Recipe<?>> kilning) {
        String smeltingPath = smelting.getValue().getPath();
        int index = smeltingPath.lastIndexOf('/');
        String path = smeltingPath.substring(0, ++index).concat(kilning.getValue().getPath());
        return Kaleidoscope.of(path);
    }

    @Shadow
    protected abstract void validate(Identifier id, Advancement advancement);

    /**
     * Generates advancements for kilning recipes based off of smelting recipes' advancements.
     */
    @Inject(method = "method_20723", at = @At("TAIL"))
    private void generateKilningAdvancements(ImmutableMap.Builder<Identifier, AdvancementEntry> builder, Identifier id, Advancement advancement, CallbackInfo ci) {
        if (advancement.rewards().recipes().isEmpty()) {
            return;
        }
        RegistryKey<Recipe<?>> smelting = advancement.rewards().recipes().get(0);
        if (!KilningRecipe.SMELTING_TO_KILNING.containsKey(smelting)) {
            return;
        }
        RegistryKey<Recipe<?>> kilning = KilningRecipe.SMELTING_TO_KILNING.get(smelting);
        try {
            Advancement kilningAdvancement = new Advancement(advancement.parent(), advancement.display(), AdvancementRewards.Builder.recipe(kilning).build(), advancement.criteria(), advancement.requirements(), advancement.sendsTelemetryEvent());
            Identifier kilningId = createAdvancementId(smelting, kilning);
            this.validate(kilningId, kilningAdvancement);
            builder.put(kilningId, new AdvancementEntry(kilningId, kilningAdvancement));
        } catch (Exception e) {
            Kaleidoscope.LOGGER.error("Parsing error loading dynamic kilning advancement for recipe {}: {}", kilning, e.getMessage());
        }
        KilningRecipe.SMELTING_TO_KILNING.remove(smelting);
    }
}

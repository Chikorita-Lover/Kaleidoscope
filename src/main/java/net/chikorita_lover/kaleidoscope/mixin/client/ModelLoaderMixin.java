package net.chikorita_lover.kaleidoscope.mixin.client;

import net.chikorita_lover.kaleidoscope.JsonUnbakedModelBuilder;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.KaleidoscopeClient;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.render.model.BlockStatesLoader;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.item.AnimalArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mixin(ModelLoader.class)
public abstract class ModelLoaderMixin {
    @Unique
    private static final Map<Identifier, UnbakedModel> MODELS_TO_BAKE = new HashMap<>();
    @Shadow
    @Final
    private Map<ModelIdentifier, UnbakedModel> modelsToBake;

    @Shadow
    protected abstract void add(ModelIdentifier id, UnbakedModel model);

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V", ordinal = 1, shift = At.Shift.AFTER))
    private void appendModels(BlockColors blockColors, Profiler profiler, Map<Identifier, JsonUnbakedModel> jsonUnbakedModels, Map<Identifier, List<BlockStatesLoader.SourceTrackedData>> blockStates, CallbackInfo ci) {
        for (Item item : Registries.ITEM) {
            if (item instanceof AnimalArmorItem animalArmorItem && animalArmorItem.getType() == AnimalArmorItem.Type.EQUESTRIAN) {
                Identifier id = Registries.ITEM.getId(item);
                if (this.modelsToBake.get(ModelIdentifier.ofInventoryVariant(id)) instanceof JsonUnbakedModel model) {
                    for (String palette : KaleidoscopeClient.TRIM_PALETTES) {
                        JsonUnbakedModelBuilder builder = JsonUnbakedModelBuilder.copy(model);
                        MODELS_TO_BAKE.put(id.withSuffixedPath("_" + palette + "_trim"), builder.texture(new SpriteIdentifier(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, Kaleidoscope.of("trims/items/horse_armor_trim_" + palette))).build());
                    }
                }
            }
        }
        MODELS_TO_BAKE.forEach((id, model) -> this.add(ModelIdentifier.ofInventoryVariant(id), model));
    }
}

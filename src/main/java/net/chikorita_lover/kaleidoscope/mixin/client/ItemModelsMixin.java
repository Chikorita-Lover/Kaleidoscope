package net.chikorita_lover.kaleidoscope.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.AnimalArmorItem;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.trim.ArmorTrim;
import net.minecraft.item.trim.ArmorTrimMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemModels.class)
public abstract class ItemModelsMixin {
    @Shadow
    @Final
    private BakedModelManager modelManager;

    @Unique
    private static Identifier getTrimmedArmorModelId(ArmorItem armor, ArmorTrim trim) {
        Identifier id = Registries.ITEM.getId(armor);
        ArmorTrimMaterial material = trim.getMaterial().value();
        String asset = material.overrideArmorMaterials().getOrDefault(armor.getMaterial(), material.assetName());
        return id.withSuffixedPath("_" + asset + "_trim");
    }

    @ModifyExpressionValue(method = "getModel(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/client/render/model/BakedModel;", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/ItemModels;getModel(Lnet/minecraft/item/Item;)Lnet/minecraft/client/render/model/BakedModel;"))
    private BakedModel modifyModel(BakedModel model, ItemStack stack) {
        if (stack.getItem() instanceof AnimalArmorItem armor && armor.getType() == AnimalArmorItem.Type.EQUESTRIAN && stack.contains(DataComponentTypes.TRIM)) {
            return this.modelManager.getModel(ModelIdentifier.ofInventoryVariant(getTrimmedArmorModelId(armor, stack.get(DataComponentTypes.TRIM))));
        }
        return model;
    }
}

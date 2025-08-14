package net.chikorita_lover.kaleidoscope.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReceiver;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.render.item.model.CompositeItemModel;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.trim.ArmorTrim;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(ItemModelManager.class)
public abstract class ItemModelManagerMixin {
    @ModifyReceiver(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/model/ItemModel;update(Lnet/minecraft/client/render/item/ItemRenderState;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/item/ItemModelManager;Lnet/minecraft/item/ItemDisplayContext;Lnet/minecraft/client/world/ClientWorld;Lnet/minecraft/entity/LivingEntity;I)V"))
    private ItemModel getTrimmedModel(ItemModel model, ItemRenderState state, ItemStack stack, ItemModelManager resolver, ItemDisplayContext displayContext, ClientWorld world, LivingEntity entity, int seed) {
        // TODO
        ArmorTrim trim = stack.get(DataComponentTypes.TRIM);
        EquippableComponent component = stack.get(DataComponentTypes.EQUIPPABLE);
        if (trim == null || component == null || component.slot() != EquipmentSlot.BODY) {
            return model;
        }
        return new CompositeItemModel(List.of(model));
    }
}

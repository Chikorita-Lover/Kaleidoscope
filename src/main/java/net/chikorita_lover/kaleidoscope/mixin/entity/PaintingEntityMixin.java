package net.chikorita_lover.kaleidoscope.mixin.entity;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PaintingEntity.class)
public abstract class PaintingEntityMixin extends AbstractDecorationEntity {
    protected PaintingEntityMixin(EntityType<? extends AbstractDecorationEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    public abstract RegistryEntry<PaintingVariant> getVariant();

    @Inject(method = "onBreak", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/decoration/painting/PaintingEntity;dropItem(Lnet/minecraft/item/ItemConvertible;)Lnet/minecraft/entity/ItemEntity;", shift = At.Shift.BEFORE), cancellable = true)
    private void tryCollectPainting(Entity entity, CallbackInfo ci) {
        if (!(entity instanceof PlayerEntity player)) {
            return;
        }
        ItemStack handStack = player.getMainHandStack();
        if (!handStack.isIn(ConventionalItemTags.SHEARS_TOOLS)) {
            return;
        }
        ItemStack paintingStack = new ItemStack(Items.PAINTING);
        NbtCompound nbt = new NbtCompound();
        PaintingEntity.writeVariantToNbt(nbt, this.getVariant());
        paintingStack.set(DataComponentTypes.ENTITY_DATA, NbtComponent.of(nbt).apply(nbtCompound -> nbtCompound.putString(Entity.ID_KEY, Registries.ENTITY_TYPE.getId(EntityType.PAINTING).toString())));
        this.dropStack(paintingStack);
        if (!this.getWorld().isClient()) {
            handStack.damage(1, player, LivingEntity.getSlotForHand(Hand.MAIN_HAND));
            player.incrementStat(Stats.USED.getOrCreateStat(handStack.getItem()));
        }
        ci.cancel();
    }
}

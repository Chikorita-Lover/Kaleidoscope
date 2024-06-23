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
import net.minecraft.nbt.NbtOps;
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
        if (handStack.isIn(ConventionalItemTags.SHEAR_TOOLS)) {
            ItemStack paintingStack = new ItemStack(Items.PAINTING);
            NbtComponent nbtComponent = NbtComponent.DEFAULT.with(this.getRegistryManager().getOps(NbtOps.INSTANCE), PaintingEntity.VARIANT_MAP_CODEC, this.getVariant()).getOrThrow().apply((nbt) -> {
                nbt.putString("id", "minecraft:painting");
            });
            paintingStack.set(DataComponentTypes.ENTITY_DATA, nbtComponent);
            this.dropStack(paintingStack);
            if (!this.getWorld().isClient()) {
                handStack.damage(1, player, LivingEntity.getSlotForHand(Hand.MAIN_HAND));
                player.incrementStat(Stats.USED.getOrCreateStat(handStack.getItem()));
            }
            ci.cancel();
        }
    }
}

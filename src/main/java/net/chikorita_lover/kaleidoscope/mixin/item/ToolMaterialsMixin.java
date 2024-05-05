package net.chikorita_lover.kaleidoscope.mixin.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ToolMaterials.class)
public class ToolMaterialsMixin {
    @Final
    @Shadow
    public static ToolMaterials GOLD;

    @ModifyReturnValue(method = "getDurability", at = @At("RETURN"))
    private int getDurability(int durability) {
        return this.equals(GOLD) ? 91 : durability;
    }

    @ModifyReturnValue(method = "getAttackDamage", at = @At("RETURN"))
    private float getAttackDamage(float attackDamage) {
        return this.equals(GOLD) ? 1.0F : attackDamage;
    }

    @ModifyReturnValue(method = "getInverseTag", at = @At("RETURN"))
    private TagKey<Block> getInverseTag(TagKey<Block> inverseTag) {
        return this.equals(GOLD) ? BlockTags.INCORRECT_FOR_STONE_TOOL : inverseTag;
    }
}

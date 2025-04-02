package net.chikorita_lover.kaleidoscope.mixin.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.chikorita_lover.kaleidoscope.CollectionUtils;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(SmithingTemplateItem.class)
public class SmithingTemplateItemMixin {
    @Unique
    private static final Identifier EMPTY_ARMOR_SLOT_HORSE_TEXTURE = Kaleidoscope.of("item/empty_slot_horse_armor");

    @ModifyReturnValue(method = "getArmorTrimEmptyBaseSlotTextures", at = @At("RETURN"))
    private static List<Identifier> addArmorTrimHorseTexture(List<Identifier> textures) {
        return CollectionUtils.append(textures, EMPTY_ARMOR_SLOT_HORSE_TEXTURE);
    }

    @ModifyReturnValue(method = "getNetheriteUpgradeEmptyBaseSlotTextures", at = @At("RETURN"))
    private static List<Identifier> addNetheriteUpgradeHorseTexture(List<Identifier> textures) {
        return CollectionUtils.append(textures, 8, EMPTY_ARMOR_SLOT_HORSE_TEXTURE);
    }
}

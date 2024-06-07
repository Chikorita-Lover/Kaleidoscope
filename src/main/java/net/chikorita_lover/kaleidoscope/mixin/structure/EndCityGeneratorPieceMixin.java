package net.chikorita_lover.kaleidoscope.mixin.structure;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.chikorita_lover.kaleidoscope.structure.EndCityStructureProcessor;
import net.minecraft.structure.EndCityGenerator;
import net.minecraft.structure.StructurePlacementData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EndCityGenerator.Piece.class)
public class EndCityGeneratorPieceMixin {
    @ModifyReturnValue(method = "createPlacementData", at = @At("RETURN"))
    private static StructurePlacementData addEndCityProcessor(StructurePlacementData placementData) {
        return placementData.addProcessor(new EndCityStructureProcessor(0.2F));
    }
}

package net.chikorita_lover.kaleidoscope.mixin.entity;

import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeBoatTypes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.vehicle.BoatEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(BoatEntity.Type.class)
public class BoatEntityTypeMixin {
    @Shadow
    @Final
    @Mutable
    private static BoatEntity.Type[] field_7724;

    @Invoker("<init>")
    private static BoatEntity.Type newType(String internalName, int internalId, Block baseBlock, String name) {
        throw new AssertionError();
    }

    @Inject(method = "<clinit>", at = @At(value = "FIELD", opcode = Opcodes.PUTSTATIC, target = "Lnet/minecraft/entity/vehicle/BoatEntity$Type;field_7724:[Lnet/minecraft/entity/vehicle/BoatEntity$Type;", shift = At.Shift.AFTER))
    private static void addBoatTypes(CallbackInfo ci) {
        ArrayList<BoatEntity.Type> types = new ArrayList<>(Arrays.asList(field_7724));
        KaleidoscopeBoatTypes.CRIMSON = newType("KALEIDOSCOPE_CRIMSON", types.size(), Blocks.CRIMSON_PLANKS, "crimson");
        types.add(KaleidoscopeBoatTypes.CRIMSON);
        KaleidoscopeBoatTypes.WARPED = newType("KALEIDOSCOPE_WARPED", types.size(), Blocks.WARPED_PLANKS, "warped");
        types.add(KaleidoscopeBoatTypes.WARPED);
        field_7724 = types.toArray(BoatEntity.Type[]::new);
    }
}

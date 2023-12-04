package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@Mixin(SplashTextResourceSupplier.class)
public class SplashTextResourceSupplierMixin {
    @Unique
    private static final Identifier RESOURCE_ID = new Identifier(Kaleidoscope.MODID, "texts/splashes.txt");

    @Inject(method = "prepare(Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)Ljava/util/List;", at = @At("RETURN"), cancellable = true)
    public void prepare(ResourceManager resourceManager, Profiler profiler, CallbackInfoReturnable<List<String>> cir) {
        try {
            BufferedReader bufferedReader = MinecraftClient.getInstance().getResourceManager().openAsReader(RESOURCE_ID);

            List<String> stringList = cir.getReturnValue();

            try {
                stringList.addAll(bufferedReader.lines().map(String::trim).toList());
            } catch (Throwable throwable) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                }

                throw throwable;
            }

            bufferedReader.close();

            cir.setReturnValue(stringList);
        } catch (IOException ioException) {
            cir.setReturnValue(cir.getReturnValue());
        }
    }
}

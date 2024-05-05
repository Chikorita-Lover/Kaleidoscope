package net.chikorita_lover.kaleidoscope.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@Mixin(SplashTextResourceSupplier.class)
public class SplashTextResourceSupplierMixin {
    @Unique
    private static final Identifier RESOURCE_ID = new Identifier(Kaleidoscope.MODID, "texts/splashes.txt");

    @ModifyReturnValue(method = "prepare(Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)Ljava/util/List;", at = @At("RETURN"))
    private List<String> prepare(List<String> splashTexts) {
        try {
            BufferedReader bufferedReader = MinecraftClient.getInstance().getResourceManager().openAsReader(RESOURCE_ID);
            try {
                splashTexts.addAll(bufferedReader.lines().map(String::trim).toList());
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
        } catch (IOException ignored) {
        }
        return splashTexts;
    }
}

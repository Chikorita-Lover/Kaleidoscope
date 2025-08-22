package net.chikorita_lover.kaleidoscope.mixin.client;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.KaleidoscopeConfig;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Unique
    private static final Identifier HOTBAR_ARMOR_LEFT_TEXTURE = Kaleidoscope.of("hud/hotbar_armor_left");
    @Unique
    private static final Identifier HOTBAR_ARMOR_RIGHT_TEXTURE = Kaleidoscope.of("hud/hotbar_armor_right");
    @Unique
    private static final List<EquipmentSlot> ARMOR_SLOTS = List.of(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
    @Unique
    private static final int HOTBAR_HALF_WIDTH = 91 + 24;

    @Shadow
    protected abstract @Nullable PlayerEntity getCameraPlayer();

    @Shadow
    protected abstract void renderHotbarItem(DrawContext context, int x, int y, RenderTickCounter tickCounter, PlayerEntity player, ItemStack stack, int seed);

    @Inject(method = "renderHotbar", at = @At("TAIL"))
    private void renderHotbarArmor(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (!KaleidoscopeConfig.SHOW_HOTBAR_ARMOR.get()) {
            return;
        }
        int center = context.getScaledWindowWidth() / 2;
        PlayerEntity player = this.getCameraPlayer();
        boolean background = false;
        boolean right = player.getMainArm() == Arm.RIGHT;
        int seed = 11;
        for (int i = 0; i < ARMOR_SLOTS.size(); ++i) {
            EquipmentSlot slot = ARMOR_SLOTS.get(i);
            ItemStack stack = player.getEquippedStack(slot);
            if (stack.isEmpty()) continue;
            if (!background) {
                background = true;
                this.drawHotbarArmorBackground(context, right);
            }
            int x = center + i * 20;
            int y = context.getScaledWindowHeight() - 19;
            if (right) {
                this.renderHotbarItem(context, x + HOTBAR_HALF_WIDTH + 10, y, tickCounter, player, stack, seed++);
            } else {
                this.renderHotbarItem(context, x - HOTBAR_HALF_WIDTH - 86, y, tickCounter, player, stack, seed++);
            }
        }
    }

    @Unique
    private void drawHotbarArmorBackground(DrawContext context, boolean right) {
        int x = context.getScaledWindowWidth() / 2;
        if (right) {
            context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, HOTBAR_ARMOR_RIGHT_TEXTURE, x + HOTBAR_HALF_WIDTH, context.getScaledWindowHeight() - 23, 89, 24);
        } else {
            context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, HOTBAR_ARMOR_LEFT_TEXTURE, x - HOTBAR_HALF_WIDTH - 89, context.getScaledWindowHeight() - 23, 89, 24);
        }
    }
}

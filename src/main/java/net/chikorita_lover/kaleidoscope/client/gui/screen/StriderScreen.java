package net.chikorita_lover.kaleidoscope.client.gui.screen;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.entity.Chestable;
import net.chikorita_lover.kaleidoscope.screen.StriderScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.Identifier;

public class StriderScreen extends HandledScreen<StriderScreenHandler> {
    private static final Identifier CHEST_SLOTS_TEXTURE = new Identifier(Kaleidoscope.MODID, "container/strider/chest_slots");
    private static final Identifier TEXTURE = new Identifier(Kaleidoscope.MODID, "textures/gui/container/strider.png");
    private float mouseX;
    private float mouseY;

    public StriderScreen(StriderScreenHandler handler, PlayerInventory inventory, StriderEntity entity) {
        super(handler, inventory, entity.getDisplayName());
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        context.drawTexture(TEXTURE, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
        if (((Chestable) this.handler.entity).kaleidoscope$hasChest()) {
            context.drawGuiTexture(CHEST_SLOTS_TEXTURE, 90, 54, 0, 0, i + 79, j + 17, 5 * 18, 54);
        }
        InventoryScreen.drawEntity(context, i + 8, j + 18, i + 78, j + 70, 17, 0.25F, this.mouseX, this.mouseY, this.handler.entity);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }
}

package net.chikorita_lover.kaleidoscope.block.entity;

import net.chikorita_lover.kaleidoscope.recipe.KaleidoscopeRecipeTypes;
import net.chikorita_lover.kaleidoscope.screen.KilnScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class KilnBlockEntity extends AbstractFurnaceBlockEntity {
    public KilnBlockEntity(BlockPos pos, BlockState state) {
        super(KaleidoscopeBlockEntityTypes.KILN, pos, state, KaleidoscopeRecipeTypes.KILNING);
    }

    protected Text getContainerName() {
        return Text.translatable("container.kiln");
    }

    protected int getFuelTime(ItemStack fuel) {
        return super.getFuelTime(fuel) / 2;
    }

    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new KilnScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }
}

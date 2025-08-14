package net.chikorita_lover.kaleidoscope.block.entity;

import net.chikorita_lover.kaleidoscope.recipe.KaleidoscopeRecipeTypes;
import net.chikorita_lover.kaleidoscope.screen.KilnScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.FuelRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class KilnBlockEntity extends AbstractFurnaceBlockEntity {
    public KilnBlockEntity(BlockPos pos, BlockState state) {
        super(KaleidoscopeBlockEntityTypes.KILN, pos, state, KaleidoscopeRecipeTypes.KILNING);
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("container.kiln");
    }

    @Override
    protected int getFuelTime(FuelRegistry fuelRegistry, ItemStack stack) {
        return super.getFuelTime(fuelRegistry, stack) / 2;
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new KilnScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }
}

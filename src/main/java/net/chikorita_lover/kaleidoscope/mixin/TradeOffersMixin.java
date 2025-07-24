package net.chikorita_lover.kaleidoscope.mixin;

import net.minecraft.item.Items;
import net.minecraft.village.TradeOffers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(TradeOffers.class)
public class TradeOffersMixin {
    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/village/TradeOffers$SellItemFactory;<init>(Lnet/minecraft/item/Item;III)V"))
    private static void modifySellItemTradeOffer(Args args) {
        if (args.get(0) == Items.NAME_TAG) {
            args.set(1, 3);
        }
    }
}

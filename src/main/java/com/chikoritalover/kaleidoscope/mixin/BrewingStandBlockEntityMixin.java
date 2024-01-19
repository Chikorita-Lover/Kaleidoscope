package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.block.entity.KaleidoscopeBrewingStandBlockEntity;
import com.chikoritalover.kaleidoscope.entity.KaleidoscopePlayerEntity;
import com.chikoritalover.kaleidoscope.recipe.BrewingRecipe;
import com.chikoritalover.kaleidoscope.recipe.BrewingUtil;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.potion.PotionUtil;
import net.minecraft.screen.BrewingStandScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(BrewingStandBlockEntity.class)
public class BrewingStandBlockEntityMixin implements KaleidoscopeBrewingStandBlockEntity {
    @Unique
    private final Object2IntOpenHashMap<Identifier> recipesUsed = new Object2IntOpenHashMap<>();
    @Shadow
    private DefaultedList<ItemStack> inventory;
    @Unique
    private int storedXp = 0;

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/BrewingStandBlockEntity;craft(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/collection/DefaultedList;)V", shift = At.Shift.BEFORE))
    private static void tick(World world, BlockPos pos, BlockState state, BrewingStandBlockEntity blockEntity, CallbackInfo ci) {
        ((KaleidoscopeBrewingStandBlockEntity) blockEntity).kaleidoscope$storeExperience(1);
        for (int i = 0; i < 3; ++i) {
            ItemStack itemStack = blockEntity.getStack(i);
            boolean bl = false;
            for (BrewingRecipe recipe2 : BrewingRecipe.BREWING_RECIPES) {
                if (!recipe2.getInput().test(itemStack) || PotionUtil.getPotion(itemStack) != recipe2.getInputPotion() || !recipe2.getReagent().test(blockEntity.getStack(3)))
                    continue;
                ((KaleidoscopeBrewingStandBlockEntity) blockEntity).kaleidoscope$addTo(recipe2.getId(), 1);
                for (PlayerEntity playerEntity : world.getPlayers()) {
                    if (playerEntity instanceof ServerPlayerEntity serverPlayerEntity && playerEntity.currentScreenHandler instanceof BrewingStandScreenHandler brewingStandScreenHandler && brewingStandScreenHandler.inventory == blockEntity) {
                        ((KaleidoscopePlayerEntity) serverPlayerEntity).kaleidoscope$unlockRecipes(BrewingUtil.getUnlockableRecipes(recipe2.getOutput()));
                    }
                }
                bl = true;
                break;
            }
            // Store brewing experience if a recipe could not be found (e.g. if a potion with custom effects is converted to a splash potion)
            if (!bl)
                ((KaleidoscopeBrewingStandBlockEntity) blockEntity).kaleidoscope$storeExperience(world.getRandom().nextBetween(0, 1));
        }
    }

    @Inject(method = "readNbt", at = @At("TAIL"))
    public void readNbt(NbtCompound nbt, CallbackInfo ci) {
        this.storedXp = nbt.getInt("StoredXp");
        NbtCompound nbtCompound = nbt.getCompound("RecipesUsed");
        for (String string : nbtCompound.getKeys()) {
            this.recipesUsed.put(new Identifier(string), nbtCompound.getInt(string));
        }
    }

    @Inject(method = "writeNbt", at = @At("TAIL"))
    public void writeNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putInt("StoredXp", this.storedXp);
        NbtCompound nbtCompound = new NbtCompound();
        this.recipesUsed.forEach((identifier, count) -> nbtCompound.putInt(identifier.toString(), count));
        nbt.put("RecipesUsed", nbtCompound);
    }

    @Override
    public void kaleidoscope$dropExperienceForRecipesUsed(ServerPlayerEntity player) {
        List<BrewingRecipe> list = this.kaleidoscope$getRecipesUsedAndDropExperience(player.getServerWorld(), player.getPos());
        ((KaleidoscopePlayerEntity) player).kaleidoscope$unlockRecipes(list);
        for (Object2IntMap.Entry<Identifier> entry : this.recipesUsed.object2IntEntrySet()) {
            BrewingUtil.getRecipeFromId(entry.getKey()).ifPresent(recipe -> {
                Criteria.RECIPE_CRAFTED.trigger(player, recipe.getId(), this.inventory);
                Criteria.BREWED_POTION.trigger(player, PotionUtil.getPotion(recipe.getOutput()));
                recipe.getOutput().onCraft(player.getWorld(), player, recipe.getOutput().getCount() * entry.getIntValue());
            });
        }
        this.recipesUsed.clear();
    }

    @Override
    public List<BrewingRecipe> kaleidoscope$getRecipesUsedAndDropExperience(ServerWorld world, Vec3d pos) {
        ArrayList<BrewingRecipe> list = Lists.newArrayList();
        for (Object2IntMap.Entry<Identifier> entry : this.recipesUsed.object2IntEntrySet()) {
            BrewingUtil.getRecipeFromId(entry.getKey()).ifPresent(recipe -> {
                list.add(recipe);
                ExperienceOrbEntity.spawn(world, pos, this.storedXp + world.getRandom().nextInt(entry.getIntValue()));
                this.kaleidoscope$clearExperience();
            });
        }
        return list;
    }

    @Override
    public void kaleidoscope$addTo(Identifier id, int incr) {
        this.recipesUsed.addTo(id, incr);
    }

    @Override
    public Object2IntOpenHashMap<Identifier> kaleidoscope$getRecipesUsed() {
        return this.recipesUsed;
    }

    @Override
    public void kaleidoscope$storeExperience(int i) {
        this.storedXp += i;
    }

    @Override
    public void kaleidoscope$clearExperience() {
        this.storedXp = 0;
    }
}

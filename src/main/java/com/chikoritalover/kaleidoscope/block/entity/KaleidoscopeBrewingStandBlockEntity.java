package com.chikoritalover.kaleidoscope.block.entity;

import com.chikoritalover.kaleidoscope.recipe.BrewingRecipe;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public interface KaleidoscopeBrewingStandBlockEntity {
    void kaleidoscope$addTo(Identifier id, int incr);

    Object2IntOpenHashMap<Identifier> kaleidoscope$getRecipesUsed();

    void kaleidoscope$dropExperienceForRecipesUsed(ServerPlayerEntity player);

    List<BrewingRecipe> kaleidoscope$getRecipesUsedAndDropExperience(ServerWorld world, Vec3d pos);

    void kaleidoscope$storeExperience(int i);

    void kaleidoscope$clearExperience();
}

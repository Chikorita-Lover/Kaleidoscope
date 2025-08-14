package net.chikorita_lover.kaleidoscope.mixin.structure;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(StructurePool.class)
public interface StructurePoolAccessor {
    @Accessor(value = "elements")
    ObjectArrayList<StructurePoolElement> getElements();

    @Accessor(value = "elementWeights")
    @Mutable
    void setElementWeights(List<Pair<StructurePoolElement, Integer>> list);
}

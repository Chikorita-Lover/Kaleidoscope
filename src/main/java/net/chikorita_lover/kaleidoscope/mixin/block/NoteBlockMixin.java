package net.chikorita_lover.kaleidoscope.mixin.block;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeSoundEvents;
import net.chikorita_lover.kaleidoscope.registry.tag.KaleidoscopeBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.NoteBlock;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.HashMap;
import java.util.Map;

@Mixin(NoteBlock.class)
public class NoteBlockMixin {
    @Unique
    private static final Map<Block, RegistryEntry<SoundEvent>> BLOCK_TO_NOTE_BLOCK_SOUND = new HashMap<>();

    @Unique
    private static void register() {
        registerTag(KaleidoscopeSoundEvents.BLOCK_NOTE_BLOCK_SAXOPHONE, KaleidoscopeBlockTags.COPPER);
    }

    @Unique
    private static void registerTag(final RegistryEntry<SoundEvent> sound, TagKey<Block> tag) {
        Registries.BLOCK.getEntryList(tag).ifPresent(entries -> entries.forEach(entry -> BLOCK_TO_NOTE_BLOCK_SOUND.put(entry.value(), sound)));
    }

    @Unique
    private static void registerBlocks(RegistryEntry<SoundEvent> sound, Block... blocks) {
        for (Block block : blocks) {
            BLOCK_TO_NOTE_BLOCK_SOUND.put(block, sound);
        }
    }

    @ModifyExpressionValue(method = "onSyncedBlockEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/enums/NoteBlockInstrument;getSound()Lnet/minecraft/registry/entry/RegistryEntry;"))
    private RegistryEntry<SoundEvent> modifySound(RegistryEntry<SoundEvent> sound, BlockState state, World world, BlockPos pos) {
        if (BLOCK_TO_NOTE_BLOCK_SOUND.isEmpty()) {
            register();
        }
        Block block = world.getBlockState(pos.down()).getBlock();
        return BLOCK_TO_NOTE_BLOCK_SOUND.getOrDefault(block, sound);
    }
}

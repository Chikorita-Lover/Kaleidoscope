package net.chikorita_lover.kaleidoscope.entity;

import com.chocohead.mm.api.ClassTinkerers;
import net.chikorita_lover.kaleidoscope.item.KaleidoscopeItems;
import net.chikorita_lover.kaleidoscope.network.StopJukeboxMinecartPlayingS2CPacket;
import net.chikorita_lover.kaleidoscope.network.UpdateJukeboxMinecartS2CPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.JukeboxBlock;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Optional;

public class JukeboxMinecartEntity extends AbstractMinecartEntity implements Inventory {
    public static final AbstractMinecartEntity.Type JUKEBOX_TYPE = ClassTinkerers.getEnum(AbstractMinecartEntity.Type.class, "JUKEBOX");
    private static final TrackedData<Boolean> PLAYING = DataTracker.registerData(JukeboxMinecartEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<ItemStack> ITEM_STACK = DataTracker.registerData(JukeboxMinecartEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);
    private int ticksThisSecond;
    private long recordStartTick;
    private long tickCount;

    public JukeboxMinecartEntity(EntityType<? extends AbstractMinecartEntity> entityType, World world) {
        super(entityType, world);
    }

    public JukeboxMinecartEntity(World world, double x, double y, double z) {
        super(KaleidoscopeEntityTypes.JUKEBOX_MINECART, world, x, y, z);
    }

    private static void spawnNoteParticle(World world, Vec3d pos) {
        if (world.isClient()) {
            Vec3d vec3d = pos.add(0.0, 1.2F, 0.0);
            float f = world.getRandom().nextInt(4) / 24.0F;
            world.addImportantParticle(ParticleTypes.NOTE, true, vec3d.getX(), vec3d.getY(), vec3d.getZ(), f, 0.0, 0.0);
        }
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public int getMaxCountPerStack() {
        return 1;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(ITEM_STACK, ItemStack.EMPTY);
        builder.add(PLAYING, false);
    }

    @Override
    public void tick() {
        super.tick();
        ++this.ticksThisSecond;
        if (this.isPlayingRecord() && this.getStack(0).contains(DataComponentTypes.JUKEBOX_PLAYABLE)) {
            if (this.isSongFinished(this.getStack(0))) {
                this.stopPlaying();
            } else if (this.ticksThisSecond >= 20) {
                this.ticksThisSecond = 0;
                this.getWorld().emitGameEvent(GameEvent.JUKEBOX_PLAY, this.getPos(), GameEvent.Emitter.of(this));
                spawnNoteParticle(this.getWorld(), this.getPos());
            }
        }
        ++this.tickCount;
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (this.getWorld().isClient()) {
            return ActionResult.SUCCESS;
        }
        if (!this.isEmpty()) {
            this.dropRecord();
        } else if (stack.contains(DataComponentTypes.JUKEBOX_PLAYABLE)) {
            this.setStack(0, stack.splitUnlessCreative(1, player));
        }
        return ActionResult.CONSUME;
    }

    private boolean isSongFinished(ItemStack stack) {
        Optional<RegistryEntry<JukeboxSong>> optional = JukeboxSong.getSongEntryFromStack(this.getWorld().getRegistryManager(), stack);
        return optional.map(songEntry -> this.tickCount >= this.recordStartTick + songEntry.value().getLengthInTicks() + 20L).orElse(true);
    }

    @Override
    public AbstractMinecartEntity.Type getMinecartType() {
        return JUKEBOX_TYPE;
    }

    public Item asItem() { // Overrides a super method
        return KaleidoscopeItems.JUKEBOX_MINECART;
    }

    @Override
    public ItemStack getPickBlockStack() {
        return new ItemStack(this.asItem());
    }

    @Override
    public BlockState getDefaultContainedBlock() {
        return Blocks.JUKEBOX.getDefaultState().with(JukeboxBlock.HAS_RECORD, !this.isEmpty());
    }

    @Override
    public boolean isEmpty() {
        return this.getStack(0).isEmpty();
    }

    public boolean isPlayingRecord() {
        return this.dataTracker.get(PLAYING);
    }

    @Override
    public ItemStack getStack(int slot) {
        return slot <= this.size() ? this.dataTracker.get(ITEM_STACK) : ItemStack.EMPTY;
    }

    public void setStack(int slot, ItemStack stack) {
        if (slot >= this.size()) {
            return;
        }
        if (stack.contains(DataComponentTypes.JUKEBOX_PLAYABLE)) {
            this.dataTracker.set(ITEM_STACK, stack);
            this.startPlaying();
        }
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        return this.removeStack(slot);
    }

    @Override
    public ItemStack removeStack(int slot) {
        return slot < this.size() ? this.removeStack() : ItemStack.EMPTY;
    }

    public ItemStack removeStack() {
        ItemStack itemStack = this.dataTracker.get(ITEM_STACK);
        this.dataTracker.set(ITEM_STACK, ItemStack.EMPTY);
        if (!itemStack.isEmpty()) {
            this.stopPlaying();
        }
        return itemStack;
    }

    public void dropRecord() {
        World world = this.getWorld();
        ItemStack itemStack = this.getStack(0);
        if (itemStack.isEmpty()) {
            return;
        }
        this.removeStack();
        if (world == null || world.isClient()) {
            return;
        }
        Vec3d vec3d = this.getPos().add(0.0, 0.9, 0.0);
        ItemStack itemStack2 = itemStack.copy();
        ItemEntity itemEntity = new ItemEntity(world, vec3d.getX(), vec3d.getY(), vec3d.getZ(), itemStack2);
        itemEntity.setToDefaultPickupDelay();
        world.spawnEntity(itemEntity);
    }

    @Override
    public void clear() {
        this.removeStack();
    }

    public void startPlaying() {
        this.recordStartTick = this.tickCount;
        this.dataTracker.set(PLAYING, true);
        UpdateJukeboxMinecartS2CPacket packet = new UpdateJukeboxMinecartS2CPacket(this.getId(), this.getStack(0));
        if (this.getWorld() instanceof ServerWorld serverWorld) {
            for (ServerPlayerEntity serverPlayer : serverWorld.getPlayers()) {
                ServerPlayNetworking.send(serverPlayer, packet);
            }
        }
    }

    public void stopPlaying() {
        this.dataTracker.set(PLAYING, false);
        this.getWorld().emitGameEvent(GameEvent.JUKEBOX_STOP_PLAY, this.getPos(), GameEvent.Emitter.of(this));
    }

    @Override
    public void remove(RemovalReason reason) {
        super.remove(reason);
        if (this.getWorld().getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
            this.dropStack(this.getStack(0));
            this.removeStack();
        }
        StopJukeboxMinecartPlayingS2CPacket packet = new StopJukeboxMinecartPlayingS2CPacket(this.getId());
        if (this.getWorld() instanceof ServerWorld serverWorld) {
            for (ServerPlayerEntity serverPlayer : serverWorld.getPlayers()) {
                ServerPlayNetworking.send(serverPlayer, packet);
            }
        }
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("RecordItem", NbtElement.COMPOUND_TYPE)) {
            this.setStack(0, ItemStack.fromNbt(this.getRegistryManager(), nbt.getCompound("RecordItem")).orElse(ItemStack.EMPTY));
        }
        this.recordStartTick = nbt.getLong("RecordStartTick");
        this.tickCount = nbt.getLong("TickCount");
        this.dataTracker.set(PLAYING, nbt.getBoolean("IsPlaying"));
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        if (!this.getStack(0).isEmpty()) {
            nbt.put("RecordItem", this.getStack(0).encode(this.getRegistryManager()));
        }
        nbt.putBoolean("IsPlaying", this.isPlayingRecord());
        nbt.putLong("RecordStartTick", this.recordStartTick);
        nbt.putLong("TickCount", this.tickCount);
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    public boolean isValid(int slot, ItemStack stack) {
        return slot < this.size() && stack.contains(DataComponentTypes.JUKEBOX_PLAYABLE);
    }

    @Override
    public boolean canTransferTo(Inventory hopperInventory, int slot, ItemStack stack) {
        return !this.isPlayingRecord();
    }

    @Override
    public void markDirty() {

    }
}

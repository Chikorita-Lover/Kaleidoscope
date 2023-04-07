package com.chikoritalover.kaleidoscope.mixin;

import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.village.VillagerProfession;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

@Mixin(ZombieVillagerEntity.class)
public class ZombieVillagerEntityMixin {
    public List<VillagerProfession> daylightImmuneProfessions = List.of(VillagerProfession.FARMER, VillagerProfession.FISHERMAN, VillagerProfession.FLETCHER, VillagerProfession.LIBRARIAN, VillagerProfession.SHEPHERD);

    protected boolean burnsInDaylight() {
        ZombieVillagerEntity zombieVillager = ZombieVillagerEntity.class.cast(this);
        return !daylightImmuneProfessions.contains(zombieVillager.getVillagerData().getProfession()) || zombieVillager.isBaby();
    }
}

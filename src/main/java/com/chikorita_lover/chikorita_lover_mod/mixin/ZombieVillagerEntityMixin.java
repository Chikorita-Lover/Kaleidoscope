package com.chikorita_lover.chikorita_lover_mod.mixin;

import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.village.VillagerProfession;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ZombieVillagerEntity.class)
public class ZombieVillagerEntityMixin {
    protected boolean burnsInDaylight() {
        ZombieVillagerEntity zombieVillager = ZombieVillagerEntity.class.cast(this);
        VillagerProfession profession = zombieVillager.getVillagerData().getProfession();
        return profession != VillagerProfession.FARMER && profession != VillagerProfession.FISHERMAN;
    }
}

package net.zimavi.crucifixmod.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zimavi.crucifixmod.CrucifixMod;
import net.zimavi.crucifixmod.entity.custom.Chains;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CrucifixMod.MOD_ID);

    //Chains that hold Entity while using crucifix
    public static final RegistryObject<EntityType<Chains>> CHAINS_ENTITY = ENTITIES.register("chains",
            () -> EntityType.Builder.of(Chains::new, MobCategory.AMBIENT)
                    .build(new ResourceLocation(CrucifixMod.MOD_ID, "chains").toString()));
}

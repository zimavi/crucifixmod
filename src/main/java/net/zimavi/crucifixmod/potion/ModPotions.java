package net.zimavi.crucifixmod.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zimavi.crucifixmod.CrucifixMod;
import net.zimavi.crucifixmod.effect.ModEffects;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, CrucifixMod.MOD_ID);

    public static final RegistryObject<Potion> DEVELISH_RESILIENCE_POTION = POTIONS.register("develish_resilience_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.DEVILISH_RESILIENCE_EFFECT.get(), 3600, 0)));
    public static final RegistryObject<Potion> LONG_DEVELISH_RESILIENCE_POTION = POTIONS.register("long_develish_resilience_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.DEVILISH_RESILIENCE_EFFECT.get(), 9600, 0)));

    public static final RegistryObject<Potion> HERB_OF_VIRIDIS_REGENERATION_POTION = POTIONS.register("herb_of_viridis_regeneration_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.REGENERATION, 36000, 0)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}

package net.zimavi.crucifixmod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zimavi.crucifixmod.CrucifixMod;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, CrucifixMod.MOD_ID);

    public static final RegistryObject<MobEffect> DEVILISH_RESILIENCE_EFFECT = MOB_EFFECTS.register("develish_resilience",
                    () -> new DevilishResilienceEffect(MobEffectCategory.NEUTRAL, 15418118));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}

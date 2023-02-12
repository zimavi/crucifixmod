package net.zimavi.crucifixmod.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zimavi.crucifixmod.CrucifixMod;

public class ModSounds{
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, CrucifixMod.MOD_ID);

    public static final RegistryObject<SoundEvent> CRUCIFIX_USE =
            registerSoundEvent("crucifix_use");

    public static final  RegistryObject<SoundEvent> CRUCIFIX_FAIL =
            registerSoundEvent("crucifix_fail_use");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name){
        return  SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(CrucifixMod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }
}

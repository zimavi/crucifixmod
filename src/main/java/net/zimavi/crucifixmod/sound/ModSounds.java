package net.zimavi.crucifixmod.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
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

    public static final RegistryObject<SoundEvent> CRUCIFIX_FAIL =
            registerSoundEvent("crucifix_fail_use");

    public static final RegistryObject<SoundEvent> CRUCIFIX_REANIMATION =
            registerSoundEvent("crucifix_reanimation");

    //music segment

    public static final RegistryObject<SoundEvent> MUSIC_DAWN_OF_THE_DOORS = registerMusicSoundEvent("music_dawn_of_the_doors");
    public static final RegistryObject<SoundEvent> MUSIC_ELEVATOR_JAM = registerMusicSoundEvent("music_elevator_jam");
    public static final RegistryObject<SoundEvent> MUSIC_GUIDING_LIGHT = registerMusicSoundEvent("music_guiding_light");
    public static final RegistryObject<SoundEvent> MUSIC_HERE_I_COME = registerMusicSoundEvent("music_here_i_come");
    public static final RegistryObject<SoundEvent> MUSIC_JEFFS_JINGLE = registerMusicSoundEvent("music_jeffs_jingle");
    public static final RegistryObject<SoundEvent> MUSIC_UNHINGED = registerMusicSoundEvent("music_unhinged");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name){
        return SOUND_EVENTS.register(name, () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(CrucifixMod.MOD_ID, name), 15));
    }

    private static RegistryObject<SoundEvent> registerMusicSoundEvent(String name){
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(CrucifixMod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }
}

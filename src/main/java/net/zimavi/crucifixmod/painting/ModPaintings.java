package net.zimavi.crucifixmod.painting;

import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zimavi.crucifixmod.CrucifixMod;

public class ModPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, CrucifixMod.MOD_ID);

    public static final RegistryObject<PaintingVariant> QUITENESS = PAINTING_VARIANTS.register("quiteness",
            () -> new PaintingVariant(32, 32));

    public static final RegistryObject<PaintingVariant> THIS_PAINTING_DOSENT_SEEM_TO_HAVE_A_TITLE = PAINTING_VARIANTS.register(
            "this_painting_doesnt_seem_to_have_a_title",
            () -> new PaintingVariant(64, 32));

    public static final RegistryObject<PaintingVariant> VIRSTE_FREX = PAINTING_VARIANTS.register("virste_frex",
            () -> new PaintingVariant(16, 32));

    public static final RegistryObject<PaintingVariant> ITS_NOT_A_PAINTING = PAINTING_VARIANTS.register("its_not_a_painting",
            () -> new PaintingVariant(32, 16));

    public static final RegistryObject<PaintingVariant> OUTLINER = PAINTING_VARIANTS.register("outliner",
            () -> new PaintingVariant(32, 16));

    public static final RegistryObject<PaintingVariant> WHITE_CELINDER = PAINTING_VARIANTS.register("white_celinder",
            () -> new PaintingVariant(16, 32));

    public static final RegistryObject<PaintingVariant> ALPHA = PAINTING_VARIANTS.register("alpha",
            () -> new PaintingVariant(64, 48));
    public static final RegistryObject<PaintingVariant> STREETLIGHT = PAINTING_VARIANTS.register("streetlight",
            () -> new PaintingVariant(32, 32));
    public static final RegistryObject<PaintingVariant> GREEDINESS = PAINTING_VARIANTS.register("greediness",
            () -> new PaintingVariant(32, 32));

    public static void register(IEventBus eventBus){
        PAINTING_VARIANTS.register(eventBus);
    }
}

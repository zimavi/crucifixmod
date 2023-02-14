package net.zimavi.crucifixmod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.zimavi.crucifixmod.entity.ModEntities;
import net.zimavi.crucifixmod.entity.client.ChainsRenderer;
import net.zimavi.crucifixmod.entity.custom.ChainsEntity;
import net.zimavi.crucifixmod.item.ModItems;
import net.zimavi.crucifixmod.sound.ModSounds;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;

@Mod(CrucifixMod.MOD_ID)
public class CrucifixMod {
    public static final String MOD_ID = "crucifixmod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public CrucifixMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);

        ModSounds.register(modEventBus);

        ModEntities.register(modEventBus);

        GeckoLib.initialize();

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.CHAINS.get(), ChainsRenderer::new);
        }
    }
}

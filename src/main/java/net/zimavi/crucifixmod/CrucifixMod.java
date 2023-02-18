package net.zimavi.crucifixmod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.zimavi.crucifixmod.block.ModBlocks;
import net.zimavi.crucifixmod.effect.ModEffects;
import net.zimavi.crucifixmod.entity.ModEntities;
import net.zimavi.crucifixmod.entity.client.ChainsRenderer;
import net.zimavi.crucifixmod.entity.custom.ChainsEntity;
import net.zimavi.crucifixmod.item.ModCreativeModTab;
import net.zimavi.crucifixmod.item.ModItems;
import net.zimavi.crucifixmod.loot.ModLootModifiers;
import net.zimavi.crucifixmod.networking.ModMessages;
import net.zimavi.crucifixmod.painting.ModPaintings;
import net.zimavi.crucifixmod.potion.ModPotions;
import net.zimavi.crucifixmod.sound.ModSounds;
import net.zimavi.crucifixmod.util.BetterBrewingRecipe;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

@Mod(CrucifixMod.MOD_ID)
public class CrucifixMod {
    public static final String MOD_ID = "crucifixmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CrucifixMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //registering

        ModLootModifiers.register(modEventBus);

        ModPaintings.register(modEventBus);

        ModItems.register(modEventBus);

        ModSounds.register(modEventBus);

        ModEffects.register(modEventBus);

        ModPotions.register(modEventBus);

        ModEntities.register(modEventBus);

        ModBlocks.register(modEventBus);

        GeckoLib.initialize();

        modEventBus.addListener(this::commonSetup);

        modEventBus.addListener(this::addCreative);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModMessages.register();

            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.HERB_OF_VIRIDIS.getId(), ModBlocks.POTTED_HERB_OF_VIRIDIS);

            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.POISON, ModBlocks.HERB_OF_VIRIDIS.get().asItem(), ModPotions.DEVELISH_RESILIENCE_POTION.get()));
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(ModPotions.DEVELISH_RESILIENCE_POTION.get(), Items.REDSTONE, ModPotions.LONG_DEVELISH_RESILIENCE_POTION.get()));
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.WATER, ModBlocks.HERB_OF_VIRIDIS.get().asItem(), ModPotions.HERB_OF_VIRIDIS_REGENERATION_POTION.get())); //CHANGE TO NEW REGENERATION!!!
        });

    }

    private void addCreative(CreativeModeTabEvent.BuildContents event){
        if(event.getTab() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.CRUCIFIX);
        }

        if(event.getTab() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.VITAMINS);
        }

        if(event.getTab() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModBlocks.HERB_OF_VIRIDIS);
        }


        if(event.getTab() == ModCreativeModTab.CRUCIFIX_TAB){
            event.accept(ModItems.CRUCIFIX);
            event.accept(ModItems.VITAMINS);
            event.accept(ModBlocks.HERB_OF_VIRIDIS);
        }
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.CHAINS.get(), ChainsRenderer::new);
        }
    }
}

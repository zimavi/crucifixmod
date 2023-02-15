package net.zimavi.crucifixmod.block;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zimavi.crucifixmod.CrucifixMod;
import net.zimavi.crucifixmod.effect.ModEffects;
import net.zimavi.crucifixmod.item.ModCreativeModTab;
import net.zimavi.crucifixmod.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, CrucifixMod.MOD_ID);

    public static final RegistryObject<Block> HERB_OF_VIRIDIS = registerBlock("herb_of_viridis",
            () -> new FlowerBlock(MobEffects.GLOWING, 5,
                    BlockBehaviour.Properties.copy(Blocks.DANDELION)), ModCreativeModTab.CRUCIFIX_TAB);

    public static final RegistryObject<Block> POTTED_HERB_OF_VIRIDIS = BLOCKS.register("potted_herb_of_viridis",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.HERB_OF_VIRIDIS,
                    BlockBehaviour.Properties.copy(Blocks.DANDELION)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}

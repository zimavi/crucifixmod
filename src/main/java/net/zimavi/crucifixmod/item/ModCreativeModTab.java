package net.zimavi.crucifixmod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.zimavi.crucifixmod.CrucifixMod;

@Mod.EventBusSubscriber(modid = CrucifixMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModTab {
    public static CreativeModeTab CRUCIFIX_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event){
        CRUCIFIX_TAB = event.registerCreativeModeTab(new ResourceLocation(CrucifixMod.MOD_ID, "crucifix_tab"),
                builder -> builder.icon(() -> new ItemStack(ModItems.CRUCIFIX::get)).title(Component.translatable("itemGroup.crucifixtab")).build());
    }
}

package net.zimavi.crucifixmod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zimavi.crucifixmod.CrucifixMod;
import net.zimavi.crucifixmod.item.custom.CrucifixItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CrucifixMod.MOD_ID);

    public static final RegistryObject<Item> CRUCIFIX = ITEMS.register("crucifix",
            () -> new CrucifixItem(new Item.Properties()
                    .tab(ModCreativeModTab.CRUCIFIX_TAB)
                    .stacksTo(1)
                    .durability(1)
                    .defaultDurability(1)
                    .setNoRepair()
                    .rarity(Rarity.RARE)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

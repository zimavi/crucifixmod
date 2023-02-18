package net.zimavi.crucifixmod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zimavi.crucifixmod.CrucifixMod;
import net.zimavi.crucifixmod.entity.ModEntities;
import net.zimavi.crucifixmod.item.custom.CrucifixItem;
import net.zimavi.crucifixmod.item.custom.VitaminsItem;
import net.zimavi.crucifixmod.sound.ModSounds;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CrucifixMod.MOD_ID);

    public static final RegistryObject<Item> CRUCIFIX = ITEMS.register("crucifix",
            () -> new CrucifixItem(new Item.Properties()
                    .stacksTo(1)
                    .durability(1)
                    .defaultDurability(1)
                    .setNoRepair()
                    .rarity(Rarity.RARE)));

    public static final RegistryObject<Item> VITAMINS = ITEMS.register("vitamins",
            () -> new VitaminsItem(new Item.Properties()
                    .stacksTo(16)
                    .setNoRepair()
                    .rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> DAWN_OF_THE_DOORS_RECORD = ITEMS.register("dawn_of_the_doors_record",
            () -> new RecordItem(1, ModSounds.MUSIC_DAWN_OF_THE_DOORS.get(), new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.RARE), 4680));
    public static final RegistryObject<Item> ELEVATOR_JAM_RECORD = ITEMS.register("elevator_jam_record",
            () -> new RecordItem(2, ModSounds.MUSIC_ELEVATOR_JAM.get(), new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.RARE), 620));
    public static final RegistryObject<Item> GUIDING_LIGHT_RECORD = ITEMS.register("guiding_light_record",
            () -> new RecordItem(3, ModSounds.MUSIC_GUIDING_LIGHT.get(), new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.RARE), 1200));
    public static final RegistryObject<Item> HERE_I_COME_RECORD = ITEMS.register("here_i_come_record",
            () -> new RecordItem(4, ModSounds.MUSIC_HERE_I_COME.get(), new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.RARE), 2220));
    public static final RegistryObject<Item> JEFFS_JINGLE_RECORD = ITEMS.register("jeffs_jingle_record",
            () -> new RecordItem(5, ModSounds.MUSIC_JEFFS_JINGLE.get(), new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.RARE), 2500));
    public static final RegistryObject<Item> UNHINGED_RECORD = ITEMS.register("unhinged_record",
            () -> new RecordItem(6, ModSounds.MUSIC_UNHINGED.get(), new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.RARE), 3820));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

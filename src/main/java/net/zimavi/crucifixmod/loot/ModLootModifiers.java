package net.zimavi.crucifixmod.loot;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zimavi.crucifixmod.CrucifixMod;

public class ModLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, CrucifixMod.MOD_ID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> VITAMINS_IN_PLAINS_CHESTS =
            LOOT_MODIFIER_SERIALIZERS.register("vitamins_in_plains_chests", VitaminsInPlainsChests.CODEC);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> VITAMINS_IN_SNOWY_CHESTS =
            LOOT_MODIFIER_SERIALIZERS.register("vitamins_in_snowy_chests", VitaminsInSnowyChests.CODEC);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> VITAMINS_IN_DESERT_CHESTS =
            LOOT_MODIFIER_SERIALIZERS.register("vitamins_in_desert_chests", VitaminsInDesertChests.CODEC);

    public static void register(IEventBus eventBus){
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }
}

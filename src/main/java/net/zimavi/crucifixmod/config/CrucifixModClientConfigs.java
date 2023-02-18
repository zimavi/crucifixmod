package net.zimavi.crucifixmod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CrucifixModClientConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    static {
        BUILDER.push("Client configs for Crucifix Mod");

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}

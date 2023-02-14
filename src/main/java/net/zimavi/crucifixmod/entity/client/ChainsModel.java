package net.zimavi.crucifixmod.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.zimavi.crucifixmod.CrucifixMod;
import net.zimavi.crucifixmod.entity.custom.ChainsEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ChainsModel extends AnimatedGeoModel<ChainsEntity> {
    @Override
    public ResourceLocation getModelResource(ChainsEntity object) {
        return new ResourceLocation(CrucifixMod.MOD_ID, "geo/chains.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ChainsEntity object) {
        return new ResourceLocation(CrucifixMod.MOD_ID, "textures/entity/chains_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ChainsEntity animatable) {
        return new ResourceLocation(CrucifixMod.MOD_ID, "animations/chains.animation.json");
    }
}

package net.zimavi.crucifixmod.entity.client;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.zimavi.crucifixmod.CrucifixMod;
import net.zimavi.crucifixmod.entity.custom.ChainsEntity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class ChainsRenderer extends GeoEntityRenderer<ChainsEntity> {
    public ChainsRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ChainsModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(ChainsEntity instance) {
        return new ResourceLocation(CrucifixMod.MOD_ID, "textures/entity/chains_texture.png");
    }

    @Override
    public RenderType getRenderType(ChainsEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {

        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }
}

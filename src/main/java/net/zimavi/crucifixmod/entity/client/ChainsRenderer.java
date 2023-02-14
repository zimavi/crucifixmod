package net.zimavi.crucifixmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.zimavi.crucifixmod.CrucifixMod;
import net.zimavi.crucifixmod.entity.custom.ChainsEntity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

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
    public RenderType getRenderType(ChainsEntity animatable, float partialTick, PoseStack poseStack,
                                    @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer,
                                    int packedLight, ResourceLocation texture) {
        poseStack.scale(2f, 2f, 2f);
        return super.getRenderType(animatable, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
    }
}

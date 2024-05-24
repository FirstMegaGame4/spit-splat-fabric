package fr.firstmegagame4.spitsplat.client.entity.renderer;

import fr.firstmegagame4.spitsplat.client.entity.model.BubbleEntityModel;
import fr.firstmegagame4.spitsplat.entity.BubbleEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BubbleEntityRenderer extends GeoEntityRenderer<BubbleEntity> {

	public BubbleEntityRenderer(EntityRendererFactory.Context renderManager) {
		super(renderManager, new BubbleEntityModel());
		this.shadowRadius = 0.0f;
	}

	@Override
	@Nullable
	public RenderLayer getRenderType(BubbleEntity animatable, Identifier texture, @Nullable VertexConsumerProvider bufferSource, float partialTick) {
		return RenderLayer.getEntityTranslucent(this.getTextureLocation(animatable));
	}

	@Override
	public void preRender(MatrixStack poseStack, BubbleEntity animatable, BakedGeoModel model, @Nullable VertexConsumerProvider bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		if (animatable == null) {
			this.scaleHeight = 1.0f;
			this.scaleWidth = 0.0f;
		}
		else if (animatable.getVehicle() instanceof LivingEntity) {
			this.scaleHeight = 0.7f * animatable.getVehicle().getHeight();
			this.scaleWidth = 0.7f * animatable.getVehicle().getHeight();
		}
		else {
			this.scaleHeight = 1.0f;
			this.scaleWidth = 1.0f;
		}
		super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	protected float getDeathMaxRotation(BubbleEntity animatable) {
		return 0.0f;
	}
}

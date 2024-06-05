package fr.firstmegagame4.spitsplat.client.entity.renderer;

import fr.firstmegagame4.spitsplat.client.entity.model.BubbleModel;
import fr.firstmegagame4.spitsplat.client.entity.renderer.dummy.BubbleDummy;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoObjectRenderer;

public class BubbleRenderer extends GeoObjectRenderer<BubbleDummy> {

	private final boolean isFeature;

	public BubbleRenderer(EntityRendererFactory.Context ignored) {
		super(new BubbleModel());
		this.isFeature = false;
	}

	public BubbleRenderer() {
		super(new BubbleModel());
		this.isFeature = true;
	}

	@Override
	@Nullable
	public RenderLayer getRenderType(BubbleDummy animatable, Identifier texture, @Nullable VertexConsumerProvider bufferSource, float partialTick) {
		return RenderLayer.getEntityTranslucent(this.getTextureLocation(animatable));
	}

	@Override
	public void preRender(MatrixStack poseStack, BubbleDummy animatable, BakedGeoModel model, @Nullable VertexConsumerProvider bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		if (this.isFeature) {
			this.scaleHeight = (float) (0.7 * animatable.getCaughtEntity().getBoundingBox().getLengthY());
			this.scaleWidth = (float) (0.7 * animatable.getCaughtEntity().getBoundingBox().getLengthY());
		}
		else {
			this.scaleHeight = 0.25f;
			this.scaleWidth = 0.25f;
		}
		super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
	}
}

package fr.firstmegagame4.spitsplat.client.entity.renderer.feature;

import fr.firstmegagame4.spitsplat.client.entity.renderer.BubbleEntityRenderer;
import fr.firstmegagame4.spitsplat.client.entity.renderer.dummy.BubbleDummy;
import fr.firstmegagame4.spitsplat.init.SpitSplatStatusEffects;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class BubbleEntityFeatureRenderer<T extends Entity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {

	private final BubbleEntityRenderer renderer = new BubbleEntityRenderer();

	public BubbleEntityFeatureRenderer(FeatureRendererContext<T, M> context) {
		super(context);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, Entity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
		if (entity instanceof LivingEntity livingEntity) {
			if (livingEntity.hasStatusEffect(SpitSplatStatusEffects.BUBBLE_TRAP)) {
				BubbleDummy dummy = new BubbleDummy(livingEntity);
				RenderLayer layer = this.renderer.getRenderType(dummy, this.renderer.getTextureLocation(dummy), vertexConsumers, tickDelta);
				VertexConsumer consumer = vertexConsumers.getBuffer(layer);
				this.renderer.render(matrices, dummy, vertexConsumers, layer, consumer, light);
			}
		}
	}
}

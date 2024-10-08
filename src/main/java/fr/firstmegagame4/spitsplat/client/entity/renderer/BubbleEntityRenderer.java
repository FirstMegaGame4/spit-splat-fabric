package fr.firstmegagame4.spitsplat.client.entity.renderer;

import fr.firstmegagame4.spitsplat.client.entity.renderer.dummy.BubbleDummy;
import fr.firstmegagame4.spitsplat.entity.BubbleEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BubbleEntityRenderer extends EntityRenderer<BubbleEntity> {

	private final BubbleRenderer renderer;
	private final BubbleDummy dummy;

	public BubbleEntityRenderer(EntityRendererFactory.Context ctx) {
		super(ctx);
		this.renderer = new BubbleRenderer(ctx);
		this.dummy = new BubbleDummy(null);
	}

	@Override
	public void render(BubbleEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
		RenderLayer layer = this.renderer.getRenderType(this.dummy, this.renderer.getTextureLocation(this.dummy), vertexConsumers, tickDelta);
		VertexConsumer consumer = vertexConsumers.getBuffer(layer);
		this.renderer.render(matrices, this.dummy, vertexConsumers, layer, consumer, light, tickDelta);
	}

	@Override
	public Identifier getTexture(BubbleEntity entity) {
		return this.renderer.getTextureLocation(this.dummy);
	}
}

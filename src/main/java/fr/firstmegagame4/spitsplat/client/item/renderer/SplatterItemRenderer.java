package fr.firstmegagame4.spitsplat.client.item.renderer;

import fr.firstmegagame4.spitsplat.client.item.model.SplatterItemModel;
import fr.firstmegagame4.spitsplat.item.SplatterItem;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class SplatterItemRenderer extends GeoItemRenderer<SplatterItem> {

	public SplatterItemRenderer() {
		super(new SplatterItemModel());
	}

	@Override
	@Nullable
	public RenderLayer getRenderType(SplatterItem animatable, Identifier texture, @Nullable VertexConsumerProvider bufferSource, float partialTick) {
		return RenderLayer.getEntityTranslucent(this.getTextureLocation(animatable));
	}

	@Override
	public void render(ItemStack stack, ModelTransformationMode transformType, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight, int packedOverlay) {
		if (this.animatable != null) {
			this.animatable.setModelTransformationMode(transformType);
		}
		super.render(stack, transformType, poseStack, bufferSource, packedLight, packedOverlay);
	}
}

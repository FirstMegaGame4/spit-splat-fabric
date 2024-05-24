package fr.firstmegagame4.spitsplat.client.init;


import fr.firstmegagame4.spitsplat.client.entity.renderer.BubbleEntityRenderer;
import fr.firstmegagame4.spitsplat.init.SpitSplatEntities;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class SpitSplatEntityRenderers {

	public static void register() {
		EntityRendererRegistry.register(SpitSplatEntities.BUBBLE, BubbleEntityRenderer::new);
	}
}

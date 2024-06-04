package fr.firstmegagame4.spitsplat.client.init;

import fr.firstmegagame4.spitsplat.init.SpitSplatEntities;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class SpitSplatEntityRenderers {

	public static void register() {
		EntityRendererRegistry.register(SpitSplatEntities.BUBBLE_PROJECTILE, FlyingItemEntityRenderer::new);
	}
}

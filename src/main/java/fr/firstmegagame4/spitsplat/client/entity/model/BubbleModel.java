package fr.firstmegagame4.spitsplat.client.entity.model;

import fr.firstmegagame4.spitsplat.SpitSplat;
import fr.firstmegagame4.spitsplat.client.entity.renderer.dummy.BubbleDummy;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class BubbleModel extends GeoModel<BubbleDummy> {

	@Override
	public Identifier getModelResource(BubbleDummy animatable) {
		return SpitSplat.createId("geo/bubble_entity.geo.json");
	}

	@Override
	public Identifier getTextureResource(BubbleDummy animatable) {
		return SpitSplat.createId("textures/entity/bubble_entity.png");
	}

	@Override
	public Identifier getAnimationResource(BubbleDummy animatable) {
		return SpitSplat.createId("animations/bubble_entity.animation.json");
	}
}

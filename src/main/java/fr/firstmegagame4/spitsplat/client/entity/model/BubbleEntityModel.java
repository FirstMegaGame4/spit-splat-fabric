package fr.firstmegagame4.spitsplat.client.entity.model;

import fr.firstmegagame4.spitsplat.SpitSplat;
import fr.firstmegagame4.spitsplat.entity.BubbleEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class BubbleEntityModel extends GeoModel<BubbleEntity> {

	@Override
	public Identifier getModelResource(BubbleEntity animatable) {
		return SpitSplat.createId("geo/bubble_entity.geo.json");
	}

	@Override
	public Identifier getTextureResource(BubbleEntity animatable) {
		return SpitSplat.createId("textures/entity/bubble_entity.png");
	}

	@Override
	public Identifier getAnimationResource(BubbleEntity animatable) {
		return SpitSplat.createId("animations/bubble_entity.animation.json");
	}
}

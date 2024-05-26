package fr.firstmegagame4.spitsplat.client.item.model;

import fr.firstmegagame4.spitsplat.SpitSplat;
import fr.firstmegagame4.spitsplat.item.SplatterItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class SplatterItemModel extends GeoModel<SplatterItem> {

	@Override
	public Identifier getModelResource(SplatterItem animatable) {
		return SpitSplat.createId("geo/splatter.geo.json");
	}

	@Override
	public Identifier getTextureResource(SplatterItem animatable) {
		return SpitSplat.createId("textures/item/splatter.png");
	}

	@Override
	public Identifier getAnimationResource(SplatterItem animatable) {
		return SpitSplat.createId("animations/splatter.animation.json");
	}
}

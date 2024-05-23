package fr.firstmegagame4.spitsplat.item;

import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.item.Item;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class SplatterItem extends Item implements GeoItem {

	public static ModelTransformationMode MODEL_TRANSFORMATION_MODE;

	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

	public String animationProcedure = "empty";

	public SplatterItem(Settings settings) {
		super(settings);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar data) {
		AnimationController<SplatterItem> procedureController = new AnimationController<>(this, "procedureController", 0, this::procedurePredicate);
		AnimationController<SplatterItem> idleController = new AnimationController<>(this, "idleController", 0, this::idlePredicate);
		data.add(procedureController);
		data.add(idleController);
	}

	private PlayState idlePredicate(AnimationState<SplatterItem> event) {
		if (SplatterItem.MODEL_TRANSFORMATION_MODE != null) {
			if (this.animationProcedure.equals("empty")) {
				event.getController().setAnimation(RawAnimation.begin().thenLoop("idle"));
				return PlayState.CONTINUE;
			}
		}
		return PlayState.STOP;
	}

	private PlayState procedurePredicate(AnimationState<SplatterItem> event) {
		if (SplatterItem.MODEL_TRANSFORMATION_MODE != null) {
			if (!this.animationProcedure.equals("empty") && event.getController().getAnimationState() == AnimationController.State.STOPPED) {
				event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationProcedure));
				if (event.getController().getAnimationState() == AnimationController.State.STOPPED) {
					this.animationProcedure = "empty";
					event.getController().forceAnimationReset();
				}
			}
			else if (this.animationProcedure.equals("empty")) {
				return PlayState.STOP;
			}
		}
		return PlayState.CONTINUE;
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}
}

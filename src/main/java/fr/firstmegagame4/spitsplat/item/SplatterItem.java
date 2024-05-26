package fr.firstmegagame4.spitsplat.item;

import fr.firstmegagame4.spitsplat.client.item.renderer.SplatterItemRenderer;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class SplatterItem extends Item implements GeoItem {

	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

	public ModelTransformationMode modelTransformationMode;
	public String animationProcedure = "empty";

	public SplatterItem(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		return null;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar data) {
		AnimationController<SplatterItem> procedureController = new AnimationController<>(this, "procedureController", 0, this::procedurePredicate);
		AnimationController<SplatterItem> idleController = new AnimationController<>(this, "idleController", 0, this::idlePredicate);
		data.add(procedureController);
		data.add(idleController);
	}

	private PlayState idlePredicate(AnimationState<SplatterItem> event) {
		if (this.modelTransformationMode != null) {
			if (this.animationProcedure.equals("empty")) {
				event.getController().setAnimation(RawAnimation.begin().thenLoop("idle"));
				return PlayState.CONTINUE;
			}
		}
		return PlayState.STOP;
	}

	private PlayState procedurePredicate(AnimationState<SplatterItem> event) {
		if (this.modelTransformationMode != null) {
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

	public void setModelTransformationMode(ModelTransformationMode mode) {
		this.modelTransformationMode = mode;
	}

	@Override
	public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
		consumer.accept(new GeoRenderProvider() {

			private SplatterItemRenderer renderer;

			@Override
			public BuiltinModelItemRenderer getGeoItemRenderer() {
				if (this.renderer == null) {
					this.renderer = new SplatterItemRenderer();
				}
				return this.renderer;
			}
		});
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}
}

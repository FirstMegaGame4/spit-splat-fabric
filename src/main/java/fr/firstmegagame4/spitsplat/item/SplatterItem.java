package fr.firstmegagame4.spitsplat.item;

import com.mojang.serialization.Codec;
import fr.firstmegagame4.spitsplat.client.item.renderer.SplatterItemRenderer;
import fr.firstmegagame4.spitsplat.entity.BubbleProjectileEntity;
import fr.firstmegagame4.spitsplat.init.SpitSplatDataComponents;
import fr.firstmegagame4.spitsplat.init.SpitSplatItems;
import fr.firstmegagame4.spitsplat.init.SpitSplatSoundEvents;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
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
	public Animation animation = Animation.NONE;

	public SplatterItem(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		TypedActionResult<ItemStack> tar = super.use(world, user, hand);
		ItemStack stack = tar.getValue();
		if (stack.isOf(SpitSplatItems.SPLATTER)) {
			stack.set(SpitSplatDataComponents.SPLATTER_ANIMATION, Animation.SHOOT);
		}
		world.playSound(null, BlockPos.ofFloored(user.getPos()), SpitSplatSoundEvents.SPLATTER_SHOT, SoundCategory.PLAYERS, 1.0f, 1.0f);
		user.getItemCooldownManager().set(stack.getItem(), 40);
		BubbleProjectileEntity projectile = new BubbleProjectileEntity(world, user);
		world.spawnEntity(projectile);
		return new TypedActionResult<>(ActionResult.CONSUME, stack);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar data) {
		AnimationController<SplatterItem> updateController = new AnimationController<>(this, "updateController", 0, this::updatePredicate);
		AnimationController<SplatterItem> idleController = new AnimationController<>(this, "idleController", 0, this::idlePredicate);
		data.add(updateController);
		data.add(idleController);
	}

	private PlayState idlePredicate(AnimationState<SplatterItem> event) {
		if (this.modelTransformationMode != null) {
			if (this.animation.equals(Animation.NONE)) {
				event.getController().setAnimation(RawAnimation.begin().thenLoop("idle"));
				return PlayState.CONTINUE;
			}
		}
		return PlayState.STOP;
	}

	private PlayState updatePredicate(AnimationState<SplatterItem> event) {
		if (this.modelTransformationMode != null) {
			if (!this.animation.equals(Animation.NONE) && event.getController().getAnimationState() == AnimationController.State.STOPPED) {
				event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animation.asString()));
				if (event.getController().getAnimationState() == AnimationController.State.STOPPED) {
					this.animation = Animation.NONE;
					event.getController().forceAnimationReset();
				}
			}
			else if (this.animation.equals(Animation.NONE)) {
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

	public enum Animation implements StringIdentifiable {

		NONE("none"),
		SHOOT("shoot"),
		IDLE("idle");

		public static final Codec<Animation> CODEC = StringIdentifiable.createCodec(SplatterItem.Animation::values);

		private final String identifier;

		Animation(String identifier) {
			this.identifier = identifier;
		}

		@Override
		public String asString() {
			return this.identifier;
		}
	}
}

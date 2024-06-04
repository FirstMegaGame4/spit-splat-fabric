package fr.firstmegagame4.spitsplat.client.entity.renderer.dummy;

import net.minecraft.entity.LivingEntity;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class BubbleDummy implements GeoAnimatable {

	private final AnimatableInstanceCache cache;

	private final LivingEntity caughtEntity;

	public BubbleDummy(LivingEntity caughtEntity) {
		this.cache = GeckoLibUtil.createInstanceCache(this);
		this.caughtEntity = caughtEntity;
	}

	public LivingEntity getCaughtEntity() {
		return this.caughtEntity;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}

	@Override
	public double getTick(Object object) {
		return 0;
	}
}

package fr.firstmegagame4.spitsplat.entity;

import fr.firstmegagame4.spitsplat.init.SpitSplatStatusEffects;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class BubbleEntity extends Entity implements GeoAnimatable {

	public static final TrackedData<Boolean> SHOOT = DataTracker.registerData(BubbleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	public static final TrackedData<String> ANIMATION = DataTracker.registerData(BubbleEntity.class, TrackedDataHandlerRegistry.STRING);

	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

	public String animationProcedure = "empty";

	public BubbleEntity(EntityType<? extends BubbleEntity> type, World world) {
		super(type, world);
	}

	public void onSpawned(World world, Vec3d position) {
		world.getEntitiesByClass(
			LivingEntity.class,
			Box.of(position, 4, 4, 4),
			livingEntity -> livingEntity.hasStatusEffect(RegistryEntry.of(SpitSplatStatusEffects.BUBBLE_TRAP))
		).stream().findFirst().ifPresent(this::startRiding);
	}

	@Override
	protected void initDataTracker(DataTracker.Builder builder) {
		builder.add(SHOOT, false);
		builder.add(ANIMATION, "bubble_animation");
	}

	@Override
	public void baseTick() {
		super.baseTick();
		if (!this.hasVehicle() || !(this.getVehicle() instanceof LivingEntity livingEntity) || !livingEntity.hasStatusEffect(RegistryEntry.of(SpitSplatStatusEffects.BUBBLE_TRAP))) {
			this.discard();
		}
		this.calculateDimensions();
	}

	@Override
	public void onRemoved() {
		this.playSound(SoundEvents.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, 1.0f, 1.0f);
	}

	@Override
	public void setNoGravity(boolean noGravity) {
		super.setNoGravity(true);
	}

	@Override
	public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
		return false;
	}

	@Override
	public boolean damage(DamageSource source, float amount) {
		return false;
	}

	@Override
	protected void readCustomDataFromNbt(NbtCompound nbt) {
	}

	@Override
	protected void writeCustomDataToNbt(NbtCompound nbt) {
	}

	@Override
	public EntityDimensions getDimensions(EntityPose pose) {
		return super.getDimensions(pose).scaled(this.getVehicle() instanceof LivingEntity ? this.getVehicle().getHeight() * 0.7f : 1.0f);
	}

	@Override
	public boolean shouldDismountUnderwater() {
		return false;
	}

	@Override
	public boolean isPushedByFluids() {
		return false;
	}

	@Override
	public void pushAwayFrom(Entity entity) {
	}

	private PlayState movementPredicate(AnimationState<BubbleEntity> event) {
		if (this.animationProcedure.equals("empty")) {
			return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
		}
		return PlayState.STOP;
	}

	private PlayState procedurePredicate(AnimationState<BubbleEntity> event) {
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
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "movement", 4, this::movementPredicate));
		controllers.add(new AnimationController<>(this, "procedure", 4, this::procedurePredicate));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}

	@Override
	public double getTick(Object object) {
		return this.age;
	}
}

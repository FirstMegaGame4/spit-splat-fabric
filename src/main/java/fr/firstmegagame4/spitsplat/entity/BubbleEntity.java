package fr.firstmegagame4.spitsplat.entity;

import fr.firstmegagame4.spitsplat.init.SpitSplatStatusEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
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
import software.bernie.geckolib.animation.AnimatableManager;

public class BubbleEntity extends Entity implements GeoAnimatable {

	public static final TrackedData<Boolean> SHOOT = DataTracker.registerData(BubbleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	public static final TrackedData<String> ANIMATION = DataTracker.registerData(BubbleEntity.class, TrackedDataHandlerRegistry.STRING);
	public static final TrackedData<String> TEXTURE = DataTracker.registerData(BubbleEntity.class, TrackedDataHandlerRegistry.STRING);

	public BubbleEntity(EntityType<?> type, World world) {
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
		this.dataTracker.set(SHOOT, false);
		this.dataTracker.set(ANIMATION, "bubble_animation");
		this.dataTracker.set(TEXTURE, "bubble_entity");
	}

	@Override
	public void onRemoved() {
		this.playSound(SoundEvents.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, 1.0f, 1.0f);
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
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return null;
	}

	@Override
	public double getTick(Object object) {
		return 0;
	}
}

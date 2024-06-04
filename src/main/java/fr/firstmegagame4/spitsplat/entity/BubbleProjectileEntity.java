package fr.firstmegagame4.spitsplat.entity;

import fr.firstmegagame4.spitsplat.init.SpitSplatEntities;
import fr.firstmegagame4.spitsplat.init.SpitSplatItems;
import fr.firstmegagame4.spitsplat.init.SpitSplatStatusEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class BubbleProjectileEntity extends PersistentProjectileEntity implements FlyingItemEntity {

	public BubbleProjectileEntity(EntityType<? extends BubbleProjectileEntity> type, World world) {
		super(type, world);
	}

	protected BubbleProjectileEntity(EntityType<? extends BubbleProjectileEntity> type, LivingEntity owner, World world, ItemStack stack) {
		super(type, owner, world, stack);
	}

	public BubbleProjectileEntity(World world, LivingEntity owner) {
		this(SpitSplatEntities.BUBBLE_PROJECTILE, owner, world, SpitSplatItems.BUBBLE.getDefaultStack());
		this.setVelocity(
			owner.getRotationVec(1).getX(),
			owner.getRotationVec(1).getY(),
			owner.getRotationVec(1).getZ(),
			3.0f,
			0
		);
		this.setSilent(true);
		this.setCritical(false);
		this.setDamage(0);
		this.setPunch(0);
	}

	@Override
	protected void onHit(LivingEntity target) {
		super.onHit(target);
		target.setStuckArrowCount(target.getStuckArrowCount() - 1);
	}

	@Override
	protected void onEntityHit(EntityHitResult entityHitResult) {
		super.onEntityHit(entityHitResult);
		Entity target = entityHitResult.getEntity();
		if (target != null && !target.hasPassengers() && !(target instanceof SpitSplatEntity)) {
			if (target instanceof LivingEntity livingTarget && !livingTarget.isBlocking()) {
				livingTarget.addStatusEffect(new StatusEffectInstance(SpitSplatStatusEffects.BUBBLE_TRAP, 40, 1));
			}
		}
	}

	@Override
	protected void onBlockHit(BlockHitResult blockHitResult) {
		super.onBlockHit(blockHitResult);
		if (this.getWorld() instanceof ServerWorld world) {
			world.spawnParticles(
				ParticleTypes.BUBBLE,
				blockHitResult.getBlockPos().getX() + 0.5,
				blockHitResult.getBlockPos().getY() + 0.5,
				blockHitResult.getBlockPos().getZ() + 0.5,
				20,
				1,
				1,
				1,
				0.1
			);
		}
	}

	@Override
	public void tick() {
		super.tick();
		if (this.age % 100 == 0) {
			if (this.getWorld() instanceof ServerWorld world) {
				world.spawnParticles(ParticleTypes.BUBBLE, this.getX(), this.getY(), this.getZ(), 5, 0.2, 0.2, 0.2, 0);
			}
		}
		if (this.isOnGround()) {
			this.discard();
		}
	}

	@Override
	public ItemStack getStack() {
		return SpitSplatItems.BUBBLE.getDefaultStack();
	}

	@Override
	protected ItemStack getDefaultItemStack() {
		return SpitSplatItems.BUBBLE.getDefaultStack();
	}
}

package fr.firstmegagame4.spitsplat.entity;

import fr.firstmegagame4.spitsplat.init.SpitSplatAttachments;
import fr.firstmegagame4.spitsplat.init.SpitSplatEntities;
import fr.firstmegagame4.spitsplat.init.SpitSplatStatusEffects;
import fr.firstmegagame4.spitsplat.payload.BubblePayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class BubbleEntity extends ThrownEntity {

	public BubbleEntity(EntityType<? extends BubbleEntity> type, World world) {
		super(type, world);
	}

	public BubbleEntity(World world, LivingEntity owner) {
		this(SpitSplatEntities.BUBBLE, world);
		this.setOwner(owner);
		this.setPosition(owner.getX(), owner.getEyeY() - 0.1f, owner.getZ());
		this.setVelocity(
			owner.getRotationVec(1).getX(),
			owner.getRotationVec(1).getY(),
			owner.getRotationVec(1).getZ(),
			3.0f,
			0
		);
		this.setSilent(true);
	}

	@Override
	protected void initDataTracker(DataTracker.Builder builder) {}

	@Override
	protected void onEntityHit(EntityHitResult entityHitResult) {
		super.onEntityHit(entityHitResult);
		Entity target = entityHitResult.getEntity();
		if (!(target instanceof SpitSplatEntity)) {
			if (target instanceof LivingEntity livingTarget && !livingTarget.isBlocking()) {
				livingTarget.addStatusEffect(new StatusEffectInstance(SpitSplatStatusEffects.BUBBLE_TRAP, 40, 1));
				if (this.getWorld() instanceof ServerWorld world) {
					livingTarget.setAttached(SpitSplatAttachments.BUBBLE_STARTED_AGE, target.age);
					world.getPlayers().forEach(player -> ServerPlayNetworking.send(player, new BubblePayload(target.getId(), target.age)));
				}
			}
		}
		this.discard();
	}

	@Override
	protected void onBlockHit(BlockHitResult blockHitResult) {
		super.onBlockHit(blockHitResult);
		if (this.getWorld() instanceof ServerWorld world) {
			int x = blockHitResult.getBlockPos().getX();
			int y = blockHitResult.getBlockPos().getY();
			int z = blockHitResult.getBlockPos().getZ();
			world.spawnParticles(ParticleTypes.BUBBLE, x+ 0.5, y + 0.5, z + 0.5, 20, 1, 1, 1, 0.1);
		}
	}

	@Override
	public void tick() {
		super.tick();
		if (this.getWorld() instanceof ServerWorld world) {
			world.spawnParticles(ParticleTypes.BUBBLE, this.getX(), this.getY(), this.getZ(), 5, 0.2, 0.2, 0.2, 0);
		}
	}
}

package fr.firstmegagame4.spitsplat.init;

import fr.firstmegagame4.spitsplat.SpitSplat;
import fr.firstmegagame4.spitsplat.entity.BubbleProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class SpitSplatEntities {

	/* public static final EntityType<BubbleEntity> BUBBLE = EntityType.Builder.create(BubbleEntity::new, SpawnGroup.MISC)
		.makeFireImmune()
		.dimensions(0.5f, 0.5f)
		.maxTrackingRange(8)
		.build(); */

	// weird
	@SuppressWarnings("unchecked")
	public static final EntityType<BubbleProjectileEntity> BUBBLE_PROJECTILE = (EntityType<BubbleProjectileEntity>)
		(EntityType<?>) EntityType.Builder.create(
			(type, world) -> new BubbleProjectileEntity((EntityType<? extends BubbleProjectileEntity>) (EntityType<?>) type, world),
			SpawnGroup.MISC
		)
			.makeFireImmune()
			.dimensions(0.5f, 0.5f)
			.maxTrackingRange(8)
			.trackingTickInterval(1)
			.build();

	public static void register() {
		// Registry.register(Registries.ENTITY_TYPE, SpitSplat.createId("bubble"), SpitSplatEntities.BUBBLE);
		Registry.register(Registries.ENTITY_TYPE, SpitSplat.createId("bubble_projectile"), SpitSplatEntities.BUBBLE_PROJECTILE);
	}
}

package fr.firstmegagame4.spitsplat.init;

import fr.firstmegagame4.spitsplat.SpitSplat;
import fr.firstmegagame4.spitsplat.entity.BubbleEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class SpitSplatEntities {

	public static final EntityType<BubbleEntity> BUBBLE = EntityType.Builder.create(BubbleEntity::new, SpawnGroup.MISC)
		.makeFireImmune()
		.dimensions(1.5f, 1.5f)
		.maxTrackingRange(8)
		.build();

	public static void register() {
		Registry.register(Registries.ENTITY_TYPE, SpitSplat.createId("bubble"), SpitSplatEntities.BUBBLE);
	}
}

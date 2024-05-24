package fr.firstmegagame4.spitsplat.effect;

import fr.firstmegagame4.spitsplat.entity.BubbleEntity;
import fr.firstmegagame4.spitsplat.init.SpitSplatEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;

public class BubbleTrapStatusEffect extends StatusEffect {

	public BubbleTrapStatusEffect(StatusEffectCategory category, int color) {
		super(category, color);
	}

	@Override
	public String getTranslationKey() {
		return "effect.spit_splat.bubble_trap";
	}

	@Override
	public void onApplied(LivingEntity entity, int amplifier) {
		if (entity != null && entity.getWorld() instanceof ServerWorld world) {
			BubbleEntity newEntity = new BubbleEntity(SpitSplatEntities.BUBBLE, world);
			world.spawnEntity(newEntity);
			newEntity.onSpawned(world, entity.getPos());
			newEntity.setYaw(world.getRandom().nextFloat() * 360.0f);
		}
	}

	@Override
	public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
		return super.applyUpdateEffect(entity, amplifier);
	}

	@Override
	public void onRemoved(AttributeContainer attributeContainer) {
		super.onRemoved(attributeContainer);
	}

	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		return true;
	}
}

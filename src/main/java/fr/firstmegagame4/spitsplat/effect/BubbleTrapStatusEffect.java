package fr.firstmegagame4.spitsplat.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class BubbleTrapStatusEffect extends StatusEffect {

	public BubbleTrapStatusEffect(StatusEffectCategory category, int color) {
		super(category, color);
	}

	@Override
	public String getTranslationKey() {
		return "effect.spit_splat.bubble_trap";
	}

	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		return true;
	}
}

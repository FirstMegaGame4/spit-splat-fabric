package fr.firstmegagame4.spitsplat.init;

import fr.firstmegagame4.spitsplat.SpitSplat;
import fr.firstmegagame4.spitsplat.effect.BubbleTrapStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class SpitSplatStatusEffects {

	public static final StatusEffect BUBBLE_TRAP = new BubbleTrapStatusEffect(StatusEffectCategory.HARMFUL, -10040065);

	public static void register() {
		Registry.register(Registries.STATUS_EFFECT, SpitSplat.createId("bubble_trap"), SpitSplatStatusEffects.BUBBLE_TRAP);
	}
}

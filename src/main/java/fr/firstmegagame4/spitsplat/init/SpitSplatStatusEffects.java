package fr.firstmegagame4.spitsplat.init;

import fr.firstmegagame4.spitsplat.SpitSplat;
import fr.firstmegagame4.spitsplat.effect.BubbleTrapStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class SpitSplatStatusEffects {

	public static final RegistryEntry<StatusEffect> BUBBLE_TRAP = SpitSplatStatusEffects.register(
		SpitSplat.createId("bubble_trap"),
		new BubbleTrapStatusEffect(StatusEffectCategory.HARMFUL, -10040065)
	);

	public static void register() {}

	private static RegistryEntry<StatusEffect> register(Identifier identifier, StatusEffect effect) {
		return Registry.registerReference(Registries.STATUS_EFFECT, identifier, effect);
	}
}

package fr.firstmegagame4.spitsplat.mixin.client;

import fr.firstmegagame4.spitsplat.init.SpitSplatEvents;
import fr.firstmegagame4.spitsplat.init.SpitSplatStatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

	@Shadow
	@Final
	private Map<RegistryEntry<StatusEffect>, StatusEffectInstance> activeStatusEffects;

	@Inject(method = "hasStatusEffect", at = @At("HEAD"), cancellable = true)
	private void hasLevitationSimilar(RegistryEntry<StatusEffect> effect, CallbackInfoReturnable<Boolean> cir) {
		if (effect.equals(StatusEffects.LEVITATION)) {
			cir.setReturnValue(this.activeStatusEffects.containsKey(StatusEffects.LEVITATION) || this.activeStatusEffects.containsKey(SpitSplatStatusEffects.BUBBLE_TRAP));
		}
	}

	@Inject(method = "getStatusEffect", at = @At("HEAD"), cancellable = true)
	private void getLevitationSimilar(RegistryEntry<StatusEffect> effect, CallbackInfoReturnable<StatusEffectInstance> cir) {
		if (effect.equals(StatusEffects.LEVITATION)) {
			StatusEffectInstance levitation = this.activeStatusEffects.get(StatusEffects.LEVITATION);
			StatusEffectInstance bubble = this.activeStatusEffects.get(SpitSplatStatusEffects.BUBBLE_TRAP);
			if (levitation != null && bubble == null) {
				cir.setReturnValue(levitation);
			}
			else if (levitation == null && bubble != null) {
				cir.setReturnValue(bubble);
			}
			else if (levitation != null && bubble != null) {
				if (levitation.getAmplifier() >= bubble.getAmplifier()) {
					cir.setReturnValue(levitation);
				}
				else {
					cir.setReturnValue(bubble);
				}
			}
		}
	}
}

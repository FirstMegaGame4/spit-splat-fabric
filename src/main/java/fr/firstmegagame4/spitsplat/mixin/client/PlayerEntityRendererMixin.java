package fr.firstmegagame4.spitsplat.mixin.client;

import fr.firstmegagame4.spitsplat.duck.BipedEntityModelDuck;
import fr.firstmegagame4.spitsplat.init.SpitSplatItems;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin extends LivingEntityRendererMixin<PlayerEntity, PlayerEntityModel<PlayerEntity>> {

	@Inject(method = "setModelPose", at = @At("HEAD"))
	private void setSplatterPose(AbstractClientPlayerEntity player, CallbackInfo ci) {
		if (!player.isSpectator()) {
			BipedEntityModelDuck duck = (BipedEntityModelDuck) this.getModel();
			if (player.getMainArm() == Arm.RIGHT) {
				duck.spitsplat$splatterActionRight(this.getSpatterPose(player, Hand.MAIN_HAND));
				duck.spitsplat$splatterActionRight(this.getSpatterPose(player, Hand.OFF_HAND));
			}
			else {
				duck.spitsplat$splatterActionRight(this.getSpatterPose(player, Hand.OFF_HAND));
				duck.spitsplat$splatterActionRight(this.getSpatterPose(player, Hand.MAIN_HAND));
			}
		}
	}

	@Unique
	private boolean getSpatterPose(AbstractClientPlayerEntity player, Hand hand) {
		if (player.getActiveHand() == hand && player.getItemUseTimeLeft() > 0) {
			return player.getStackInHand(player.getActiveHand()).isOf(SpitSplatItems.SPLATTER);
		}
		else {
			return false;
		}
	}
}

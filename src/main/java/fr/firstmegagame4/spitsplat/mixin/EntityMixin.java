package fr.firstmegagame4.spitsplat.mixin;

import fr.firstmegagame4.spitsplat.init.SpitSplatAttachments;
import fr.firstmegagame4.spitsplat.payload.BubblePayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {

	@Inject(method = "baseTick", at = @At("HEAD"))
	private void bubbleDataBehavior(CallbackInfo ci) {
		Entity object = (Entity) (Object) this;
		if (object.getWorld() instanceof ServerWorld world) {
			if (object.hasAttached(SpitSplatAttachments.BUBBLE_STARTED_AGE)) {
				if (object.age >= object.getAttachedOrThrow(SpitSplatAttachments.BUBBLE_STARTED_AGE) + 40) {
					object.removeAttached(SpitSplatAttachments.BUBBLE_STARTED_AGE);
					world.getPlayers().forEach(player -> ServerPlayNetworking.send(player, new BubblePayload(object.getId(), 0)));
				}
			}
		}
	}
}

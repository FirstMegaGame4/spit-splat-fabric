package fr.firstmegagame4.spitsplat.init;

import fr.firstmegagame4.spitsplat.payload.BubblePayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;

public class SpitSplatNetworking {

	public static void register() {
		PayloadTypeRegistry.playS2C().register(BubblePayload.ID, BubblePayload.CODEC);
		ClientPlayNetworking.registerGlobalReceiver(BubblePayload.ID, (payload, context) -> {
			context.client().execute(() -> {
				ClientWorld world = context.client().world;
				if (world != null) {
					Entity entity = world.getEntityById(payload.entityId());
					if (entity != null) {
						if (payload.age() != 0) {
							entity.setAttached(SpitSplatAttachments.BUBBLE_STARTED_AGE, payload.age());
						}
						else {
							entity.removeAttached(SpitSplatAttachments.BUBBLE_STARTED_AGE);
						}
					}
				}
			});
		});
	}
}

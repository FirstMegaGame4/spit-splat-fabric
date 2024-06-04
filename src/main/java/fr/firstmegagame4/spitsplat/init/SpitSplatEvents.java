package fr.firstmegagame4.spitsplat.init;

import fr.firstmegagame4.spitsplat.item.SplatterItem;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.world.ClientWorld;

public class SpitSplatEvents {

	public static void register() {
		ClientTickEvents.START_WORLD_TICK.register(SpitSplatEvents::startWorldTick);
	}

	private static void startWorldTick(ClientWorld clientWorld) {
		clientWorld.getPlayers().forEach(player -> {
			if (player.getMainHandStack().isOf(SpitSplatItems.SPLATTER)) {
				SplatterItem.Animation animation = player.getMainHandStack().getOrDefault(SpitSplatDataComponents.SPLATTER_ANIMATION, SplatterItem.Animation.NONE);
				if (animation != SplatterItem.Animation.NONE) {
					player.getMainHandStack().set(SpitSplatDataComponents.SPLATTER_ANIMATION, SplatterItem.Animation.NONE);
					((SplatterItem) player.getMainHandStack().getItem()).animation = animation;
				}
			}
		});
	}
}

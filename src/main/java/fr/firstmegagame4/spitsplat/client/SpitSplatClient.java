package fr.firstmegagame4.spitsplat.client;

import fr.firstmegagame4.spitsplat.client.init.SpitSplatEntityRenderers;
import net.fabricmc.api.ClientModInitializer;

public class SpitSplatClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		SpitSplatEntityRenderers.register();
	}
}

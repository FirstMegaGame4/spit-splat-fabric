package fr.firstmegagame4.spitsplat;

import fr.firstmegagame4.spitsplat.init.SpitSplatEntities;
import fr.firstmegagame4.spitsplat.init.SpitSplatItems;
import fr.firstmegagame4.spitsplat.init.SpitSplatStatusEffects;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpitSplat implements ModInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger("spit_splat");

	@Override
	public void onInitialize() {
		LOGGER.info("Splat is saying hello!");

		SpitSplatItems.register();
		SpitSplatEntities.register();
		SpitSplatStatusEffects.register();
	}

	public static String id() {
		return "spit_splat";
	}

	public static Identifier createId(String path) {
		return new Identifier(SpitSplat.id(), path);
	}

	public static Logger getLogger() {
		return SpitSplat.LOGGER;
	}
}

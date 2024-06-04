package fr.firstmegagame4.spitsplat.init;

import fr.firstmegagame4.spitsplat.SpitSplat;
import fr.firstmegagame4.spitsplat.item.SplatterItem;
import net.minecraft.component.DataComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class SpitSplatDataComponents {

	public static final DataComponentType<SplatterItem.Animation> SPLATTER_ANIMATION = DataComponentType.<SplatterItem.Animation>builder()
		.codec(SplatterItem.Animation.CODEC)
		.cache()
		.build();

	public static void register() {
		Registry.register(Registries.DATA_COMPONENT_TYPE, SpitSplat.createId("splatter_animation"), SpitSplatDataComponents.SPLATTER_ANIMATION);
	}
}

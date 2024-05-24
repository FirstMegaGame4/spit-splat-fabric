package fr.firstmegagame4.spitsplat.init;

import fr.firstmegagame4.spitsplat.SpitSplat;
import fr.firstmegagame4.spitsplat.item.RawSpitSplatItem;
import fr.firstmegagame4.spitsplat.item.SplatterItem;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Rarity;

public class SpitSplatItems {

	public static final Item BUBBLE = new Item(new Item.Settings().maxCount(1));

	public static final Item RAW_SPIT_SPLAT = new RawSpitSplatItem(new Item.Settings().food(
		new FoodComponent.Builder()
			.nutrition(2)
			.saturationModifier(0.1f)
			.alwaysEdible()
			.build()
	));

	public static final Item SPLAT_BONES = new Item(new Item.Settings());

	public static final Item SPLAT_SCALE = new Item(new Item.Settings());

	public static final Item SPLATTER = new SplatterItem(new Item.Settings().maxCount(1).rarity(Rarity.RARE));

	public static void register() {
		Registry.register(Registries.ITEM, SpitSplat.createId("bubble"), SpitSplatItems.BUBBLE);
		Registry.register(Registries.ITEM, SpitSplat.createId("raw_spit_splat"), SpitSplatItems.RAW_SPIT_SPLAT);
		Registry.register(Registries.ITEM, SpitSplat.createId("splat_bones"), SpitSplatItems.SPLAT_BONES);
		Registry.register(Registries.ITEM, SpitSplat.createId("splat_scale"), SpitSplatItems.SPLAT_SCALE);
		Registry.register(Registries.ITEM, SpitSplat.createId("splatter"), SpitSplatItems.SPLATTER);
	}
}

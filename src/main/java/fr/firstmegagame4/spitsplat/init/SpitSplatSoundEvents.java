package fr.firstmegagame4.spitsplat.init;

import fr.firstmegagame4.spitsplat.SpitSplat;
import net.minecraft.sound.SoundEvent;

public class SpitSplatSoundEvents {

	public static final SoundEvent BUBBLE_TOP = SoundEvent.of(SpitSplat.createId("bubble_pop"));
	public static final SoundEvent SPLATTER_SHOT = SoundEvent.of(SpitSplat.createId("splatter_shoot"));
	public static final SoundEvent WATER_SHOCKWAVE = SoundEvent.of(SpitSplat.createId("water_shockwave"));
}

package fr.firstmegagame4.spitsplat.init;

import com.mojang.serialization.Codec;
import fr.firstmegagame4.spitsplat.SpitSplat;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;

public class SpitSplatAttachments {

	public static final AttachmentType<Integer> BUBBLE_STARTED_AGE = AttachmentRegistry.<Integer>builder()
		.initializer(() -> 0)
		.persistent(Codec.INT)
		.buildAndRegister(SpitSplat.createId("bubble_started_age"));

	public static void register() {}
}

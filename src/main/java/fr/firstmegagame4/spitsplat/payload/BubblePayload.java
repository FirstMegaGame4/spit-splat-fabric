package fr.firstmegagame4.spitsplat.payload;

import fr.firstmegagame4.spitsplat.SpitSplat;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record BubblePayload(int entityId, int age) implements CustomPayload {

	public static final Id<BubblePayload> ID = new Id<>(SpitSplat.createId("bubble"));

	public static final PacketCodec<PacketByteBuf, BubblePayload> CODEC = PacketCodec.of(
		(value, buf) -> {
			buf.writeInt(value.entityId());
			buf.writeInt(value.age());
		},
		buf -> new BubblePayload(buf.readInt(), buf.readInt())
	);

	@Override
	public Id<? extends CustomPayload> getId() {
		return BubblePayload.ID;
	}
}

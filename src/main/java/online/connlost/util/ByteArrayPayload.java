package online.connlost.util;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ByteArrayPayload(byte[] data) implements CustomPayload {
    public static final CustomPayload.Id<ByteArrayPayload> ID = new CustomPayload.Id<>(Identifier.of("allstackable", "config"));
    public static final PacketCodec<RegistryByteBuf, ByteArrayPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.BYTE_ARRAY, ByteArrayPayload::data,
            ByteArrayPayload::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}

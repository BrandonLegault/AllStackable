package online.connlost;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.SerializationUtils;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;

public class AllstackableClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
                ClientPlayConnectionEvents.INIT.register((handler, client) ->{
                ClientPlayNetworking.registerReceiver(
                        AllStackable.SHARE_CONFIG_PACKET_ID,
                        (client1, handler1, buf, sender1) -> configHandler(handler1, sender1, client1, buf)
                );
                });
	}

	private void configHandler(ClientPlayNetworkHandler handler, PacketSender sender, MinecraftClient client, PacketByteBuf buf){
        ArrayList<LinkedHashMap<String, Integer>> configList = SerializationUtils.deserialize(buf.readByteArray());
        ConfigSync.syncConfig(configList);
    }
}
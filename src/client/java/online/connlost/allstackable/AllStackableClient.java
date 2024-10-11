package online.connlost.allstackable;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import online.connlost.allstackable.util.ByteArrayPayload;
import org.apache.commons.lang3.SerializationUtils;


public class AllStackableClient implements ClientModInitializer {
    @Override
    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {
        ClientPlayConnectionEvents.INIT.register((handler, client) -> {
            ClientPlayNetworking.registerReceiver(
                    ByteArrayPayload.ID,
                    (packet, context) -> {
                        configHandler(packet.data());
                    }
            );
        });
    }

    // Updated configHandler method to match the new PlayPayloadHandler API
    private void configHandler(byte[] configPayload) {
        ArrayList<LinkedHashMap<String, Integer>> configList = SerializationUtils.deserialize(configPayload);
        ConfigSync.syncConfig(configList);
    }
}

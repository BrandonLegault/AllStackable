package online.connlost.util;

import online.connlost.AllStackable;
import online.connlost.server.Server;
import online.connlost.server.config.ConfigManager;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;

import static online.connlost.AllStackable.LOGGER;

public class NetworkHelper {

    public static void sentConfigToAll(){
        if (Server.minecraft_server != null){
            List<ServerPlayerEntity> players = Server.minecraft_server.getPlayerManager().getPlayerList();
            for (ServerPlayerEntity player:players){
                sentConfigToPlayer(player, ConfigManager.getConfigManager().getSerializedConfig());
            }
        } else {
            LOGGER.warn("[All Stackable] Server hasn't been loaded.");
        }
    }

    public static void sentConfigToPlayer(ServerPlayerEntity player, byte[] data) {
        ByteArrayPayload payload = new ByteArrayPayload(data);
        ServerPlayNetworking.send(player, payload);
    }
}

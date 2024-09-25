package online.connlost.server;

import online.connlost.server.config.ConfigManager;
import online.connlost.util.NetworkHelper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.WorldSavePath;
import online.connlost.AllStackable;


public class Server {
    public static MinecraftServer minecraft_server;
    public static ConfigManager config_manager = ConfigManager.getConfigManager();

    public static void onServerLoaded(MinecraftServer ms){
        minecraft_server = ms;
        config_manager.passConfigFile(minecraft_server.getSavePath(WorldSavePath.ROOT).resolve("allstackable-config.json").toFile());
        config_manager.setupConfig();
        AllStackable.LOGGER.info("[All Stackable] Loaded!");
    }

    public static void onPlayerJoin(ServerPlayerEntity player){
        NetworkHelper.sentConfigToPlayer(player);
    }

}

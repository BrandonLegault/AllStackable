package online.connlost;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import online.connlost.server.Server;
import online.connlost.server.command.StackSizeCommand;
import online.connlost.util.ByteArrayPayload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class AllStackable implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public void onInitialize() {
		PayloadTypeRegistry.playS2C().register(ByteArrayPayload.ID, ByteArrayPayload.CODEC);
		LOGGER.info("[All Stackable] Start loading!");
		StackSizeCommand.register();
		LOGGER.info("[All Stackable] Command registered.");
		ServerLifecycleEvents.SERVER_STARTED.register(Server::onServerLoaded);
	}
}

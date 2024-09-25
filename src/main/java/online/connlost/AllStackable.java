package online.connlost;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.util.Identifier;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

import online.connlost.server.Server;
import online.connlost.server.command.StackSizeCommand;

public class AllStackable implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("allstackable");

	public static final Identifier SHARE_CONFIG_PACKET_ID = Identifier.of("allstackable", "config");

	@Override
	public void onInitialize() {
		LOGGER.info("[All Stackable] Start loading!");
		StackSizeCommand.register();
		LOGGER.info("[All Stackable] Command registered.");
		ServerLifecycleEvents.SERVER_STARTED.register(Server::onServerLoaded);
	}
}
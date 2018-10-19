package io.github.techdweebgaming.ctunigui.networking;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
	
	private static int packetID = 0;
	
	public static SimpleNetworkWrapper instance = null;
	
	public PacketHandler() {
		
	}
	
	public static int nextID() {
		return packetID++;
	}
	
	public static void registerMessages(String channelName) {
		instance = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
		registerMessages();
	}
	
	private static void registerMessages() {
		instance.registerMessage(PacketRequestCacheUpdate.Handler.class, PacketRequestCacheUpdate.class, nextID(), Side.SERVER);
		instance.registerMessage(PacketRefreshClientCache.Handler.class, PacketRefreshClientCache.class, nextID(), Side.CLIENT);
	}

}

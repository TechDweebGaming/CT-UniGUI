package io.github.techdweebgaming.ctunigui.networking;

import io.github.techdweebgaming.ctunigui.CTUniGUI;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketRequestCacheUpdate implements IMessage {
	
	public PacketRequestCacheUpdate() {
		
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		
	}
	
	public static class Handler implements IMessageHandler<PacketRequestCacheUpdate, IMessage> {
		
		@Override
		public IMessage onMessage(PacketRequestCacheUpdate packet, MessageContext ctx) {
			CTUniGUI.logger.info("Request for cache refresh recieved");
			return new PacketRefreshClientCache();
		}
		
	}

}

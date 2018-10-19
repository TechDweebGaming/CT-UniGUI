package io.github.techdweebgaming.ctunigui.networking;

import java.util.ArrayList;
import java.util.List;

import io.github.techdweebgaming.ctunigui.CTUniGUI;
import io.github.techdweebgaming.ctunigui.zencomponents.AutoZSFile;
import io.github.techdweebgaming.ctunigui.zencomponents.ZenComponentRegistry;
import io.github.techdweebgaming.ctunigui.zencomponents.ZenComponentRegistry.ZenComponentEnum;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketRefreshClientCache implements IMessage {
	
	private List<AutoZSFile> zsFiles;
	
	public PacketRefreshClientCache() {
		zsFiles = CTUniGUI.instance.getZSFileCache();
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		zsFiles = new ArrayList<AutoZSFile>();
		int numTags = buf.readInt();
		for (int i = 0; i < numTags; i++) {
			zsFiles.add((AutoZSFile) ZenComponentRegistry.readFromNBT(ZenComponentEnum.FILE.getComponentClass(), ByteBufUtils.readTag(buf)));
		}		
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(zsFiles.size());
		for (AutoZSFile file : zsFiles) {
			ByteBufUtils.writeTag(buf, file.writeToNBT());
		}
	}
	
	public static class Handler implements IMessageHandler<PacketRefreshClientCache, IMessage> {
		
		@Override
		public IMessage onMessage(PacketRefreshClientCache packet, MessageContext ctx) {
			CTUniGUI.instance.setZSFileCache(packet.zsFiles);
			CTUniGUI.logger.info("Client file cache refreshed! Cache contains " + CTUniGUI.instance.getZSFileCache().size() + " files!");
			return null;
		}
		
	}
}

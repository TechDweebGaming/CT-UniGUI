package io.github.techdweebgaming.ctunigui.proxy;

import io.github.techdweebgaming.ctunigui.CTUniGUI;
import io.github.techdweebgaming.ctunigui.networking.PacketHandler;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@EventBusSubscriber
public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e) {
		CTUniGUI.logger.info("Registering CT-UniGUI packet channel.");
		PacketHandler.registerMessages("ctunigui");
	}
	
	public void init(FMLInitializationEvent e) {
		
	}
	
	public void postInit(FMLPostInitializationEvent e) {
			
	}
	
}

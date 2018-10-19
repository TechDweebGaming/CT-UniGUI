package io.github.techdweebgaming.ctunigui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.apache.logging.log4j.Logger;

import io.github.techdweebgaming.ctunigui.commands.CTUGReloadCommand;
import io.github.techdweebgaming.ctunigui.commands.CTUniGUICommand;
import io.github.techdweebgaming.ctunigui.proxy.CommonProxy;
import io.github.techdweebgaming.ctunigui.zencomponents.AutoZSFile;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(useMetadata = true, modid = CTUniGUI.modID)
public class CTUniGUI {
	
	public static final String modID = "ctunigui";
	
	private List<AutoZSFile> zsFiles = new ArrayList<AutoZSFile>();
	
	@SidedProxy(clientSide = "io.github.techdweebgaming.ctunigui.proxy.ClientProxy", serverSide = "io.github.techdweebgaming.ctunigui.proxy.ServerProxy")
	public static CommonProxy proxy;
	
	@Instance
	public static CTUniGUI instance;
	
	public static Logger logger;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		logger = e.getModLog();
		proxy.preInit(e);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent e) {
		e.registerServerCommand(new CTUniGUICommand());
		e.registerServerCommand(new CTUGReloadCommand());
		File directory = new File("scripts");
		if(!directory.exists()) directory.mkdirs();
		loadZSFilesFromDirectory(null);
	}
	
	public void loadZSFilesFromDirectory(@Nullable File dirIn) {
		zsFiles.clear();
		File directory;
		if (dirIn != null) directory = dirIn;
		else directory = new File("scripts");
		if(!directory.exists()) directory.mkdirs();
		if (dirIn != null) directory = dirIn;
		for (File file : directory.listFiles()) {
			if (file.isDirectory()) loadZSFilesFromDirectory(file);
			else {
				try {
					if (file.getName().endsWith(".auto.zs")) zsFiles.add(new AutoZSFile(file));	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void setZSFileCache(List<AutoZSFile> files) {
		zsFiles = files;
	}
	
	public List<AutoZSFile> getZSFileCache() {
		return zsFiles;
	}

}

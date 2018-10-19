package io.github.techdweebgaming.ctunigui.commands;

import java.io.File;
import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.Lists;

import io.github.techdweebgaming.ctunigui.CTUniGUI;
import io.github.techdweebgaming.ctunigui.networking.PacketHandler;
import io.github.techdweebgaming.ctunigui.networking.PacketRefreshClientCache;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class CTUGReloadCommand extends CommandBase {
	
	@Override
	@Nonnull
	public String getName() {
		return "ctugreload";
	}
	
	@Override
	@Nonnull
	public String getUsage(@Nonnull ICommandSender sender) {
		return "ctugreload";
	}
	
	@Override
	@Nonnull
	public List<String> getAliases() {
		return Lists.newArrayList(CTUniGUI.modID, "ctugreload", "CTUGReload");
	}
	
	@Override
	public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) throws CommandException {
		CTUniGUI.instance.loadZSFilesFromDirectory(new File("scripts"));
		PacketHandler.instance.sendToAll(new PacketRefreshClientCache());
		CTUniGUI.logger.info("Reloaded file cache from filesystem");
	}
	
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		if (sender.canUseCommand(4, "")) return true;
		return false;
	}


}

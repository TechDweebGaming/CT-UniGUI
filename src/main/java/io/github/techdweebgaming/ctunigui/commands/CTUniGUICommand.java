package io.github.techdweebgaming.ctunigui.commands;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.Lists;

import io.github.techdweebgaming.ctunigui.CTUniGUI;
import io.github.techdweebgaming.ctunigui.networking.PacketHandler;
import io.github.techdweebgaming.ctunigui.networking.PacketRefreshClientCache;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CTUniGUICommand extends CommandBase {
	
	@Override
	@Nonnull
	public String getName() {
		return "ctunigui";
	}
	
	@Override
	@Nonnull
	public String getUsage(@Nonnull ICommandSender sender) {
		return "ctunigui";
	}
	
	@Override
	@Nonnull
	public List<String> getAliases() {
		return Lists.newArrayList(CTUniGUI.modID, "ctunigui", "ctUniGui", "ctUniGUI");
	}
	
	@Override
	public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) throws CommandException {
		if (sender instanceof EntityPlayer) {
			PacketHandler.instance.sendTo(new PacketRefreshClientCache(), (EntityPlayerMP) sender);
			// Open GUI
		} else sender.sendMessage(new TextComponentString("Silly console, GUIs are for clients!"));
	}
	
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		if (sender.canUseCommand(4, "")) return true;
		return false;
	}

}

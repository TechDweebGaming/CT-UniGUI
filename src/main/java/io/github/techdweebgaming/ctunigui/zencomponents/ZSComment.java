package io.github.techdweebgaming.ctunigui.zencomponents;

import java.io.BufferedWriter;
import java.io.IOException;

import io.github.techdweebgaming.ctunigui.zencomponents.ZenComponentRegistry.ZenComponentEnum;
import net.minecraft.nbt.NBTTagCompound;

public class ZSComment implements IZSElement {
	
	private String commentText;
	
	public ZSComment() {
		
	}
	
	public ZSComment(String commentText) {
		this.commentText = commentText;
	}
	
	public static ZSComment readFromFile(String line) {
		return new ZSComment(line.replace(ZenComponentEnum.COMMENT.getLineRoot(), ""));
	}
	
	public void writeToFile(BufferedWriter writer) throws IOException {
		writer.write(ZenComponentEnum.COMMENT.getLineRoot() + commentText);
		writer.newLine();
	}
	
	public NBTTagCompound writeToNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setString("key", ZenComponentEnum.COMMENT.getKey());
		nbt.setString("commentText", commentText);
		return nbt;
	}
	
	public static ZSComment readFromNBT(NBTTagCompound nbt) {
		return new ZSComment(nbt.getString("commentText"));
	}
}

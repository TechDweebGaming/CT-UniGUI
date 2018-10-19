package io.github.techdweebgaming.ctunigui.zencomponents;

import java.io.BufferedWriter;
import java.io.IOException;

import net.minecraft.nbt.NBTTagCompound;

public interface IZSElement {
	
	public NBTTagCompound writeToNBT();
	
	public void writeToFile(BufferedWriter writer) throws IOException;
}

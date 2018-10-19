package io.github.techdweebgaming.ctunigui.zencomponents;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

import io.github.techdweebgaming.ctunigui.zencomponents.ZenComponentRegistry.ZenComponentEnum;
import net.minecraft.nbt.NBTTagCompound;

public class ZSSection implements IZSElement {
	
	private String sectionName;
	private List<IZSElement> elements = new ArrayList<IZSElement>();
	
	public ZSSection(String sectionName) {
		this.sectionName = sectionName;
	}
	
	public ZSSection(List<String> lines) {
		boolean parsedHeader = false;
		for (String line : lines) {
			if(parsedHeader) {
				elements.add(ZenComponentRegistry.readFromFile(ZenComponentRegistry.getZenComponentEnumFromLine(line).getComponentClass(), line));
			} else {
				sectionName = line.replace(ZenComponentEnum.SECTION.getLineRoot(), "");
				parsedHeader = true;
			}
		}
	}
	
	public void writeToFile(BufferedWriter writer) {
		
	}
	
	public NBTTagCompound writeToNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		int i = 0;
		nbt.setString("key", ZenComponentEnum.SECTION.getKey());
		nbt.setString("sectionName", sectionName);
		for (IZSElement element : elements) {
			nbt.setTag(Integer.toString(i), element.writeToNBT());
			i++;
		}
		nbt.setInteger("count", i);
		return nbt;
	}
	
	public static ZSSection readFromNBT(NBTTagCompound nbt) {
		ZSSection section = new ZSSection(nbt.getString("sectionName"));
		for (int i = 0; i < nbt.getInteger("count"); i++) {
			NBTTagCompound tag = (NBTTagCompound) nbt.getTag(Integer.toString(i));
			section.elements.add(ZenComponentRegistry.readFromNBT(ZenComponentRegistry.getZenComponentEnumFromKey(tag.getString("key")).getComponentClass(), (NBTTagCompound) tag));
		}
		return section;
	}

}

package io.github.techdweebgaming.ctunigui.zencomponents;

import java.lang.reflect.Method;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;

public class ZenComponentRegistry {
	
	public enum ZenComponentEnum {
		FILE("file", AutoZSFile.class, null),
		SECTION("section", ZSSection.class, "# SECTION: "),
		COMMENT("comment", ZSComment.class, "#: ");
		
		private String key;
		private Class<?> zenComponentClass;
		private String lineRoot;
		
		public String getKey() {
			return key;
		}
		
		@SuppressWarnings("unchecked")
		public <T extends IZSElement> Class<T> getComponentClass() {
			return (Class<T>) zenComponentClass;
		}
		
		public String getLineRoot() {
			return lineRoot;
		}
		
		ZenComponentEnum(String key, Class<?> zenComponentClass, String lineRoot) {
			this.key = key;
			this.zenComponentClass = zenComponentClass;
			this.lineRoot = lineRoot;
		}
	}

	@Nullable
	public static ZenComponentEnum getZenComponentEnumFromKey(String key) {
		for (ZenComponentEnum _enum : ZenComponentEnum.values()) {
			if(_enum.getKey().equals(key)) return _enum;
		}
		return null;
	}
	
	@Nullable
	public static ZenComponentEnum getZenComponentEnumFromLine(String line) {
		for (ZenComponentEnum _enum : ZenComponentEnum.values()) {
			if(line.startsWith(_enum.getLineRoot())) return _enum;
		}
		return null;
	}
	
	public static <T extends IZSElement> T readFromNBT(Class<T> compClass, NBTTagCompound nbt) {
		try {
			Method method = compClass.getMethod("readFromNBT", NBTTagCompound.class);
			return compClass.cast(method.invoke(null, nbt));	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T extends IZSElement> T readFromFile(Class<T> compClass, String line) {
		try {
			if(compClass.equals(AutoZSFile.class) || compClass.equals(ZSSection.class)) return null;
			else {
				Method method = compClass.getMethod("readFromFile", String.class);
				return compClass.cast(method.invoke(null, line));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

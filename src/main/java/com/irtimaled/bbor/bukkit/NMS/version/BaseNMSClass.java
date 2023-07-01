package com.irtimaled.bbor.bukkit.NMS.version;

import com.irtimaled.bbor.bukkit.NMS.api.INMSClass;
import com.irtimaled.bbor.bukkit.NMS.api.NMSClassName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseNMSClass implements INMSClass {

    public BaseNMSClass(String version) throws ClassNotFoundException {
        addCraftClass(NMSClassName.CraftChunk, "org.bukkit.craftbukkit.!version!.CraftChunk", version);
        addCraftClass(NMSClassName.CraftWorld, "org.bukkit.craftbukkit.!version!.CraftWorld", version);
        addCraftClass(NMSClassName.CraftPlayer, "org.bukkit.craftbukkit.!version!.entity.CraftPlayer", version);
        addCraftClass(NMSClassName.CraftChunk, "org.bukkit.craftbukkit.!version!.CraftChunk", version);
        addCraftClass(NMSClassName.CraftServer, "org.bukkit.craftbukkit.!version!.CraftServer", version);
    }

    protected final Map<NMSClassName, Class<?>> nmsClassCache = new HashMap<>();

    protected void addClassCache(NMSClassName className, String classPath) throws ClassNotFoundException {
        nmsClassCache.put(className, Class.forName(classPath));
    }

    protected void addCraftClass(NMSClassName className, @NotNull String classPath, String version) throws ClassNotFoundException {
        nmsClassCache.put(className, Class.forName(classPath.replaceAll("!version!", version)));
    }

    @NotNull
    @Override
    public Class<?> getNMSClass(@NotNull NMSClassName name) {
        return nmsClassCache.get(name);
    }

    @Nullable
    @Override
    public Object cast(@NotNull NMSClassName name, @Nullable Object object) {
        return getNMSClass(name).cast(object);
    }
}

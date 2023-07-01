package com.irtimaled.bbor.bukkit.NMS;

import com.irtimaled.bbor.Logger;
import com.irtimaled.bbor.bukkit.NMS.api.*;
import io.netty.buffer.ByteBuf;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.util.*;

public class NMSHelper {

    public static final int lowestSupportVersion = 11800;
    public static final int lowestUnSupportVersion = 12000;

    private static INMSClass nmsClassCache;
    private static INMSMethod nmsMethodCache;

    private static boolean isInit = false;

    public static boolean init(@NotNull JavaPlugin plugin) {
        if (!isInit) {
            int version = getVersion();
            while (nmsClassCache == null) {
                String packVersion = getPackVersion(version);
                Class<?> nmsClassCacheClass = null;

                try {
                    nmsClassCacheClass = Class.forName("com.irtimaled.bbor.bukkit.NMS.version." + packVersion + "_NMSClass");
                } catch (ReflectiveOperationException ignored) {
                }

                if (nmsClassCache == null && nmsClassCacheClass != null) {
                    try {
                        nmsClassCache = (INMSClass) nmsClassCacheClass.getDeclaredConstructor().newInstance();
                        Logger.info("NMSHelper init class version " + packVersion);
                    } catch (ReflectiveOperationException ignored) {
                    }
                }

                if (--version < lowestSupportVersion) {
                    Logger.error("NMSHelper init cache fail");
                    return false;
                }
            }

            version = getVersion();
            while (nmsMethodCache == null) {
                String packVersion = getPackVersion(version);
                Class<?> nmsMethodCacheClass = null;

                try {
                    nmsMethodCacheClass = Class.forName("com.irtimaled.bbor.bukkit.NMS.version." + packVersion + "_NMSMethod");
                } catch (ReflectiveOperationException ignored) {
                }

                if (nmsMethodCache == null && nmsMethodCacheClass != null) {
                    try {
                        nmsMethodCache = (INMSMethod) nmsMethodCacheClass.getDeclaredConstructor().newInstance();
                        Logger.info("NMSHelper init method version " + packVersion);
                    } catch (ReflectiveOperationException ignored) {
                        ignored.printStackTrace();
                    }
                }

                if (--version < lowestSupportVersion) {
                    Logger.error("NMSHelper init cache fail");
                    return false;
                }
            }

            isInit = true;
            Logger.info("NMSHelper init over");
            return true;
        } else {
            return false;
        }
    }

    public static int getVersion() {
        String[] version = Bukkit.getBukkitVersion().split("-")[0].split("\\.");
        return Integer.parseInt(version[0]) * 10000 + Integer.parseInt(version[1]) * 100 + ((version.length > 2) ? Integer.parseInt(version[2]) : 0);
    }

    public static int toVersion(String packVersion) {
        packVersion = packVersion.substring(1).replaceAll("R", "");
        String[] version = packVersion.split("_");
        return Integer.parseInt(version[0]) * 10000 + Integer.parseInt(version[1]) * 100 + ((version.length > 2) ? Integer.parseInt(version[2]) : 0);
    }

    private final static Map<String, String> packVersionMap = Map.of("v1_19_R2", "v1_19_R1", "v1_19_R3", "v1_19_R2", "v1_19_R4", "v1_19_R3");

    @NotNull
    public static String getPackVersion(int version) {
        int mainVersion = version / 10000;
        int minorVersion = (version - mainVersion * 10000) / 100;
        int revisionVersion = (version - mainVersion * 10000 - minorVersion * 100);

        String packVersion = "v" + mainVersion + "_" + minorVersion + "_R" + ((revisionVersion == 0) ? "1" : revisionVersion);
        return packVersionMap.getOrDefault(packVersion, packVersion);
    }

    public static INMSClass getNmsClassCache() {
        return nmsClassCache;
    }

    public static INMSMethod getNmsMethodCache() {
        return nmsMethodCache;
    }

    @NotNull
    public static Class<?> getNMSClass(@NotNull NMSClassName name) {
        return nmsClassCache.getNMSClass(name);
    }

    @Nullable
    @Contract("_, null -> null")
    public static Object cast(@NotNull NMSClassName name, @Nullable Object object) {
        return nmsClassCache.cast(name, object);
    }

    public static String getPackVersion() {
        return getPackVersion(getVersion());
    }

    public static Object getNMSChunk(@NotNull Chunk chunk) {
        return nmsMethodCache.craftChunkGetChunk(chunk);
    }

    public static Object getNMSWorld(@NotNull World world) {
        return nmsMethodCache.craftWorldGetWorld(world);
    }

    public static Object getNMSPlayer(@NotNull Player player) {
        return nmsMethodCache.craftPlayerGetPlayer(player);
    }

    public static Object getNMSServer(@NotNull Server server) {
        return nmsMethodCache.craftServerGetServer(server);
    }

    public static Object chunkGetWorld(Object chunk) {
        return nmsMethodCache.chunkGetWorld(chunk);
    }

    public static Map<?, ?> chunkGetStructureMap(Object chunk) {
        return nmsMethodCache.chunkGetStructureMap(chunk);
    }

    public static Object worldGetStructureFeatureRegistry(Object world) {
        return nmsMethodCache.worldGetStructureFeatureRegistry(world);
    }

    public static Object worldGetResourceKey(Object world) {
        return nmsMethodCache.worldGetResourceKey(world);
    }

    public static Object worldGetWorldData(Object world) {
        return nmsMethodCache.worldGetWorldData(world);
    }

    public static Object worldGetOverloadWorldKey() {
        return nmsMethodCache.worldGetOverloadWorldKey();
    }

    public static long worldGetSeed(Object world) {
        return nmsMethodCache.worldGetSeed(world);
    }

    public static int worldDataGetSpawnX(Object worldData) {
        return nmsMethodCache.worldDataGetSpawnX(worldData);
    }

    public static int worldDataGetSpawnZ(Object worldData) {
        return nmsMethodCache.worldDataGetSpawnZ(worldData);
    }

    public static Optional<?> registryGetOptionalResourceKey(Object registry, Object structure) {
        return nmsMethodCache.registryGetOptionalResourceKey(registry, structure);
    }

    public static Set<Map.Entry<?, ?>> registryGetAllResourceKeySet(Object registry) {
        return nmsMethodCache.registryGetAllResourceKeySet(registry);
    }

    public static Object resourceKeyGetValue(Object resourceKey) {
        return nmsMethodCache.resourceKeyGetValue(resourceKey);
    }

    public static int playerGetEntityID(Object player) {
        return nmsMethodCache.playerGetEntityID(player);
    }

    public static Object playerGetWorld(Object player) {
        return nmsMethodCache.playerGetWorld(player);
    }

    public static NMSMethodConsumer playerGetPacketConsumer(Object player) {
        return nmsMethodCache.playerGetPacketConsumer(player);
    }

    public static NMSClassFunction packetPlayOutCustomPayloadNewFunction() {
        return nmsMethodCache.packetPlayOutCustomPayloadNewFunction();
    }

    public static Object minecraftKeyNew(String name) {
        return nmsMethodCache.minecraftKeyNew(name);
    }

    public static Object packetDataSerializerNew(ByteBuf bytebuf) {
        return nmsMethodCache.packetDataSerializerNew(bytebuf);
    }

    public static void packetDataSerializerWriteLong(Object packetDataSerializer, long value) {
        nmsMethodCache.packetDataSerializerWriteLong(packetDataSerializer, value);
    }

    public static void packetDataSerializerWriteInt(Object packetDataSerializer, int value) {
        nmsMethodCache.packetDataSerializerWriteInt(packetDataSerializer, value);
    }

    public static void packetDataSerializerWriteVarInt(Object packetDataSerializer, int value) {
        nmsMethodCache.packetDataSerializerWriteVarInt(packetDataSerializer, value);
    }

    public static void packetDataSerializerWriteChar(Object packetDataSerializer, char value) {
        nmsMethodCache.packetDataSerializerWriteChar(packetDataSerializer, value);
    }

    public static void packetDataSerializerWriteMinecraftKey(Object packetDataSerializer, Object value) {
        nmsMethodCache.packetDataSerializerWriteMinecraftKey(packetDataSerializer, value);
    }

    public static void packetDataSerializerWriteString(Object packetDataSerializer, String value) {
        nmsMethodCache.packetDataSerializerWriteString(packetDataSerializer, value);
    }

    public static Object structureStartGetBox(Object structureStart) {
        return nmsMethodCache.structureStartGetBox(structureStart);
    }

    public static List<?> structureStartGetPiece(Object structureStart) {
        return nmsMethodCache.structureStartGetPiece(structureStart);
    }

    public static Object structurePieceGetBox(Object structurePiece) {
        return nmsMethodCache.structurePieceGetBox(structurePiece);
    }

    public static int structureBoundingBoxGetMinX(Object structureBoundingBox) {
        return nmsMethodCache.structureBoundingBoxGetMinX(structureBoundingBox);
    }

    public static int structureBoundingBoxGetMinY(Object structureBoundingBox) {
        return nmsMethodCache.structureBoundingBoxGetMinY(structureBoundingBox);
    }

    public static int structureBoundingBoxGetMinZ(Object structureBoundingBox) {
        return nmsMethodCache.structureBoundingBoxGetMinZ(structureBoundingBox);
    }

    public static int structureBoundingBoxGetMaxX(Object structureBoundingBox) {
        return nmsMethodCache.structureBoundingBoxGetMaxX(structureBoundingBox);
    }

    public static int structureBoundingBoxGetMaxY(Object structureBoundingBox) {
        return nmsMethodCache.structureBoundingBoxGetMaxY(structureBoundingBox);
    }

    public static int structureBoundingBoxGetMaxZ(Object structureBoundingBox) {
        return nmsMethodCache.structureBoundingBoxGetMaxZ(structureBoundingBox);
    }

    public static Object serverGetStructureFeatureRegistry(Object server) {
        return nmsMethodCache.serverGetStructureFeatureRegistry(server);
    }
}

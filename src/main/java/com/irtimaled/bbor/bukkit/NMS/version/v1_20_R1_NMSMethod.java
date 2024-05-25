package com.irtimaled.bbor.bukkit.NMS.version;

import com.irtimaled.bbor.bukkit.NMS.NMSHelper;
import com.irtimaled.bbor.bukkit.NMS.api.NMSClassName;
import com.irtimaled.bbor.bukkit.NMS.api.NMSFieldDescribe;
import com.irtimaled.bbor.bukkit.NMS.api.NMSFunctionDescribe;
import com.irtimaled.bbor.bukkit.NMS.api.NMSMethodDescribe;
import io.netty.buffer.ByteBuf;

public class v1_20_R1_NMSMethod extends BaseNMSMethod {

    public v1_20_R1_NMSMethod() throws ReflectiveOperationException {
        addMethodCache("chunkGetWorld", NMSMethodDescribe.of(NMSClassName.Chunk, "F"));
        addMethodCache("chunkGetStructureMap", NMSMethodDescribe.of(NMSClassName.Chunk, "g"));
        addMethodCache("worldGetStructureFeatureRegistry1", NMSMethodDescribe.of(NMSClassName.WorldServer, "B_"));
        addMethodCache("worldGetStructureFeatureRegistry2", NMSMethodDescribe.of(NMSClassName.IRegistryCustom, "d", NMSHelper.getNMSClass(NMSClassName.ResourceKey)));
        addFieldCache("worldGetStructureFeatureRegistry3", NMSFieldDescribe.of(NMSClassName.Registries, "az"));
        addMethodCache("worldGetResourceKey", NMSMethodDescribe.of(NMSClassName.WorldServer, "ac"));
        addFieldCache("worldGetWorldData", NMSFieldDescribe.of(NMSClassName.WorldServer, "K"));
        addFieldCache("worldGetOverloadWorldKey", NMSFieldDescribe.of(NMSClassName.World, "h"));
        addMethodCache("worldGetSeed", NMSMethodDescribe.of(NMSClassName.WorldServer, "A"));
        addMethodCache("worldDataGetSpawnX", NMSMethodDescribe.of(NMSClassName.WorldData, "a"));
        addMethodCache("worldDataGetSpawnZ", NMSMethodDescribe.of(NMSClassName.WorldData, "c"));
        addMethodCache("registryGetOptionalResourceKey", NMSMethodDescribe.of(NMSClassName.IRegistry, "c", Object.class));
        addMethodCache("registryGetAllResourceKeySet", NMSMethodDescribe.of(NMSClassName.IRegistry, "g"));
        addMethodCache("resourceKeyGetValue", NMSMethodDescribe.of(NMSClassName.ResourceKey, "a"));
        addMethodCache("playerGetEntityID", NMSMethodDescribe.of(NMSClassName.EntityPlayer, "af"));
        addMethodCache("playerGetWorld", NMSMethodDescribe.of(NMSClassName.EntityPlayer, "dI"));
        addFieldCache("playerGetPacketConsumer1", NMSFieldDescribe.of(NMSClassName.EntityPlayer, "c"));
        addConsumerCache("playerGetPacketConsumer2", NMSMethodDescribe.of(NMSClassName.PlayerConnection, "a", NMSHelper.getNMSClass(NMSClassName.Packet)));
        addClassFunctionCache("packetPlayOutCustomPayloadNewFunction", NMSFunctionDescribe.of(NMSClassName.PacketPlayOutCustomPayload, NMSHelper.getNMSClass(NMSClassName.MinecraftKey), NMSHelper.getNMSClass(NMSClassName.PacketDataSerializer)));
        addClassFunctionCache("minecraftKeyNew", NMSFunctionDescribe.of(NMSClassName.MinecraftKey, String.class));
        addClassFunctionCache("packetDataSerializerNew", NMSFunctionDescribe.of(NMSClassName.PacketDataSerializer, ByteBuf.class));
        addMethodCache("packetDataSerializerWriteLong", NMSMethodDescribe.of(NMSClassName.PacketDataSerializer, "writeLong", long.class));
        addMethodCache("packetDataSerializerWriteInt", NMSMethodDescribe.of(NMSClassName.PacketDataSerializer, "writeInt", int.class));
        addMethodCache("packetDataSerializerWriteVarInt", NMSMethodDescribe.of(NMSClassName.PacketDataSerializer, "d", int.class));
        addMethodCache("packetDataSerializerWriteChar", NMSMethodDescribe.of(NMSClassName.PacketDataSerializer, "writeChar", int.class));
        addMethodCache("packetDataSerializerWriteMinecraftKey", NMSMethodDescribe.of(NMSClassName.PacketDataSerializer, "a", NMSHelper.getNMSClass(NMSClassName.MinecraftKey)));
        addMethodCache("packetDataSerializerWriteString", NMSMethodDescribe.of(NMSClassName.PacketDataSerializer, "a", String.class));
        addMethodCache("structureStartGetBox", NMSMethodDescribe.of(NMSClassName.StructureStart, "a"));
        addMethodCache("structureStartGetPiece", NMSMethodDescribe.of(NMSClassName.StructureStart, "i"));
        addMethodCache("structurePieceGetBox", NMSMethodDescribe.of(NMSClassName.StructurePiece, "f"));
        addMethodCache("structureBoundingBoxGetMinX", NMSMethodDescribe.of(NMSClassName.StructureBoundingBox, "g"));
        addMethodCache("structureBoundingBoxGetMinY", NMSMethodDescribe.of(NMSClassName.StructureBoundingBox, "h"));
        addMethodCache("structureBoundingBoxGetMinZ", NMSMethodDescribe.of(NMSClassName.StructureBoundingBox, "i"));
        addMethodCache("structureBoundingBoxGetMaxX", NMSMethodDescribe.of(NMSClassName.StructureBoundingBox, "j"));
        addMethodCache("structureBoundingBoxGetMaxY", NMSMethodDescribe.of(NMSClassName.StructureBoundingBox, "k"));
        addMethodCache("structureBoundingBoxGetMaxZ", NMSMethodDescribe.of(NMSClassName.StructureBoundingBox, "l"));
        addMethodCache("serverGetStructureFeatureRegistry1", NMSMethodDescribe.of(NMSClassName.MinecraftServer, "aV"));
        addMethodCache("serverGetStructureFeatureRegistry2", NMSMethodDescribe.of(NMSClassName.IRegistryCustom, "d", NMSHelper.getNMSClass(NMSClassName.ResourceKey)));
        addFieldCache("serverGetStructureFeatureRegistry3", NMSFieldDescribe.of(NMSClassName.Registries, "az"));
        addMethodCache("craftChunkGetChunk", NMSMethodDescribe.of(NMSClassName.CraftChunk, "getHandle", NMSHelper.getNMSClass(NMSClassName.ChunkStatus)));
        addMethodCache("craftPlayerGetPlayer", NMSMethodDescribe.of(NMSClassName.CraftPlayer, "getHandle"));
        addMethodCache("craftWorldGetWorld", NMSMethodDescribe.of(NMSClassName.CraftWorld, "getHandle"));
        addMethodCache("craftServerGetServer", NMSMethodDescribe.of(NMSClassName.CraftServer, "getServer"));
        addFieldCache("chunkStatusFull", NMSFieldDescribe.of(NMSClassName.ChunkStatus, "n"));
    }

    @Override
    public Object craftChunkGetChunk(Object chunk) {
        return invokeMethod("craftChunkGetChunk", chunk, getField("chunkStatusFull", null));
    }

    @Override
    public Object chunkGetWorld(Object chunk) {
        return invokeMethod("chunkGetWorld", chunk);
    }

    @Override
    public Object playerGetWorld(Object player) {
        return invokeMethod("playerGetWorld", player);
    }
}
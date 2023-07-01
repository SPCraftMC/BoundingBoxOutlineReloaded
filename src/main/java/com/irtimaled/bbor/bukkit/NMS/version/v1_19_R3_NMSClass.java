package com.irtimaled.bbor.bukkit.NMS.version;

import com.irtimaled.bbor.bukkit.NMS.api.NMSClassName;

public class v1_19_R3_NMSClass extends v1_19_R2_NMSClass {

    public v1_19_R3_NMSClass() throws ClassNotFoundException {
        super("v1_19_R3");
        addClassCache(NMSClassName.ChunkStatus, "net.minecraft.world.level.chunk.ChunkStatus");
    }
}

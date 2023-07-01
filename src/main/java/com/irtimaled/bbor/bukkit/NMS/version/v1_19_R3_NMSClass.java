package com.irtimaled.bbor.bukkit.NMS.version;

import com.irtimaled.bbor.bukkit.NMS.api.NMSClassName;

public class v1_19_R3_NMSClass extends v1_19_R2_NMSClass {

    public v1_19_R3_NMSClass(String version) throws ClassNotFoundException {
        super(version);
        addClassCache(NMSClassName.ChunkStatus, "net.minecraft.world.level.chunk.ChunkStatus");
    }

    public v1_19_R3_NMSClass() throws ClassNotFoundException {
        this("v1_19_R3");
    }
}

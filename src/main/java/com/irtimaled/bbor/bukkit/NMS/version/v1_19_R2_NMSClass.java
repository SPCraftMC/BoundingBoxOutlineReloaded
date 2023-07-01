package com.irtimaled.bbor.bukkit.NMS.version;

import com.irtimaled.bbor.bukkit.NMS.api.NMSClassName;

public class v1_19_R2_NMSClass extends v1_19_R1_NMSClass {

    public v1_19_R2_NMSClass() throws ClassNotFoundException {
        this("v1_19_R2");
    }

    public v1_19_R2_NMSClass(String version) throws ClassNotFoundException {
        super(version);
        addClassCache(NMSClassName.Registries, "net.minecraft.core.registries.Registries");
    }
}

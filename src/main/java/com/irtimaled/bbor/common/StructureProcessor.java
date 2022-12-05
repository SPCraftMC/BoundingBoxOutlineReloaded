package com.irtimaled.bbor.common;

import com.irtimaled.bbor.common.models.AbstractBoundingBox;
import com.irtimaled.bbor.common.models.BoundingBoxCuboid;
import com.irtimaled.bbor.common.models.Coords;
import it.unimi.dsi.fastutil.objects.Object2ObjectMaps;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import net.minecraft.world.level.levelgen.structure.StructureBoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StructureProcessor {
    public static final Object mutex = new Object();

    private static final Map<String, BoundingBoxType> supportedStructures = Object2ObjectMaps.synchronize(new Object2ObjectOpenHashMap<>(), mutex);
    public static final Set<String> supportedStructureIds = ObjectSets.synchronize(new ObjectOpenHashSet<>(), mutex);

    public static void registerSupportedStructure(@NotNull BoundingBoxType type) {
        if (!type.getName().startsWith("structure:")) {
            throw new IllegalArgumentException("type need start with \"structure:\"");
        }
        supportedStructures.put(type.getName(), type);
        supportedStructureIds.add(type.getName().substring("structure:".length()));
    }

    StructureProcessor(BoundingBoxCache boundingBoxCache) {
        this.boundingBoxCache = boundingBoxCache;
    }

    private final BoundingBoxCache boundingBoxCache;

    private void addStructures(BoundingBoxType type, StructureStart structureStart) {
        if (structureStart == null) return;

        StructureBoundingBox bb = structureStart.a();
        if (bb == null) return;


        AbstractBoundingBox boundingBox = buildStructure(bb, type);
        if (boundingBoxCache.isCached(boundingBox)) return;

        Set<AbstractBoundingBox> structureBoundingBoxes = new HashSet<>();
        for (StructurePiece structureComponent : structureStart.i()) {
            structureBoundingBoxes.add(buildStructure(structureComponent.f(), type));
        }
        boundingBoxCache.addBoundingBoxes(boundingBox, structureBoundingBoxes);
    }

    private AbstractBoundingBox buildStructure(StructureBoundingBox bb, BoundingBoxType type) {
        Coords min = new Coords(bb.g(), bb.h(), bb.i());
        Coords max = new Coords(bb.j(), bb.k(), bb.l());
        return BoundingBoxCuboid.from(min, max, type);
    }

    void process(Map<String, StructureStart> structures) {
        for (Map.Entry<String, StructureStart> entry : structures.entrySet()) {
            final BoundingBoxType type = supportedStructures.get("structure:" + entry.getKey());
            if (type != null) {
                addStructures(type, entry.getValue());
            }
        }
    }
}

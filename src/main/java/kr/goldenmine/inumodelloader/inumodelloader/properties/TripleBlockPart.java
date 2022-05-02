package kr.goldenmine.inumodelloader.inumodelloader.properties;

import net.minecraft.util.IStringSerializable;

public enum TripleBlockPart implements IStringSerializable  {
    UPPER,
    MIDDLE,
    LOWER;

    public String toString() { return this.getString(); }

    @Override
    public String getString() {
        return this == UPPER ? "upper" : this == MIDDLE ? "middle" : "lower";
    }
}
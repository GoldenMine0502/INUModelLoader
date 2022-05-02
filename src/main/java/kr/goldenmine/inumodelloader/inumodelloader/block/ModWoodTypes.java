package kr.goldenmine.inumodelloader.inumodelloader.block;

import kr.goldenmine.inumodelloader.inumodelloader.Inumodelloader;
import net.minecraft.block.WoodType;
import net.minecraft.util.ResourceLocation;

public class ModWoodTypes {
    public static final WoodType INUWood =
            WoodType.create(new ResourceLocation(Inumodelloader.MOD_ID, "inu_wood").toString());
}

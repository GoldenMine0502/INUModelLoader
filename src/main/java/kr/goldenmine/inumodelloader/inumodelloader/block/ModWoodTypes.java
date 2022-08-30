package kr.goldenmine.inumodelloader.inumodelloader.block;

import kr.goldenmine.inumodelloader.inumodelloader.INUModelLoader;
import net.minecraft.block.WoodType;
import net.minecraft.util.ResourceLocation;

public class ModWoodTypes {
    public static final WoodType INUWood =
            WoodType.create(new ResourceLocation(INUModelLoader.MOD_ID, "inu_wood").toString());

    public static final WoodType INUWoodMan =
            WoodType.create(new ResourceLocation(INUModelLoader.MOD_ID, "inu_wood_man").toString());

    public static final WoodType INUWoodWoman =
            WoodType.create(new ResourceLocation(INUModelLoader.MOD_ID, "inu_wood_woman").toString());
}

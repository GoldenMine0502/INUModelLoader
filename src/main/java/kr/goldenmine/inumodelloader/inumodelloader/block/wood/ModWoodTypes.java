package kr.goldenmine.inumodelloader.inumodelloader.block.wood;

import kr.goldenmine.inumodelloader.inumodelloader.Inumodelloader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.RedStoneOreBlock;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {
    public static final WoodType INUSignWood =
            WoodType.create(new ResourceLocation(Inumodelloader.MOD_ID, "inusign").toString());
}

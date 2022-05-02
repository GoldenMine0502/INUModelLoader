package kr.goldenmine.inumodelloader.inumodelloader.block;

import kr.goldenmine.inumodelloader.inumodelloader.tileentity.InuSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;


// https://github.com/Myrathi/FlatSignsEx
public class InuWallSignBlock extends WallSignBlock {
    public InuWallSignBlock(Properties properties, WoodType type) {
        super(properties, type);
    }


    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new InuSignBlockEntity(blockPos, blockState);
    }
}

//{
//  "variants": {
//    "facing=east,waterlogged=false": { "model": "inumodelloader:block/inu_sign" },
//    "facing=east,waterlogged=true": { "model": "inumodelloader:block/inu_sign" },
//    "facing=south,waterlogged=false": { "model": "inumodelloader:block/inu_sign" },
//    "facing=south,waterlogged=true": { "model": "inumodelloader:block/inu_sign" },
//    "facing=west,waterlogged=false": { "model": "inumodelloader:block/inu_sign" },
//    "facing=west,waterlogged=true": { "model": "inumodelloader:block/inu_sign" },
//    "facing=north,waterlogged=false": { "model": "inumodelloader:block/inu_sign" },
//    "facing=north,waterlogged=true": { "model": "inumodelloader:block/inu_sign" }
//  }
//}
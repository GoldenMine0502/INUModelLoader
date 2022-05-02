package kr.goldenmine.inumodelloader.inumodelloader.block;

import kr.goldenmine.inumodelloader.inumodelloader.tileentity.InuSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class InuStandingSignBlock extends StandingSignBlock {
    public InuStandingSignBlock(Properties properties, WoodType type) {
        super(properties, type);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new InuSignBlockEntity(blockPos, blockState);
    }
}

package kr.goldenmine.inumodelloader.inumodelloader.block.blocks;

//import kr.goldenmine.inumodelloader.inumodelloader.tileentity.InuSignTileEntity;
import kr.goldenmine.inumodelloader.inumodelloader.tileentity.InuSignTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

import javax.annotation.Nullable;

// https://github.com/Myrathi/FlatSignsEx
public class InuWallSignBlock extends WallSignBlock {
    public InuWallSignBlock(Properties properties, WoodType type) {
        super(properties, type);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new InuSignTileEntity();
    }

    // 공중에 뜰 수 있도록 함
    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return true;
    }
}
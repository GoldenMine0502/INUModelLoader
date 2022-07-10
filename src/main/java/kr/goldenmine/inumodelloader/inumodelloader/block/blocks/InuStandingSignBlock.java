package kr.goldenmine.inumodelloader.inumodelloader.block.blocks;

//import kr.goldenmine.inumodelloader.inumodelloader.tileentity.InuSignTileEntity;
import kr.goldenmine.inumodelloader.inumodelloader.tileentity.InuSignTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.StandingSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class InuStandingSignBlock extends StandingSignBlock {

    private final String signType;

    public InuStandingSignBlock(Properties properties, WoodType type, String signType) {
        super(properties, type);

        this.signType = signType;
    }

    public String getSignType() {
        return signType;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new InuSignTileEntity(signType);
    }

    @Override
    public boolean eventReceived(BlockState state, World worldIn, BlockPos pos, int id, int param) {
        return false;
    }

    // 공중에 뜰 수 있도록 함
    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return true;
    }
}

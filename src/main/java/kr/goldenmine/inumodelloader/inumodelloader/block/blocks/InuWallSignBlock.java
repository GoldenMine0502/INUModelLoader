package kr.goldenmine.inumodelloader.inumodelloader.block.blocks;

//import kr.goldenmine.inumodelloader.inumodelloader.entity.InuSignTileEntity;
import kr.goldenmine.inumodelloader.inumodelloader.entity.InuSignTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

// https://github.com/Myrathi/FlatSignsEx
public class InuWallSignBlock extends WallSignBlock {

    private final String signType;

    public InuWallSignBlock(Properties properties, WoodType type, String signType) {
        super(properties, type);

        this.signType = signType;
    }

    public String getSignType() {
        return signType;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new InuSignTileEntity(signType);
    }

    // 공중에 뜨도록 함
    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return true;
    }
}
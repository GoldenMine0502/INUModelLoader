package kr.goldenmine.inumodelloader.inumodelloader.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import java.util.stream.Stream;

public class OneByThreeVerticalBlock extends BaseHorizontalBlock {

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(0, -16, 0, 16, 32, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

//    private static final VoxelShape SHAPE_W = Stream.of(
//            Block.makeCuboidShape(0, 0, 0, 16, 16, 16)
//    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    public OneByThreeVerticalBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case NORTH:
            case SOUTH:
            case WEST:
            case EAST:
            default:
                return SHAPE_N;
        }
    }
}

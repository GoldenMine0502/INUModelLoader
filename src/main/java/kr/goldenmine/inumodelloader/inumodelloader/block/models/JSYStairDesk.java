package kr.goldenmine.inumodelloader.inumodelloader.block.models;

import kr.goldenmine.inumodelloader.inumodelloader.block.BaseHorizontalBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import java.util.stream.Stream;

public class JSYStairDesk extends BaseHorizontalBlock {

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(-16, 0, -1, 32, 16, 0),
            Block.makeCuboidShape(-16, 16, 0, 32, 17, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(-16, 0, 16, 32, 16, 17),
            Block.makeCuboidShape(-16, 16, 0, 32, 17, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(16, 0, -16, 17, 16, 32),
            Block.makeCuboidShape(0, 16, -16, 16, 17, 32)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(-1, 0, -16, 0, 16, 32),
            Block.makeCuboidShape(0, 16, -16, 16, 17, 32)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    public JSYStairDesk(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case NORTH:
                return SHAPE_N;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_E;
            case EAST:
                return SHAPE_W;
            default:
                return SHAPE_N;
        }
    }
}

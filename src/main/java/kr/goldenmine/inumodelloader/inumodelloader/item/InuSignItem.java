package kr.goldenmine.inumodelloader.inumodelloader.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SignItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class InuSignItem extends SignItem {
    public InuSignItem(Properties propertiesIn, Block floorBlockIn, Block wallBlockIn) {
        super(propertiesIn, floorBlockIn, wallBlockIn);
    }

    @Override
    protected boolean onBlockPlaced(BlockPos pos, World worldIn, @Nullable PlayerEntity player, ItemStack stack, BlockState state) {
        boolean flag = setTileEntityNBT(worldIn, player, pos, stack);

//        if (!worldIn.isRemote && !flag && player != null) { // 이로써 GUI가 열리지 않음
//            player.openSignEditor((SignTileEntity)worldIn.getTileEntity(pos));
//        }

        return flag;
    }
}

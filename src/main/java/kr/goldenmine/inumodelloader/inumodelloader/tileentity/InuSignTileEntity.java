package kr.goldenmine.inumodelloader.inumodelloader.tileentity;

import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class InuSignTileEntity extends SignTileEntity {
    public InuSignTileEntity() {
        super();
    }

    @Override
    public TileEntityType<?> getType() {
        return ModTileEntities.SIGN_TILE_ENTITIES.get();
    }
}
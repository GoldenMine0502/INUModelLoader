package kr.goldenmine.inumodelloader.inumodelloader.tileentity;

import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class InuSignTileEntity extends SignTileEntity {
//    private final String inuModelType;

    private String signType;

    public InuSignTileEntity() {

    }

    public InuSignTileEntity(String signType) {
        this.signType = signType;
    }

    public String getSignType() {
        return signType;
    }

    @Override
    public TileEntityType<?> getType() {
        switch (signType) {
            case "101":
                return ModTileEntities.SIGN_TILE_ENTITIES_101.get();
        }
        return ModTileEntities.SIGN_TILE_ENTITIES.get();
    }

//    public String getInuModelType() {
//        return inuModelType;
//    }
}
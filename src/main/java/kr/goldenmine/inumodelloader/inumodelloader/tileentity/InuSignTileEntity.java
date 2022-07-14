package kr.goldenmine.inumodelloader.inumodelloader.tileentity;

import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class InuSignTileEntity extends SignTileEntity {
//    private final String inuModelType;

    private String signType;

    private Supplier<TileEntityType<?>> tileEntityType;

    public InuSignTileEntity(String signType) {
        this.signType = signType;
    }

    public String getSignType() {
        return signType;
    }

    @Override
    public TileEntityType<?> getType() {
        return tileEntityType.get();
    }

    public void setTileEntityType(RegistryObject<TileEntityType<InuSignTileEntity>> sign_tile_entities) {
        this.tileEntityType = sign_tile_entities::get;
    }
}
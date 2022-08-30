package kr.goldenmine.inumodelloader.inumodelloader.entity;

import kr.goldenmine.inumodelloader.inumodelloader.INUModelLoader;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities {

    public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, INUModelLoader.MOD_ID);

//    public static final RegistryObject<TileEntityType<InuSignTileEntity>> SIGN_TILE_ENTITIES =
//            TILE_ENTITIES.register("inu_sign", () -> TileEntityType.Builder.create(InuSignTileEntity::new,
//                    ModBlocks.INU_WOOD_SIGN.get(),
//                    ModBlocks.INU_WOOD_WALL_SIGN.get()
//            ).build(null));

//    public static final RegistryObject<TileEntityType<InuSignTileEntity>> SIGN_TILE_ENTITIES_101 =
//            TILE_ENTITIES.register("inu_sign_101", () -> TileEntityType.Builder.create(InuSignTileEntity::new,
//                    ModBlocks.INU_WOOD_SIGN_101.get(),
//                    ModBlocks.INU_WOOD_WALL_SIGN_101.get()
//            ).build(null));

//    public static final RegistryObject<TileEntityType<InuSignTileEntity>> SIGN_TILE_ENTITIES_101 =
//            TILE_ENTITIES.register("inu_wood_sign_101", () -> TileEntityType.Builder.create(InuSignTileEntity::new,
//                    ModBlocks.INU_WOOD_SIGN_101.get(),
//                    ModBlocks.INU_WOOD_WALL_SIGN_101.get()
//            ).build(null));

//    public static final RegistryObject<TileEntityType<InuSignTileEntity>> SIGN_TILE_ENTITIES =
//            TILE_ENTITIES.register("inu_sign", () -> TileEntityType.Builder.create(InuSignTileEntity::new,
//                    ModBlocks.INU_WOOD_SIGN.get(),
//                    ModBlocks.INU_WOOD_WALL_SIGN.get()
//            ).build(null));


    public static void register(IEventBus eventBus) {
        TILE_ENTITIES.register(eventBus);
    }
}
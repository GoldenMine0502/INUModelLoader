package kr.goldenmine.inumodelloader.inumodelloader.tileentity;

import kr.goldenmine.inumodelloader.inumodelloader.Inumodelloader;
import kr.goldenmine.inumodelloader.inumodelloader.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlockEntities {

    public static DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Inumodelloader.MOD_ID);

    public static final RegistryObject<BlockEntityType<InuSignBlockEntity>> SIGN_BLOCK_ENTITIES =
            BLOCK_ENTITIES.register("inu_sign", () -> BlockEntityType.Builder.of(InuSignBlockEntity::new,
                    ModBlocks.INU_SIGN.get(),
                    ModBlocks.INU_WALL_SIGN.get()
            ).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}

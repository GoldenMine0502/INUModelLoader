package kr.goldenmine.inumodelloader.inumodelloader.sign;

import kr.goldenmine.inumodelloader.inumodelloader.INUModelLoader;
import kr.goldenmine.inumodelloader.inumodelloader.block.blocks.InuStandingSignBlock;
import kr.goldenmine.inumodelloader.inumodelloader.block.blocks.InuWallSignBlock;
import kr.goldenmine.inumodelloader.inumodelloader.item.InuSignItem;
import kr.goldenmine.inumodelloader.inumodelloader.item.ModItemGroup;
import kr.goldenmine.inumodelloader.inumodelloader.entity.InuSignTileEntity;
import kr.goldenmine.inumodelloader.inumodelloader.render.InuSignTileEntityRenderer;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.WoodType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SignModelRegistry {
    public static DeferredRegister<Block> SIGN_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, INUModelLoader.MOD_ID);
    public static DeferredRegister<Item> SIGN_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, INUModelLoader.MOD_ID);
    public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, INUModelLoader.MOD_ID);

    private static List<SignModelRegistry> registryList = new ArrayList<SignModelRegistry>();

    private RegistryObject<Block> blockSign;
    private RegistryObject<Block> blockWallSign;
    private RegistryObject<Item> itemSign;
    private RegistryObject<TileEntityType<InuSignTileEntity>> tileEntity;

    private String type;

    private static List<String> registeredTypes = new ArrayList<>();

    public SignModelRegistry(String type, WoodType texture) {
        this.type = type;

        blockSign = SIGN_BLOCKS.register("inu_sign_" + type,
                () -> new InuStandingSignBlock(AbstractBlock.Properties.create(Material.IRON).doesNotBlockMovement(), texture, type) {
                    @Nullable
                    @Override
                    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
                        return SignModelRegistry.this.createTileEntity();
                    }
                });

        blockWallSign = SIGN_BLOCKS.register("inu_wall_sign_" + type,
                () -> new InuWallSignBlock(AbstractBlock.Properties.create(Material.IRON).doesNotBlockMovement(), texture, type) {
                    @Override
                    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
                        return SignModelRegistry.this.createTileEntity();
                    }
                });

        itemSign = SIGN_ITEMS.register("inu_sign_" + type,
                () -> new InuSignItem(new Item.Properties().maxStackSize(1).group(ModItemGroup.INU_MODELS_TAB), blockSign.get(), blockWallSign.get()));

        tileEntity =
                TILE_ENTITIES.register("inu_sign_" + type, () -> TileEntityType.Builder.create(SignModelRegistry.this::createTileEntity,
                        blockSign.get(),
                        blockWallSign.get()
                ).build(null));
    }


    public RegistryObject<Block> getBlockSign() {
        return blockSign;
    }

    public RegistryObject<Block> getBlockWallSign() {
        return blockWallSign;
    }

    public RegistryObject<Item> getItemSign() {
        return itemSign;
    }

    public RegistryObject<TileEntityType<InuSignTileEntity>> getTileEntity() {
        return tileEntity;
    }

    private InuSignTileEntity createTileEntity() {
        InuSignTileEntity entity = new InuSignTileEntity(type);

        entity.setTileEntityType(tileEntity);

        return entity;
    }

    public static List<String> getRegisteredTypes() {
        return registeredTypes;
    }

    public static synchronized void registerSign(String type, WoodType woodType) {
        SignModelRegistry registry = new SignModelRegistry(type, woodType);

        registeredTypes.add(type);
        registryList.add(registry);
    }

    public static void register(IEventBus eventBus) {
        SIGN_BLOCKS.register(eventBus);
        SIGN_ITEMS.register(eventBus);
        TILE_ENTITIES.register(eventBus);
    }

    public static void bindAllRenderers() {
        registryList.forEach(it -> ClientRegistry.bindTileEntityRenderer(it.tileEntity.get(), InuSignTileEntityRenderer::new));
    }

}

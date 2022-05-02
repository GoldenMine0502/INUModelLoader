package kr.goldenmine.inumodelloader.inumodelloader.block;

import kr.goldenmine.inumodelloader.inumodelloader.Inumodelloader;
import kr.goldenmine.inumodelloader.inumodelloader.block.wood.ModWoodTypes;
import kr.goldenmine.inumodelloader.inumodelloader.item.ModCreativeModeTab;
import kr.goldenmine.inumodelloader.inumodelloader.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Inumodelloader.MOD_ID);

    public static final RegistryObject<Block> TITANIUM_BLOCK = registerBlock("titanium_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(12f)), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> TITANIUM_ORE = registerBlock("titanium_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(10f)), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> TEST_OBJ_BLOCK = registerBlock("test_obj_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.GLASS).strength(10f)), ModCreativeModeTab.INU_MODELS_TAB);

    public static final RegistryObject<Block> TREE_BLOCK = registerBlock("tree_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.AIR).strength(10f).noCollission().noOcclusion()), ModCreativeModeTab.INU_MODELS_TAB);

    public static final RegistryObject<Block> TALL_INU_DOOR_BLOCK = registerBlock("tall_inu_door_block",
            TallINUDoorBlock::new, ModCreativeModeTab.INU_MODELS_TAB);

    public static final RegistryObject<Block> INU_SIGN = BLOCKS.register("inu_sign",
            () -> new InuStandingSignBlock(BlockBehaviour.Properties.of(Material.METAL), ModWoodTypes.INUSignWood));

    public static final RegistryObject<Block> INU_WALL_SIGN = BLOCKS.register("inu_wall_sign",
            () -> new InuWallSignBlock(BlockBehaviour.Properties.of(Material.METAL), ModWoodTypes.INUSignWood));

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
package kr.goldenmine.inumodelloader.inumodelloader.block;

import kr.goldenmine.inumodelloader.inumodelloader.Inumodelloader;
import kr.goldenmine.inumodelloader.inumodelloader.block.blocks.InuStandingSignBlock;
import kr.goldenmine.inumodelloader.inumodelloader.block.blocks.InuWallSignBlock;
import kr.goldenmine.inumodelloader.inumodelloader.block.blocks.TallINUDoorBlock;
import kr.goldenmine.inumodelloader.inumodelloader.item.ModItemGroup;
import kr.goldenmine.inumodelloader.inumodelloader.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Inumodelloader.MOD_ID);

    public static final RegistryObject<Block> TITANIUM_BLOCK = registerBlock("titanium_block",
            () -> new Block(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(15f)), ModItemGroup.TUTORIAL_TAB);

    public static final RegistryObject<Block> TITANIUM_ORE = registerBlock("titanium_ore",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(10f)), ModItemGroup.TUTORIAL_TAB);

    public static final RegistryObject<Block> TEST_OBJ_BLOCK = registerBlock("test_obj_block",
            () -> new Block(AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(10f)), ModItemGroup.TUTORIAL_TAB);

    public static final RegistryObject<Block> TREE_BLOCK = registerBlock("tree_block",
            () -> new Block(AbstractBlock.Properties.create(Material.AIR).hardnessAndResistance(10f)), ModItemGroup.TUTORIAL_TAB);

    //Properties.of(Material.METAL).strength(5f)
    public static final RegistryObject<Block> TALL_INU_DOOR_BLOCK = registerBlock("tall_inu_door_block",
            () -> new TallINUDoorBlock(Blocks.OAK_DOOR), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> INU_WOOD_LOG = registerBlock("inu_wood_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_LOG)), ModItemGroup.TUTORIAL_TAB);

    public static final RegistryObject<Block> INU_WOOD_WOOD = registerBlock("inu_wood_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_WOOD)), ModItemGroup.TUTORIAL_TAB);

    public static final RegistryObject<Block> STRIPPED_INU_WOOD_LOG = registerBlock("stripped_inu_wood_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_LOG)), ModItemGroup.TUTORIAL_TAB);

    public static final RegistryObject<Block> STRIPPED_INU_WOOD_WOOD = registerBlock("stripped_inu_wood_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_WOOD)), ModItemGroup.TUTORIAL_TAB);

    public static final RegistryObject<Block> INU_WOOD_PLANKS = registerBlock("inu_wood_planks",
            () -> new Block(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)), ModItemGroup.TUTORIAL_TAB);

    public static final RegistryObject<Block> INU_WOOD_SIGN = BLOCKS.register("inu_wood_sign",
            () -> new InuStandingSignBlock(AbstractBlock.Properties.create(Material.IRON).doesNotBlockMovement(), ModWoodTypes.INUWood, ""));

    public static final RegistryObject<Block> INU_WOOD_SIGN_101 = BLOCKS.register("inu_wood_sign_101",
            () -> new InuStandingSignBlock(AbstractBlock.Properties.create(Material.IRON).doesNotBlockMovement(), ModWoodTypes.INUWood, "101"));

    public static final RegistryObject<Block> INU_WOOD_WALL_SIGN = BLOCKS.register("inu_wood_wall_sign",
            () -> new InuWallSignBlock(AbstractBlock.Properties.create(Material.IRON).doesNotBlockMovement(), ModWoodTypes.INUWood, ""));

    public static final RegistryObject<Block> INU_WOOD_WALL_SIGN_101 = BLOCKS.register("inu_wood_wall_sign_101",
            () -> new InuWallSignBlock(AbstractBlock.Properties.create(Material.IRON).doesNotBlockMovement(), ModWoodTypes.INUWood, "101"));

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, ItemGroup group) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, group);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block, ItemGroup group) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().group(group)));
    }

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().group(ModItemGroup.TUTORIAL_TAB)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
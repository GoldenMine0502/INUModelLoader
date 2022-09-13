package kr.goldenmine.inumodelloader.inumodelloader.block;

import kr.goldenmine.inumodelloader.inumodelloader.INUModelLoader;
import kr.goldenmine.inumodelloader.inumodelloader.block.blocks.TallINUDoorBlock;
import kr.goldenmine.inumodelloader.inumodelloader.block.models.JSYStairDesk;
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
            DeferredRegister.create(ForgeRegistries.BLOCKS, INUModelLoader.MOD_ID);

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

    public static final RegistryObject<Block> OHYEJIN_DESK1 = registerBlock("ohyejin_desk1",
            () -> new OneByThreeHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> OHYEJIN_DESK2 = registerBlock("ohyejin_desk2",
            () -> new OneByThreeHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> OHYEJIN_DESK3 = registerBlock("ohyejin_desk3",
            () -> new OneByThreeHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> OHYEJIN_DESK4 = registerBlock("ohyejin_desk4",
            () -> new OneByThreeHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> OHYEJIN_DESK5 = registerBlock("ohyejin_desk5",
            () -> new OneByThreeHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> OHYEJIN_DESK6 = registerBlock("ohyejin_desk6",
            () -> new BaseHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> OHYEJIN_DESK7 = registerBlock("ohyejin_desk7",
            () -> new BaseHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> OHYEJIN_DESK8 = registerBlock("ohyejin_desk8",
            () -> new BaseHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> OHYEJIN_BUCKET = registerBlock("ohyejin_bucket",
            () -> new BaseHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> OHYEJIN_CHAIR = registerBlock("ohyejin_chair",
            () -> new BaseHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> OHYEJIN_FLOWERPOT = registerBlock("ohyejin_flowerpot",
            () -> new BaseHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> OHYEJIN_LOCKER = registerBlock("ohyejin_locker",
            () -> new OneByThreeVerticalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> OHYEJIN_SINK = registerBlock("ohyejin_sink",
            () -> new BaseHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> OHYEJIN_SINKSJ = registerBlock("ohyejin_sinksj",
            () -> new BaseHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> BMJ_LAB1 = registerBlock("b.m.j.lab1",
            () -> new BaseHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> BMJ_LAB2 = registerBlock("b.m.j.lab2",
            () -> new BaseHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> BMJ_LAB3 = registerBlock("b.m.j.lab3",
            () -> new BaseHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> BSY_LOCKER = registerBlock("bsy_locker",
            () -> new ThreeByThreeBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> BSY_REAGENT1 = registerBlock("bsy_reagent1",
            () -> new OneByThreeVerticalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> BSY_REAGENT2 = registerBlock("bsy_reagent2",
            () -> new OneByThreeVerticalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> CHAIR_NOBACK_1 = registerBlock("chair_noback_1",
            () -> new BaseHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> CHAIR_NOBACK_2 = registerBlock("chair_noback_2",
            () -> new BaseHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> EUNHA_ELECTRONICS_SHELVES_1 = registerBlock("eunha_electronicshelves1",
            () -> new ThreeByThreeBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> EUNHA_ELECTRONICS_SHELVES_2 = registerBlock("eunha_electronicshelves2",
            () -> new OneByThreeVerticalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> JSY_DESK = registerBlock("j.s.y_desk",
            () -> new OneByThreeHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> KJH_HOME = registerBlock("k.j.h_home",
            () -> new ThreeByThreeBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> KJH_HOME2 = registerBlock("k.j.h_home2",
            () -> new OneByThreeHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> KJH_SMOKE = registerBlock("k.j.h_smoke",
            () -> new BaseHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> KJH_TRASH = registerBlock("k.j.h_trash",
            () -> new BaseHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> LEEJUNYONG_OVEN_1 = registerBlock("leejunyong_oven1",
            () -> new OneByThreeVerticalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> LEEJUNYONG_OVEN_2 = registerBlock("leejunyong_oven2",
            () -> new OneByThreeVerticalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> LEEJUNYONG_OVEN_3 = registerBlock("leejunyong_oven3",
            () -> new OneByThreeVerticalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> LEEJUNYONG_SINK_1 = registerBlock("leejunyong_sink1",
            () -> new OneByThreeHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> LEEJUNYONG_SINK_2 = registerBlock("leejunyong_sink2",
            () -> new BaseHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> LINS_BOOKSHELF = registerBlock("lins_bookshelf",
            () -> new ThreeByThreeBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> PROP_BOX = registerBlock("prop_box",
            () -> new BaseHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> PROP_CAMCODER = registerBlock("prop_camcoder",
            () -> new BaseHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> PROP_STORAGE_BOX = registerBlock("prop_storage_box",
            () -> new BaseHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> PROP_TISSUE_BOX = registerBlock("prop_tissue_box",
            () -> new BaseHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> IIS_PRINTER = registerBlock("iis_printer",
            () -> new BaseHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> KMS_BLUE_CHAIR_2 = registerBlock("kimmiseung_blue_chair_2",
            () -> new BaseHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> KMS_GREEN_CHAIR_2 = registerBlock("kimmiseung_green_chair_2",
            () -> new BaseHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> JSY_STAIR_DESK = registerBlock("jsy_stair_desk",
            () -> new OneByThreeHorizontalBlock(AbstractBlock.Properties.from(Blocks.OAK_WOOD).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> OHYEJIN_BOOKSHELF2_BT1 = registerBlock("ohyejin_bookshelf2_bt1",
            () -> new BaseHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> OHYEJIN_BOOKSHELF2_BT2 = registerBlock("ohyejin_bookshelf2_bt2",
            () -> new BaseHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> OHYEJIN_BOOKSHELF2_BT3 = registerBlock("ohyejin_bookshelf2_bt3",
            () -> new BaseHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> OHYEJIN_BOOKSHELF2_TOP1 = registerBlock("ohyejin_bookshelf2_top1",
            () -> new OneByThreeVerticalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> OHYEJIN_BOOKSHELF2_TOP2 = registerBlock("ohyejin_bookshelf2_top2",
            () -> new OneByThreeVerticalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> OHYEJIN_BOOKSHELF2_TOP3 = registerBlock("ohyejin_bookshelf2_top3",
            () -> new OneByThreeVerticalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> OHYEJIN_LABORATORY_EQUIPMENT_1 = registerBlock("ohyejin_laboratory_equipment_1",
            () -> new OneByThreeVerticalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> OHYEJIN_LABORATORY_EQUIPMENT_2 = registerBlock("ohyejin_laboratory_equipment_2",
            () -> new OneByThreeVerticalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> OHYEJIN_LABORATORY_EQUIPMENT_3 = registerBlock("ohyejin_laboratory_equipment_3",
            () -> new OneByThreeVerticalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

    public static final RegistryObject<Block> OHYEJIN_LABORATORY_EQUIPMENT_4 = registerBlock("ohyejin_laboratory_equipment_4",
            () -> new BaseHorizontalBlock(AbstractBlock.Properties.from(Blocks.GLASS).notSolid()), ModItemGroup.INU_MODELS_TAB);

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
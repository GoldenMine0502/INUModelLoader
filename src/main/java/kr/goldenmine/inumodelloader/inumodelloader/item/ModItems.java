package kr.goldenmine.inumodelloader.inumodelloader.item;

import kr.goldenmine.inumodelloader.inumodelloader.Inumodelloader;
import kr.goldenmine.inumodelloader.inumodelloader.block.ModBlocks;
import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Inumodelloader.MOD_ID);

    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot",
            () -> new Item(new Item.Properties().group(ModItemGroup.TUTORIAL_TAB)));

    public static final RegistryObject<Item> TITANIUM_NUGGET = ITEMS.register("titanium_nugget",
            () -> new Item(new Item.Properties().group(ModItemGroup.TUTORIAL_TAB)));

    public static final RegistryObject<Item> RAW_TITANIUM = ITEMS.register("raw_titanium",
            () -> new Item(new Item.Properties().group(ModItemGroup.TUTORIAL_TAB)));

    public static final RegistryObject<Item> INU_ITEM = ITEMS.register("inu_item",
            () -> new Item(new Item.Properties().group(ModItemGroup.INU_MODELS_TAB)));

    public static final RegistryObject<Item> INU_WOOD_SIGN = ITEMS.register("inu_wood_sign",
            () -> new SignItem(new Item.Properties().maxStackSize(16).group(ModItemGroup.INU_MODELS_TAB), ModBlocks.INU_WOOD_SIGN.get(), ModBlocks.INU_WOOD_WALL_SIGN.get()));

//    public static final RegistryObject<Item> TALL_INU_DOOR_ITEM = ITEMS.register("inu_item",
//            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.INU_MODELS_TAB)));

//    public static final RegistryObject<Item> TEST_OBJ_ITEM = ITEMS.register("test_obj_item",
//            () -> new BlockItem(ModBlocks.TEST_OBJ_BLOCK.get(), new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

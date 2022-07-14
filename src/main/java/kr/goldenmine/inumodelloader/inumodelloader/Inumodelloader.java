package kr.goldenmine.inumodelloader.inumodelloader;

import kr.goldenmine.inumodelloader.inumodelloader.block.ModBlocks;
import kr.goldenmine.inumodelloader.inumodelloader.block.ModWoodTypes;
import kr.goldenmine.inumodelloader.inumodelloader.item.ModItems;
import kr.goldenmine.inumodelloader.inumodelloader.sign.SignModelRegistry;
import kr.goldenmine.inumodelloader.inumodelloader.sign.SignSet;
import kr.goldenmine.inumodelloader.inumodelloader.tileentity.InuSignTileEntity;
import kr.goldenmine.inumodelloader.inumodelloader.tileentity.ModTileEntities;
import kr.goldenmine.inumodelloader.inumodelloader.tileentity.InuSignTileEntityRenderer;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.WoodType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Inumodelloader.MOD_ID)
public class Inumodelloader {

    public static final String MOD_ID = "inumodelloader";
    public static final String MOD_NAME = "INUModelLoader";
    public static final String VERSION = "1.0-SNAPSHOT";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();


    public Inumodelloader() {

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModTileEntities.register(eventBus);

        String[] signs = new String[] {
                "default",
                "08-101",
                "08-102",
                "08-103",
                "08-104",
                "08-105",
                "08-106",
                "08-107",
                "08-108",
                "08-109",
                "08-110",
                "08-111",
                "08-115",
                "08-116",
                "08-117a",
                "08-117b",
                "08-118",
                "08-302",
                "08-303",
                "08-304",
                "08-305",
                "08-306",
                "08-307",
                "08-308",
                "08-309",
                "08-310",
                "08-311",
                "08-312",
                "08-313",
                "08-314",
                "08-315",
                "08-316",
                "08-317",
                "08-318",
                "08-319",
                "08-321a",
                "08-321b",
                "08-321c",
                "08-322a",
                "08-322b",
                "08-322c",
                "08-361",
                "08-362",
                "08-363",
                "08-405",
                "08-406",
                "08-407",
                "08-464",
                "08-465",
                "08-466",
                "08-467",
                "08-468",
                "08-469",
                "08-470",
                "08-471",
                "08-472",
        };

        for(int i = 0; i < signs.length; i++) {
            // TODO 아쉽게도 엑셀에서 실시간으로 불러오기는 불가능...
            SignModelRegistry.registerSign(signs[i]);
        }

        SignModelRegistry.register(eventBus);

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());

        event.enqueueWork(() -> {
            WoodType.register(ModWoodTypes.INUWood);
        });
//        ItemBlockRenderTypes.setRenderLayer(ModBlocks.TREE_BLOCK.get(), RenderType.glintTranslucent());
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("inumodelloader", "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        event.enqueueWork(() -> {
            RenderType cutoutMipped = RenderType.getCutoutMipped();

            RenderTypeLookup.setRenderLayer(ModBlocks.TALL_INU_DOOR_BLOCK.get(), cutoutMipped);
            RenderTypeLookup.setRenderLayer(ModBlocks.TEST_OBJ_BLOCK.get(), cutoutMipped);

            SignModelRegistry.registerAllRenderers();
//            ClientRegistry.bindTileEntityRenderer(ModTileEntities.SIGN_TILE_ENTITIES.get(), InuSignTileEntityRenderer::new);
//            ClientRegistry.bindTileEntityRenderer(entity.get(), InuSignTileEntityRenderer::new);


            // load all sign contents
            ResourceLocation sheetLocation = new ResourceLocation(Inumodelloader.MOD_ID, "signs/signtext.xlsx");
            try {
                InputStream is = Minecraft.getInstance().getResourceManager().getResource(sheetLocation).getInputStream();
                SignSet.loadAll(is);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Atlases.addWoodType(ModWoodTypes.INUWood);
        });
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}

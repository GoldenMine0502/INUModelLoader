package kr.goldenmine.inumodelloader.inumodelloader;

import kr.goldenmine.inumodelloader.inumodelloader.block.ModBlocks;
import kr.goldenmine.inumodelloader.inumodelloader.block.ModWoodTypes;
import kr.goldenmine.inumodelloader.inumodelloader.events.ServerEvents;
import kr.goldenmine.inumodelloader.inumodelloader.item.ModItems;
import kr.goldenmine.inumodelloader.inumodelloader.network.AssetNetwork;
import kr.goldenmine.inumodelloader.inumodelloader.sign.SignModelRegistry;
import kr.goldenmine.inumodelloader.inumodelloader.sign.SignSet;
import kr.goldenmine.inumodelloader.inumodelloader.tileentity.ModTileEntities;
import kr.goldenmine.inumodelloader.inumodelloader.util.SignText;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.WoodType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Inumodelloader.MOD_ID)
public class Inumodelloader {

    public static final String MOD_ID = "inumodelloader";
    public static final String MOD_NAME = "INUModelLoader";
    public static final String VERSION = "1.2.5-SNAPSHOT";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    private String[] signs;

    public Inumodelloader() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::commonSetup);

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModTileEntities.register(eventBus);

        LOGGER.info("home path: " + System.getProperty("user.dir"));

        signs = new String[]{
                "default",
                "08-101", "08-102", "08-103", "08-104", "08-104b", "08-105", "08-106", "08-107", "08-108", "08-109",
                "08-110", "08-111", "08-112", "08-113", "08-115", "08-116", "08-117", "08-117b", "08-118",

                "08-201", "08-202", "08-203", "08-204", "08-204b",
                "08-211", "08-212", "08-213", "08-216", "08-218", "08-219",
                "08-223", "08-224", "08-225", "08-226", "08-228", "08-249",
                "08-250", "08-269", "08-270",

                "08-301", "08-302", "08-303", "08-304", "08-305", "08-306", "08-307", "08-308", "08-309",
                "08-310", "08-311", "08-312", "08-313", "08-314", "08-315", "08-316", "08-317", "08-318", "08-319",
                "08-321", "08-321b", "08-322", "08-322b", "08-323", "08-323b", "08-324", "08-325", "08-326", "08-327",
                "08-328",
                "08-330", "08-333", "08-334", "08-334b", "08-335", "08-338", "08-339", "08-339b",
                "08-340", "08-344", "08-345", "08-346", "08-347", "08-348", "08-349",
                "08-350", "08-351", "08-352", "08-353", "08-354", "08-355", "08-356", "08-357", "08-358", "08-359",
                "08-361", "08-362", "08-363", "08-364", "08-365", "08-366", "08-367", "08-368", "08-369",
                "08-370", "08-371", "08-372", "08-373", "08-374", "08-375", "08-376", "08-377", "08-378", "08-379",
                "08-380", "08-381", "08-382", "08-383", "08-384", "08-385", "08-385b", "08-386", "08-386b",

                "08-401", "08-402", "08-403", "08-404", "08-405", "08-406", "08-407", "08-408",
                "08-411", "08-412", "08-413", "08-416", "08-417", "08-417b", "08-418", "08-419",
                "08-420", "08-421", "08-423", "08-424", "08-425", "08-426", "08-429",
                "08-431", "08-432", "08-433", "08-435", "08-435b", "08-438", "08-438b",
                "08-443", "08-444",
                "08-452", "08-453", "08-454", "08-455", "08-456", "08-457", "08-458", "08-459",
                "08-460", "08-461", "08-462", "08-463", "08-464", "08-465", "08-466", "08-467", "08-468", "08-469",
                "08-470", "08-471", "08-472", "08-473", "08-474", "08-475", "08-476", "08-477", "08-478", "08-479",
                "08-480", "08-481", "08-481b", "08-482", "08-482b", "08-483", "08-483b",

                "08-502", "08-503", "08-507", "08-508",
                "08-516", "08-517", "08-518",
                "08-520", "08-524", "08-525", "08-526", "08-527", "08-528", "08-529",
                "08-530", "08-531", "08-532", "08-533", "08-534", "08-535", "08-539", "08-539b",
                "08-540", "08-540b", "08-542", "08-542b", "08-543", "08-545", "08-547", "08-548", "08-549",
                "08-550", "08-551", "08-553", "08-554", "08-555", "08-556", "08-558", "08-559",
                "08-561", "08-563", "08-564", "08-565", "08-569",
                "08-570", "08-571", "08-572", "08-575",
                "08-580", "08-581", "08-582", "08-583",
        };

        for (int i = 0; i < signs.length; i++) {
            // TODO 아쉽게도 엑셀에서 실시간으로 불러오기는 불가능...
            SignModelRegistry.registerSign(signs[i], ModWoodTypes.INUWood);
        }

        SignModelRegistry.registerSign("ps", ModWoodTypes.INUWood);
        SignModelRegistry.registerSign("ad", ModWoodTypes.INUWood);
        SignModelRegistry.registerSign("manb", ModWoodTypes.INUWoodMan);
        SignModelRegistry.registerSign("womanb", ModWoodTypes.INUWoodWoman);
        SignModelRegistry.registerSign("stair0", ModWoodTypes.INUWood);
        SignModelRegistry.registerSign("stair1", ModWoodTypes.INUWood);

        LOGGER.info("loaded " + signs.length + " sign models.");

        SignModelRegistry.register(eventBus);

//        FMLJavaModLoadingContext.get().getModEventBus().
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void commonSetup(final FMLCommonSetupEvent event) {
        AssetNetwork.init();
    }

    private void setup(final FMLCommonSetupEvent event) {
//        MinecraftForge.EVENT_BUS.register(new ServerEvents());
        event.enqueueWork(() -> {
            WoodType.register(ModWoodTypes.INUWood);
        });
//        ItemBlockRenderTypes.setRenderLayer(ModBlocks.TREE_BLOCK.get(), RenderType.glintTranslucent());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            RenderType cutoutMipped = RenderType.getCutoutMipped();

            RenderTypeLookup.setRenderLayer(ModBlocks.TALL_INU_DOOR_BLOCK.get(), cutoutMipped);
            RenderTypeLookup.setRenderLayer(ModBlocks.TEST_OBJ_BLOCK.get(), cutoutMipped);

            SignModelRegistry.bindAllRenderers();
//            ClientRegistry.bindTileEntityRenderer(ModTileEntities.SIGN_TILE_ENTITIES.get(), InuSignTileEntityRenderer::new);
//            ClientRegistry.bindTileEntityRenderer(entity.get(), InuSignTileEntityRenderer::new);

            Atlases.addWoodType(ModWoodTypes.INUWood);
            Atlases.addWoodType(ModWoodTypes.INUWoodMan);
            Atlases.addWoodType(ModWoodTypes.INUWoodWoman);

            loadSignData();
        });
    }

    public void loadSignData() {
        //        ResourceLocation sheetLocation = new ResourceLocation(Inumodelloader.MOD_ID, "data/signtext.csv");
        try {
            LOGGER.info("reading sheet data...");
            BufferedReader reader = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResource("data/signtext.csv").openStream()));
            List<String> list = reader.lines().collect(Collectors.toList());
            reader.close();

            SignSet.loadCsvAll(list);
            LOGGER.info("loaded " + SignSet.getSignInfoMap().size() + " sign data.");

            Set<String> remaining = new HashSet<>(SignSet.getSignInfoMap().keySet());
            for(int i = 0; i < signs.length; i++) {
                remaining.remove(signs[i]);
            }

            // 현재 c랑 d버전 표지판은 출력하지 않기로 하였음.
            remaining.removeIf(it -> it.contains("c") || it.contains("d"));

            LOGGER.info("not added (" + remaining.size() + ") : " + remaining.stream().sorted().collect(Collectors.toList()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

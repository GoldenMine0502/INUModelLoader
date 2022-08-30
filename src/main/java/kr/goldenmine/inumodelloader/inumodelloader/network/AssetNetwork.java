package kr.goldenmine.inumodelloader.inumodelloader.network;

import kr.goldenmine.inumodelloader.inumodelloader.INUModelLoader;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class AssetNetwork {
    public static final String NETWORK_VERSION = "0.1.1";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(INUModelLoader.MOD_ID, "assetnetwork"),
            () -> NETWORK_VERSION,
            version -> version.equals(NETWORK_VERSION),
            version -> version.equals(NETWORK_VERSION));

    public static void init() {
//        CHANNEL.registerMessage(0, AssetFileMessage.class, AssetFileMessage::encode, AssetFileMessage::decode, AssetFileMessage::handle);
    }
}

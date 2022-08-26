package kr.goldenmine.inumodelloader.inumodelloader.network.messages;

import kr.goldenmine.inumodelloader.inumodelloader.network.AssetNetwork;
import kr.goldenmine.inumodelloader.inumodelloader.sign.SignSet;
import kr.goldenmine.inumodelloader.inumodelloader.util.Align;
import kr.goldenmine.inumodelloader.inumodelloader.util.Point;
import kr.goldenmine.inumodelloader.inumodelloader.util.SignInfo;
import kr.goldenmine.inumodelloader.inumodelloader.util.SignText;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

public class AssetFileMessage {
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    private HashMap<String, SignInfo> map;

    public AssetFileMessage() {
        map = SignSet.getSignInfoMap();
    }

    public AssetFileMessage(HashMap<String, SignInfo> map) {
        this.map = map;
    }

    public static void encode(AssetFileMessage message, PacketBuffer buffer) {
        buffer.writeInt(message.map.size());

        message.map.forEach((k, v) -> {
            buffer.writeString(k);
            buffer.writeString(v.getSignTextureType());
            buffer.writeInt(v.getTexts().size());
            for(int i = 0; i < v.getTexts().size(); i++) {
                SignText text = v.getTexts().get(i);

                buffer.writeDouble(text.getPoint().getX());
                buffer.writeDouble(text.getPoint().getY());
                buffer.writeString(text.getText());
                buffer.writeInt(text.getColor());
                buffer.writeFloat(text.getMultiplier());
                if(text.getAlign() == Align.LEFT)
                    buffer.writeInt(0);
                else if(text.getAlign() == Align.CENTER)
                    buffer.writeInt(1);
                else
                    buffer.writeInt(2);
            }
        });
    }

    public static AssetFileMessage decode(PacketBuffer buffer) {
        HashMap<String, SignInfo> map = new HashMap<>();

        int count = buffer.readInt();

        for(int i = 0; i < count; i++) {
            String type = buffer.readString();
            String textureType = buffer.readString();
            int textCount = buffer.readInt();

            List<SignText> texts = new ArrayList<>();

            for (int j = 0; j < textCount; j++) {
                Point p = new Point(buffer.readDouble(), buffer.readDouble());
                String text = buffer.readString();
                int color = buffer.readInt();
                float multiplier = buffer.readFloat();
                Align align = Align.getAlignFromString(String.valueOf(buffer.readInt()));

                SignText signText = new SignText(p, text, multiplier, color, align);

                texts.add(signText);
            }

            map.put(type, new SignInfo(type, textureType, texts));
        }


        SignSet.getSignInfoMap().clear();
        LOGGER.info("putting " + map.size() + " sign data.");
        SignSet.getSignInfoMap().putAll(map);

        return new AssetFileMessage(map);
    }

    public static void handle(AssetFileMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();

        context.enqueueWork(() -> {
//            SignSet.getSignInfoMap().clear();
//            SignSet.getSignInfoMap().putAll(message.map);
        });
        context.setPacketHandled(true);
    }
}

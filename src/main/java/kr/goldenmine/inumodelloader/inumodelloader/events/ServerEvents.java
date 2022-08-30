package kr.goldenmine.inumodelloader.inumodelloader.events;

//import com.mbs.moh_ui.data.server.PlayerDataController;


import kr.goldenmine.inumodelloader.inumodelloader.network.AssetNetwork;
import kr.goldenmine.inumodelloader.inumodelloader.network.messages.AssetFileMessage;
import kr.goldenmine.inumodelloader.inumodelloader.sign.SignSet;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.PacketDistributor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerEvents {
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

//    @SubscribeEvent
//    public void onBlockBreak(BlockEvent.BreakEvent event) {
//        if(event.getPlayer() instanceof ServerPlayerEntity) {
//            ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
//
//            if(!player.hasPermissionLevel(1)) {
//                event.setCanceled(true);
//            }
//        }
//    }

    // For servers non-ops will have 0.
    // Ops will have the permission level that was set
    // in the server.properties (op-permission-level) at the time they were made op.

//    @SubscribeEvent
//    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
//        // 플레이어에게 표지판 데이터 보내기
//        if(event.getPlayer() instanceof ServerPlayerEntity) {
//            LOGGER.info("sending sign data packet to " + event.getPlayer().getName());
//            AssetNetwork.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()), new AssetFileMessage(SignSet.getSignInfoMap()));
//        }
//    }
}

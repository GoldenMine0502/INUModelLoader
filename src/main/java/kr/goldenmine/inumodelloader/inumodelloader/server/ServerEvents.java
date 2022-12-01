package kr.goldenmine.inumodelloader.inumodelloader.server;

//import com.mbs.moh_ui.data.server.PlayerDataController;


import lombok.extern.slf4j.Slf4j;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import okhttp3.ResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.File;

public class ServerEvents {
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

//    private static final StandardServiceRegistry registry;
//
//    static {
//        registry = new StandardServiceRegistryBuilder().configure(new File("config/hibernate.cfg.xml")).build();
//    }

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


    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        // 플레이어 접속 확인
        if(event.getPlayer() instanceof ServerPlayerEntity) {
            try {
                RetrofitServices.STATISTICS_SERVICE.joinRequest(event.getPlayer().getName().getString()).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            LOGGER.info("(join) sended to server sucessfully");
                        } else {
                            LOGGER.info("(join) connected but not succeed");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        LOGGER.error(t.getMessage(), t);
                    }
                });
            } catch(Exception ex) {
                ex.printStackTrace();
                LOGGER.info("single play joined");
            }
        }
    }

    @SubscribeEvent
    public void onPlayerQuit(PlayerEvent.PlayerLoggedOutEvent event) {
        if(event.getPlayer() instanceof ServerPlayerEntity) {
            try {
                RetrofitServices.STATISTICS_SERVICE.quitRequest(event.getPlayer().getName().getString()).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            LOGGER.info("(quit) sended to server sucessfully");
                        } else {
                            LOGGER.info("(quit) connected but not succeed");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        LOGGER.error(t.getMessage(), t);
                    }
                });
            } catch(Exception ex) {
                ex.printStackTrace();
                LOGGER.info("multi play joined");
            }
        }
    }
}

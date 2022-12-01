package kr.goldenmine.inumodelloader.inumodelloader.server;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;

public interface StatisticsService {
    @POST("/statistics/join")
    Call<ResponseBody> joinRequest(String username);

    @POST("/statistics/quit")
    Call<ResponseBody> quitRequest(String username);
}

package app.ntnt.finalprojectexamonline.services;

import app.ntnt.finalprojectexamonline.model.request.HistoryRequest;
import app.ntnt.finalprojectexamonline.model.response.ResponseEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IStatisticService {

    @GET("statistic/test/inTest")
    Call<ResponseEntity> getHighScore(@Query("testId") Long testId);


    @GET("statistic/test")
    Call<ResponseEntity> getFeatureTest(@Query("index") int index,@Query("size") int size);


}

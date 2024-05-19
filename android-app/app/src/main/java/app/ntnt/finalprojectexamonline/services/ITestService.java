package app.ntnt.finalprojectexamonline.services;

import app.ntnt.finalprojectexamonline.model.request.QuestionRequest;
import app.ntnt.finalprojectexamonline.model.request.TestRequest;
import app.ntnt.finalprojectexamonline.model.response.ResponseEntity;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ITestService {
    @POST("/test/add")
    Call<ResponseEntity> addTest(@Body TestRequest test);

    @GET("test/testInTopic")
    Call<ResponseEntity> getTestInTopic(@Query("topicId") long topicId, @Query("index") int index, @Query("size") int size);

    @GET("test")
    Call<ResponseEntity> getTestById(@Query("testId") long testId);
}

package app.ntnt.finalprojectexamonline.services;

import java.util.List;

import app.ntnt.finalprojectexamonline.model.entites.Subject;

import app.ntnt.finalprojectexamonline.model.request.RegisterRequest;
import app.ntnt.finalprojectexamonline.model.response.DeleteResponse;
import app.ntnt.finalprojectexamonline.model.response.RespRegister;
import app.ntnt.finalprojectexamonline.model.response.SubjectDataResponse;
import app.ntnt.finalprojectexamonline.model.response.TopicResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import app.ntnt.finalprojectexamonline.model.response.ResponseEntity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ISubjectService {


    @GET("subject")
    Call<SubjectDataResponse> getSubject();


    @Multipart
    @POST("subject/add")
    Call<RespRegister> addSubject(@Part("name") RequestBody name,
                                @Part MultipartBody.Part image);

    @Multipart
    @POST("subject/add")
    Call<RespRegister> addSubject(@Part("name") RequestBody name
                                  );
    @GET("subject/del")
    Call<DeleteResponse> delSubject(@Query("subjectId") long subjectId);
    
    @GET("subject")
    Call<ResponseEntity> getAllSubject();

    @GET("subject/getSubjectByUserId")
    Call<ResponseEntity> getSubjectByUserId(@Query("userId") long userId);


}

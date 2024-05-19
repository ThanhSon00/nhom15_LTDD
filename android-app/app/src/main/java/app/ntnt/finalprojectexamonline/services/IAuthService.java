package app.ntnt.finalprojectexamonline.services;



import app.ntnt.finalprojectexamonline.model.request.LoginRequest;
import app.ntnt.finalprojectexamonline.model.request.RegisterRequest;

import app.ntnt.finalprojectexamonline.model.request.ResetPasswordRequest;
import app.ntnt.finalprojectexamonline.model.response.AuthResponse;
import app.ntnt.finalprojectexamonline.model.response.ResponseEntity;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface IAuthService {
    @POST("auth/login")
    Call<ResponseEntity> login (@Body LoginRequest loginRequest);


    @Multipart
    @POST("auth/register")
    Call<ResponseEntity> register(@Part("user") RegisterRequest user,
                                  @Part MultipartBody.Part avatar);

    @POST("auth/reset")
    Call<ResponseEntity> resetPassword(@Body ResetPasswordRequest resetPasswordRequest);

}

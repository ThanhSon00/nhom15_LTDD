package app.ntnt.finalprojectexamonline.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.ntnt.finalprojectexamonline.model.response.RespRegister;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginService {
    Gson gson = new GsonBuilder().setDateFormat("dd:MM:yyyy").create();
    LoginService loginService = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/auth/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(LoginService.class);
    @FormUrlEncoded
    @POST("register")
    Call<RespRegister> createUser(@Field("username") String username,
                                  @Field("password") String password,
                                  @Field("email") String email,
                                  @Field("phoneNumber") String phoneNumber,
                                  @Field("firstName") String firstName,
                                  @Field("lastName") String lastName);
}

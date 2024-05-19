package app.ntnt.finalprojectexamonline.services;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseAPIService {
//   public static final String URL_BASE = "http://172.16.0.211:8080";
// public static final String URL_BASE = "http://192.168.137.1:8080";
public static final String URL_BASE = "http://10.0.2.2:8080";
    public static final OkHttpClient httpClient
            = new OkHttpClient
            .Builder()
            .addInterceptor(new RequestInterceptor())
            .build();

    public static final Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient);

    public static final Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> service){
        return retrofit.create(service);
    }
}

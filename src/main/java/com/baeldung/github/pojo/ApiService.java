package com.baeldung.github.pojo;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ApiService {

  public static void main(String[] args) {
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build();

    UserService service = retrofit.create(UserService.class);
    Call<User> callSync = service.getUser("eugenp");

    try {
      Response<User> response = callSync.execute();
      User user = response.body();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}

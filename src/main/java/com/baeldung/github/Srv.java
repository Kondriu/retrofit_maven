package com.baeldung.github;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class Srv {
  public static void main(String[] args) {

    OkHttpClient.Builder builderClient = new OkHttpClient.Builder();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(builderClient.build())
            .build();
    UserService service = retrofit.create(UserService.class);
    Call<List<User>> call = service.getUsers(100,1);

    try {
      Response<List<User>> response = call.execute();
      response.body().stream()
              .map(x -> x.login)
              .forEach(System.out::println);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}

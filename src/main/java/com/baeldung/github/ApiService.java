package com.baeldung.github;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ApiService {

  public static void main(String[] args) {
    User user = new User();
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build();

    UserService service = retrofit.create(UserService.class);
    //Call<User> callSync = service.getUser("eugenp");
    Call<User> callSync = service.getUser("kondriu");

    try {
      Response<User> response = callSync.execute();
      user = response.body();
    } catch (IOException e) {
      e.printStackTrace();
    }


//    System.out.println("url: \t\t" + user.url);
//    System.out.println("htmlUrl: \t" + user.htmlUrl);
//    System.out.println("login: \t\t" + user.login);
//    System.out.println("id: \t\t" + user.id);


  }

}

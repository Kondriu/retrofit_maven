package eu.jlelse.movie;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class MainActivity {

  Logger log = LoggerFactory.getLogger(MainActivity.class);
  public static final String BASE_URL = "http://api.themoviedb.org/3/";
  private static Retrofit retrofit = null;
  private final static String API_KEY = "";// insert your themoviedb.org API KEY here

  public static void main(String[] args) {
    MainActivity mainActivity = new MainActivity();
    mainActivity.connectAndGetApiData();
  }

  // This method create an instance of Retrofit
  // set the base url
  public void connectAndGetApiData() {
    if (retrofit == null) {
      retrofit = new Retrofit.Builder()
              .baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create())
              .build();
    }
    MovieApiService movieApiService = retrofit.create(MovieApiService.class);
    Call<MovieResponse> call = movieApiService.getTopRatedMovies(API_KEY);

    call.enqueue(new Callback<MovieResponse>() {
      @Override
      public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
        List<Movie> movies = response.body().getResults();
//        recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
        log.debug(String.format("Number of movies received: %d", movies.size()));
      }

      @Override
      public void onFailure(Call<MovieResponse> call, Throwable throwable) {
        log.debug(throwable.toString());
      }
    });
  }
}

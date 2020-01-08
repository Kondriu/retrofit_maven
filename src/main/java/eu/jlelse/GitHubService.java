package eu.jlelse;

import com.baeldung.github.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface GitHubService {
  @GET("group/{id}/users")
  Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort);

}

package abassawo.c4q.nyc.retrogit;




import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by c4q-Abass on 12/24/15.
 */
public interface GitService {
    public static String API = "https://api.github.com";

    @GET("/users/{user}")      //here is the other url part.best way is to start using /
    public void getFeed(@Path("user") String user, Callback<GitObj> response);

    @GET("/users/{user}/repos")
    public void listRepos(@Path("user") String user, Callback<List<Repo>>response);

}
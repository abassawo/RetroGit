package abassawo.c4q.nyc.retrogit;

import retrofit.RestAdapter;

/**
 * Created by c4q-Abass on 12/24/15.
 */

//Singlton
public class Github {
    static RestAdapter REST_SERVICE = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(GitService.API).build();

    public static GitService getService(){
        return REST_SERVICE.create(GitService.class);
    }

}

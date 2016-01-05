package abassawo.c4q.nyc.retrogit;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by c4q-Abass on 12/24/15.
 */
public class QueryPrefs {
    private static final String PREF_SEARCH_QUERY = "searchQuery";
    private static final String PREF_LAST_RESULT_ID = "lastResultId";


    public static String getStoredQuery(Context ctx){
        return PreferenceManager.getDefaultSharedPreferences(ctx).getString(PREF_SEARCH_QUERY, null);
    }

    public static void setStoredQuery(Context ctx, String query){
        PreferenceManager.getDefaultSharedPreferences(ctx).edit().putString(PREF_SEARCH_QUERY, query).apply();
    }


    public static String getfLastResultId(Context ctx){
        return PreferenceManager.getDefaultSharedPreferences(ctx).getString(PREF_LAST_RESULT_ID, null);
    }

    public static void setLastResultid(Context ctx, String lastResultId){
        PreferenceManager.getDefaultSharedPreferences(ctx).edit().putString(PREF_LAST_RESULT_ID, lastResultId).apply();
    }


}
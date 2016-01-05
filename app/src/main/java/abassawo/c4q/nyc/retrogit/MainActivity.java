package abassawo.c4q.nyc.retrogit;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    ListView listView;
    RecyclerView mRecyclerView;
    ImageView imgView;
    private String TAG = "MainActivity";
    private List<String> repos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        updateService("abassawo");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_item_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = QueryPrefs.getStoredQuery(getApplicationContext());
                searchView.setQuery(query, false);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "Query text submitted: " + query);
                QueryPrefs.setStoredQuery(getApplicationContext(), query);
                updateService(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, "Query text submitted: " + newText);
                return false;
            }
        });
        return  super.onCreateOptionsMenu(menu);

    }

    public void initViews(){
//        listView = (ListView) findViewById(R.id.list_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    public void updateService(String user){
        GitService gitService = Github.getService();
        gitService.listRepos(user, new Callback<List<Repo>>() {
            @Override
            public void success(List<Repo> repos, Response response) {
                //listView.setAdapter(new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, repos));
                mRecyclerView.setAdapter(new RVAdapter(repos));
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private class RepoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private CardView mCardView;
        private TextView mTitle;

        public RepoHolder(View itemView) {
            super(itemView);
            mTitle = (TextView)itemView.findViewById(R.id.repo_card_title);
            itemView.setOnClickListener(this);
        }

        public void bindRepo(Repo repo){
            mTitle.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            mTitle.setText(repo.getName());
        }

        @Override
        public void onClick(View v) {

        }
    }

    private class RVAdapter extends RecyclerView.Adapter<RepoHolder>{
        private List<Repo> repos;

        public RVAdapter(List<Repo> repos){
            this.repos = repos;
        }
        @Override
        public RepoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
            View view = inflater.inflate(R.layout.repo_item, parent, false);
            return new RepoHolder(view);
        }

        @Override
        public void onBindViewHolder(RepoHolder holder, int position) {
        Repo repo = repos.get(position);
            holder.bindRepo(repo);
        }

        @Override
        public int getItemCount() {
            return repos.size();
        }
    }
}
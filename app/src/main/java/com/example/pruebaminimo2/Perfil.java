package com.example.pruebaminimo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.pruebaminimo2.github.Contributor;
import com.example.pruebaminimo2.github.GitHub;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Perfil extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    //private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(Perfil.this);
        recyclerView.setLayoutManager(layoutManager);

        // Set the adapter
        adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);

        doApiCall();

        // Manage swipe on items
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
                            target) {
                        return false;
                    }
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        adapter.remove(viewHolder.getAdapterPosition());
                    }
                };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


    }

    private void doApiCall() {
        Bundle extras = getIntent().getExtras();
        String owner = extras.getString("key");
        //Log.d("Owner", owner);
        GitHub gitHubService = GitHub.retrofit.create(GitHub.class);
        Call<List<Contributor>> call = gitHubService.contributors(owner);//"MarcelMarco"
        Log.d("Call1234", call.request().toString());
        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                // set the results to the adapter
                adapter.setData(response.body());
                Log.d("Respuesta", response.message().toString());
                //if(mySwipeRefreshLayout!=null) mySwipeRefreshLayout.setRefreshing(false);
            }
            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {
                //if(mySwipeRefreshLayout!=null) mySwipeRefreshLayout.setRefreshing(false);
                Log.d("Fallada", t.toString());
            }
        });
    }
}
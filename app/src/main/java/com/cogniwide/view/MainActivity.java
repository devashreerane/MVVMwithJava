package com.cogniwide.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cogniwide.R;
import com.cogniwide.adapter.MoviesAdapter;
import com.cogniwide.model.MovieModel;
import com.cogniwide.model.Result;
import com.cogniwide.utils.Util;
import com.cogniwide.viewmodel.MovieViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    MovieViewModel movieViewModel;

    RecyclerView rvMovies;
    MoviesAdapter moviesAdapter;
    LinearLayoutManager linearLayout;
    List<Result> movieList = new ArrayList<>();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle(getString(R.string.please_wait));
        progressDialog.setCanceledOnTouchOutside(false);

        rvMovies = findViewById(R.id.rvMovies);
        linearLayout = new LinearLayoutManager(this);
        rvMovies.setLayoutManager(linearLayout);

        if (Util.isNetworkConnected(this)) {

            progressDialog.show();

            movieViewModel.getMovieData().observe(this, movieModel ->
            {
                Gson gson = new GsonBuilder().create();

                String res = gson.toJson(movieModel, MovieModel.class);

                try {
                    JSONObject jsonObject = new JSONObject(res);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");

                    Log.e("JSONArrayyyyyyy", jsonArray.toString());

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsons = jsonArray.getJSONObject(i);

                        Result result = new Result();
                        result.setTitle(jsons.optString("title", ""));
                        result.setReleaseDate(jsons.optString("release_date", ""));
                        result.setPosterPath(jsons.optString("poster_path", ""));


                        movieList.add(result);
                    }

                    progressDialog.cancel();
                    moviesAdapter = new MoviesAdapter(this, movieList);
                    rvMovies.setAdapter(moviesAdapter);
                    moviesAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    progressDialog.cancel();
                    e.printStackTrace();
                }


            });
        }else
        {
            progressDialog.cancel();
            Toast.makeText(this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
package com.example.codepathproject3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView List;
    ArrayList<model> combine = new ArrayList<>();
    String title, body, picture;
    JSONObject movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List = findViewById(R.id.list);

        APICall();
        adapterClass adapter = new adapterClass(MainActivity.this, combine);
        List.setAdapter(adapter);
        List.setLayoutManager(new LinearLayoutManager(this));
    }

    public void APICall(){
        Request postRequest = new Request.Builder()
                .url("https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed&language=en-US&page=1")
                .build();
        new OkHttpClient()
                .newCall(postRequest)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull okhttp3.Call call, @NonNull IOException e) {
                        Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onResponse(@NonNull okhttp3.Call call, @NonNull Response response) throws IOException {
                        Log.i("tag", response.body().string());
                        try {
                            movies = new JSONObject(String.valueOf(response.body()));
                            JSONArray result = movies.getJSONArray("results");
                            for(int i=0; i< result.length(); i++) {
                                combine.add(new model(
                                        title = result.getJSONObject(i).getString("original_title"),
                                        body = result.getJSONObject(i).getString("overview"),
                                        picture = result.getJSONObject(i).getString("poster_path")));
                            }
                                } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

}
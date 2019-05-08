package com.example.moviezone;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private TextView arrayListTextView;
    private ImageView posterImage;
    private RequestQueue queue;
    private Context context;

    String URL = "https://api.themoviedb.org/3/discover/movie?api_key=d0532d41c9054bf65a4ec278b98fd6cf&language=en-US&" +
            "sort_by=popularity.desc&include_adult=false&include_video=false&page=2&" +
            "primary_release_date.gte=1990-01-01&primary_release_date.lte=1999-12-31&vote_average.gte=6&with_genres=28";


    String requestUrl = "https://api.themoviedb.org/3/discover/movie?api_key=d0532d41c9054bf65a4ec278b98fd6cf" +
            "&language=en-US&" +
            "sort_by=popularity.desc" +
            "&include_adult=false" +
            "&include_video=false" +
            "&page=1" +
            "&primary_release_date.gte=%s" +
            "&primary_release_date.lte=%s" +
            "&vote_average.gte=%s" +
            "&with_genres=%s" +
            "&with_original_language=en\n";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        posterImage = findViewById(R.id.posterImage);
        arrayListTextView = findViewById(R.id.arrayListTextView);
        queue = Volley.newRequestQueue(this);


        getIntent();
        ArrayList<String> allAnswers = (ArrayList<String>) getIntent().getSerializableExtra("allAnswers");

        Log.d("!!!", allAnswers.toString());

        String vote = allAnswers.get(0);
        String date = allAnswers.get(1);
        String[] dateArr = date.split(" ");
        String date1 = dateArr[0];
        String date2 = dateArr[1];
        String genre = allAnswers.get(2);

        String url = String.format(requestUrl, date1, date2, vote, genre);
        jsonParse(url);

        Log.d("!!!!", "onCreate: " + url);


        //arrayListTextView.setText(allAnswers.toString());

       /* String URL = "https://api.themoviedb.org/3/discover/movie?api_key=d0532d41c9054bf65a4ec278b98fd6cf&language=en-US&" +
                "sort_by=popularity.desc&include_adult=false&include_video=false&page=1&" +
                "primary_release_date.gte=" + allAnswers.get(1) +
                "2&vote_average.gte=" + allAnswers.get(0) + "&with_genres=" + allAnswers.get(2);*/
    }
        private void jsonParse(String url){
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("results");

                        JSONObject result = jsonArray.getJSONObject(0);
                        String title = result.getString("title");
                        String release_date = result.getString("release_date");
                        String vote_average = result.getString("vote_average");
                        String poster_path = result.getString("poster_path");

                        arrayListTextView.setText(title + " , " + release_date + " , " + vote_average);

                        Log.d("!!!!", poster_path);
                        Glide.with(ResultActivity.this)
                                .load("https://image.tmdb.org/t/p/w500" + poster_path)
                                .override(303,175)
                                .into(posterImage);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                }
            });
            queue.add(request);
        }



    }




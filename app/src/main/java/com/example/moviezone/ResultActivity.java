package com.example.moviezone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import org.w3c.dom.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class ResultActivity extends AppCompatActivity {

    private TextView arrayListTextView;
    private ImageView posterImage;
    private Button showButton;
    private TextView titleTextView;
    private TextView ratingTextView;


    private RequestQueue mQueue;
    Movie movie;
    ArrayList<Movie> movies = new ArrayList<>();
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        arrayListTextView = findViewById(R.id.arrayListTextView);
        posterImage = findViewById(R.id.posterImage);
        showButton = findViewById(R.id.showButton);
        titleTextView = findViewById(R.id.titleTextView);
        ratingTextView = findViewById(R.id.ratingTextView);

        getIntent();
        ArrayList<String> allAnswers = (ArrayList<String>) getIntent().getSerializableExtra("allAnswers");
        Log.d("!!!", allAnswers.toString());
        arrayListTextView.setText(allAnswers.toString());

        mQueue = Volley.newRequestQueue(this);

        String url = "https://api.themoviedb.org/3/discover/movie?api_key=d0532d41c9054bf65a4ec278b98fd6cf&language=en-US&" +
                "sort_by=popularity.desc&include_adult=false&include_video=false&page=1&" +
                "primary_release_date.gte=" + allAnswers.get(1) +
                "&vote_average.gte=" + allAnswers.get(0) + "&with_genres=" + allAnswers.get(2);

        jsonParse(url);

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movie =  movies.get(random.nextInt(19));

                titleTextView.setText(movie.getTitle());
                ratingTextView.setText(String.valueOf(movie.getRating()));
                Glide.with(ResultActivity.this).load("https://image.tmdb.org/t/p/original"+movie.getPoster()).into(posterImage);
            }
        });

    }

    private void jsonParse(String url){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject film = jsonArray.getJSONObject(i);


                                movies.add(new Movie(film.getString("title"), film.getString("release_date"), film.getDouble("vote_average"),
                                        film.getString("poster_path")));

                                Log.d("###", movies.toString());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mQueue.add(request);
        Log.d("###", movies.toString());
    }
}

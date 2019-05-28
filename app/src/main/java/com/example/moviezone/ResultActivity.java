
package com.example.moviezone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

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

    private ImageView posterImage;
    private Button showButton;
    private TextView titleTextView;
    private TextView ratingTextView;
    private TextView info;

    private Boolean toStart;

    private Toolbar toolbar;

    private ProgressBar progressBar;


    private RequestQueue mQueue;
    Movie movie;
    ArrayList<Movie> movies = new ArrayList<>();
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        this.setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        posterImage = findViewById(R.id.posterImage);
        showButton = findViewById(R.id.showButton);
        titleTextView = findViewById(R.id.titleTextView);
        ratingTextView = findViewById(R.id.ratingTextView);
        info = findViewById(R.id.info);
        progressBar = findViewById(R.id.progressbar);

        Bundle b = getIntent().getExtras();


        if(b.getSerializable("allAnswers")!= null){
            ArrayList<String> allAnswers = (ArrayList<String>) getIntent().getSerializableExtra("allAnswers");
            Log.d("!!!", allAnswers.toString());

            mQueue = Volley.newRequestQueue(this);
            String url = "https://api.themoviedb.org/3/discover/movie?api_key=d0532d41c9054bf65a4ec278b98fd6cf&language=en-US&" +
                    "sort_by=popularity.desc&include_adult=false&include_video=false&page=1&" +
                    "primary_release_date.gte=" + allAnswers.get(1) +
                    "&vote_average.gte=" + allAnswers.get(0) + "&with_genres=" + allAnswers.get(2);

            jsonParse(url);

            showButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    movie =  movies.get(random.nextInt(19));

                    titleTextView.setText(movie.getTitle());
                    ratingTextView.setText(String.valueOf(movie.getRating()));
                    info.setText(movie.getOverview());
                    if(movie.getPoster() != "null"){
                        Glide.with(ResultActivity.this).load("https://image.tmdb.org/t/p/original"+movie.getPoster()).into(posterImage);
                    }

                }
            });
            toStart = true;

        }else{
            titleTextView.setText(b.getString("title"));
            ratingTextView.setText(String.valueOf(b.getDouble("rating")));
            info.setText(b.getString("overview"));

            String postImage = b.getString("poster");

            if(!postImage.equals("null")){
                Glide.with(ResultActivity.this).load("https://image.tmdb.org/t/p/original"+postImage).into(posterImage);
            }
            showButton.setVisibility(View.INVISIBLE);

            toStart = false;
        }


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(toStart){
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
            }else{
                onBackPressed();
            }
        }
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {

        if(toStart){
            startActivity(new Intent(ResultActivity.this, MainActivity.class));
        }else{
            onBackPressed();
        }
        return true;

    }



    private void jsonParse(String url){
        progressBar.setVisibility(View.VISIBLE);
        showButton.setVisibility(View.INVISIBLE);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject film = jsonArray.getJSONObject(i);


                                movies.add(new Movie(film.getString("title"), film.getString("release_date"), film.getDouble("vote_average"),
                                        film.getString("poster_path"),film.getString("overview")));

                                Log.d("###", movies.toString());
                            }
                            showButton.callOnClick();
                            progressBar.setVisibility(View.GONE);
                            showButton.setVisibility(View.VISIBLE);

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
    }
}
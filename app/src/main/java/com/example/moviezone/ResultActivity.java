package com.example.moviezone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private TextView arrayListTextView;

    String URL = "https://api.themoviedb.org/3/discover/movie?api_key=d0532d41c9054bf65a4ec278b98fd6cf&language=en-US&" +
            "sort_by=popularity.desc&include_adult=false&include_video=false&page=2&" +
            "primary_release_date.gte=1990-01-01&primary_release_date.lte=1999-12-31&vote_average.gte=6&with_genres=28";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        arrayListTextView = findViewById(R.id.arrayListTextView);

        getIntent();
        ArrayList<String> allAnswers = (ArrayList<String>) getIntent().getSerializableExtra("allAnswers");

        Log.d("!!!", allAnswers.toString());

        arrayListTextView.setText(allAnswers.toString());

        String URL = "https://api.themoviedb.org/3/discover/movie?api_key=d0532d41c9054bf65a4ec278b98fd6cf&language=en-US&" +
                "sort_by=popularity.desc&include_adult=false&include_video=false&page=1&" +
                "primary_release_date.gte=" + allAnswers.get(1) +
                "2&vote_average.gte=" + allAnswers.get(0) + "&with_genres=" + allAnswers.get(2);
    }
}

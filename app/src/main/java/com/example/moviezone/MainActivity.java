package com.example.moviezone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toms kommentar
        //joakim
        //Sara
        //daniel
        //Tom stationär

        Movie movie = new Movie("Shawshank redemption", 1994, "Drama", 9.4,
                "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtOD" +
                        "M1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg");

        Log.d("!!!", movie.getTitle());
    }
}

package com.example.moviezone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);

        //Toms kommentar
        //joakim
        //Sara
        //daniel
        //Tom stationär

        // VÅRAN API NYCKEL: 844f26c4

       /* Movie movie = new Movie("Shawshank redemption", 1994, "Drama", 9.4,
                "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtOD" +
                        "M1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg");

        Log.d("!!!", movie.getTitle()); */

       startButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, QuestionsActivity.class);
               startActivity(intent);
           }
       });
    }
}

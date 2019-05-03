package com.example.moviezone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private TextView arrayListTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        arrayListTextView = findViewById(R.id.arrayListTextView);

        getIntent();
        ArrayList<String> allAnswers = (ArrayList<String>) getIntent().getSerializableExtra("allAnswers");

        Log.d("!!!", allAnswers.toString());

        arrayListTextView.setText(allAnswers.toString());
    }
}

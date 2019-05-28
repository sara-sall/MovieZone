package com.example.moviezone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionsActivity extends AppCompatActivity {

    private TextView questionContent;

    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;
    private Button buttonFour;

    //API:n kommer från the themoviedb.org (INTE OMDB)

    String API_KEY = "d0532d41c9054bf65a4ec278b98fd6cf";
    String API_URL = "https://api.themoviedb.org/3/movie/76341?api_key=";

    private int mQuestionNumber = 0;
    private QuestionLibrary questionsLibrary = new QuestionLibrary();
    private ArrayList<String> savedAnswers = new ArrayList<>();

    private Viewers viewerResults = new Viewers();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        questionContent = findViewById(R.id.questionContent);

        buttonOne = findViewById(R.id.buttonOne);
        buttonTwo = findViewById(R.id.buttonTwo);
        buttonThree = findViewById(R.id.buttonThree);
        buttonFour = findViewById(R.id.buttonFour);

        updateQuestion();

        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedAnswers.add(buttonOne.getText().toString());
                updateQuestion();
            }
        });

        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedAnswers.add(buttonTwo.getText().toString());
                updateQuestion();
            }
        });
        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedAnswers.add(buttonThree.getText().toString());
                updateQuestion();
            }
        });
        buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedAnswers.add(buttonFour.getText().toString());
                updateQuestion();
            }
        });

    }

    public void updateQuestion(){
        if(mQuestionNumber == 3){
            Intent intent = new Intent(QuestionsActivity.this, ResultActivity.class);
            intent.putExtra("allAnswers", savedAnswers);

            startActivity(intent);
        } else {

            questionContent.setText(questionsLibrary.getQuestion(mQuestionNumber));
            buttonOne.setText(questionsLibrary.getFirstChoice(mQuestionNumber));
            buttonTwo.setText(questionsLibrary.getSecondChoice(mQuestionNumber));
            buttonThree.setText(questionsLibrary.getThirdChoice(mQuestionNumber));
            buttonFour.setText(questionsLibrary.getFourthChoice(mQuestionNumber));



            mQuestionNumber++;
        }
    }
}

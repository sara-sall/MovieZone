package com.example.moviezone;

public class QuestionLibrary {

    private String[] questionsArray = {"Average rating?",
                                        "release date?",
                                        "What genre do you want to see?"};

    private String[][] choices = {
            {"5/10","6/10","7/10","8/10"},
            {"1980", "1990", "2000", "2010"},
            {"Action", "Comedy", "Drama", "Fantasy"}
    };

    public String getQuestion (int i){
        String questions = questionsArray[i];
        return questions;
    }
    public String getFirstChoice(int i){
        String choice0 = choices[i][0];
        return choice0;
    }

    public String getSecondChoice(int i){
        String choice1 = choices[i][1];
        return choice1;
    }

    public String getThirdChoice(int i){
        String choice2 = choices[i][2];
        return choice2;
    }
    public String getFourthChoice(int i){
        String choice2 = choices[i][3];
        return choice2;
    }
}

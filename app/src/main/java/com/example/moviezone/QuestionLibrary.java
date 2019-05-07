package com.example.moviezone;

public class QuestionLibrary {

    private String[] questionsArray = {"Average rating?",
                                        "release date?",
                                        "What genre do you want to see?"};

    private String[][] choices = {
            {"5","6","7","8"},
            {"1980-01-01", "1990-01-01", "2000-01-01", "2010-01-01"},
            {"28", "35", "18", "14"}
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

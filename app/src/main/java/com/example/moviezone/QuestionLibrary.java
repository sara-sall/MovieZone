package com.example.moviezone;

public class QuestionLibrary {

    private String[] questionsArray = {"How many people are you?",
                                        "What genders are you?",
                                        "What genre do you want to see?"};

    private String[][] choices = {
            {"One","Two","Three","Group"},
            {"Males", "Females", "Both", "Large group"},
            {"Thriller", "Comedy", "Drama", "Romance"}
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

package com.example.moviezone;

public class Viewers {

    private String rating;
    private String rDate;
    private String genre;

    public String getrDate() {
        switch (this.rDate){
            case "1980":
                return "1980-01-01";

            case "1990":
                return "1990-01-01";

            case "2000":
                return "2000-01-01";

            case "2010":
                return "2010-01-01";

            default:
                return "2010-01-01";

        }
    }

    public void setDate(String rDate) {
        this.rDate = rDate;
    }

    public String getRating() {
        switch (this.rating){
            case "5/10":
                return "5";

            case "6/10":
                return "6";

            case "7/10":
                return "7";

            case "8/10":
                return "8";

            default:
                return "5";

        }
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getGenre() {
        switch (this.genre){
            case "Action":
                return "28";

            case "Comedy":
                return "35";

            case "Drama":
                return "18";

            case "Fantasy":
                return "14";

            default:
                return "35";

        }
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}

//{"5","6","7","8"},
//{"1980-01-01", "1990-01-01", "2000-01-01", "2010-01-01"},
//{"28", "35", "18", "14"}

// action, comedy, drama, fantasy.

package com.example.moviezone

data class Movie(val title: String, val year: String, var rating: Double, val poster: String)


//Kotlin har automatiskt byggt in getters och setters
//data keyworden gör så att toString(), hashcode() och equals() skapas automatiskt också
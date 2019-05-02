package com.example.moviezone

data class Movie(val title: String, val year: Int, val genre: String, var rating: Double)


//Kotlin har automatiskt byggt in getters och setters
//data keyworden gör så att toString(), hashcode() och equals() skapas automatiskt också
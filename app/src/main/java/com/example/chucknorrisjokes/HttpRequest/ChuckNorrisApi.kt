package com.example.chucknorrisjokes.HttpRequest

import com.example.chucknorrisjokes.Joke
import retrofit2.Call
import retrofit2.http.GET

interface ChuckNorrisApi {

    @GET("random")
    fun getRandomJoke(): Call<Joke>
}
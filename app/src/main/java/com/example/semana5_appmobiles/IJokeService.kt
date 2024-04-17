package com.example.semana5_appmobiles

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IJokeService {
    @GET("api")
    fun getJoke(@Query("format") format: String) : Call<Joke>
}
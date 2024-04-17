package com.example.semana5_appmobiles

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnJoke = findViewById<Button>(R.id.btnJoke)

        btnJoke.setOnClickListener {
            val tvJoke = findViewById<TextView>(R.id.tvJoke)

            // codigo de RETROFIT

            val retrofit = Retrofit.Builder()
                .baseUrl("https://geek-jokes.sameerkumar.website/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            //Crear una intancia de la interface
            val iJokeService: IJokeService
            iJokeService = retrofit.create(IJokeService::class.java)

            val request = iJokeService.getJoke("json")

            request.enqueue(object : Callback<Joke>{
                override fun onResponse(p0: Call<Joke>, p1: Response<Joke>) {
                    if (p1.isSuccessful){
                        tvJoke.text = p1.body()!!.joke
                    }
                }

                override fun onFailure(p0: Call<Joke>, p1: Throwable) {
                    //falta imple
                }
            })
        }
    }
}
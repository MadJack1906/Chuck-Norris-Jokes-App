package com.example.chucknorrisjokes

import android.app.AppOpsManager
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.chucknorrisjokes.HttpRequest.ChuckNorrisApi
import com.example.chucknorrisjokes.databinding.ActivityMainBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var chuckNorrisApi: ChuckNorrisApi
    private val imgUrl: String = "https://api.chucknorris.io/img/chucknorris_logo_coloured_small@2x.png"
    private val API_URL: String = "https://api.chucknorris.io/jokes/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootView = binding.root
        setContentView(rootView)

        chuckNorrisApi = buildChuckNorrisApi() // Retrofit API builder
        // Fetches and Renders the Image of the api site
        Glide.with(this).load(imgUrl).apply(RequestOptions.overrideOf(900, 500)).into(binding.imgvImage)

        getRandomJoke(chuckNorrisApi)

        // TODO: Add shared preference to store the joke locally, then learn retrofit!

        // Copies the joke to the clipboard
        binding.btnCopyJoke.setOnClickListener { copyJoke(it) }

        // Upon swiping the app will fetch a joke from the server
        binding.swipeRefreshLayout.setOnRefreshListener {
            getRandomJoke(chuckNorrisApi)
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun copyJoke(it: View) {
        val clipboard: ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData: ClipData = ClipData.newPlainText("Chuck Norris Joke", binding.tvJokeContainer.text.toString())
        clipboard.setPrimaryClip(clipData)
        Toast.makeText(it.context, "Copied!", Toast.LENGTH_SHORT).show()
    }

    private fun buildChuckNorrisApi(): ChuckNorrisApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(API_URL)
            .build()
            .create(ChuckNorrisApi::class.java)
    }

    private fun getRandomJoke(apiBuilder: ChuckNorrisApi) {
        val jokeData = apiBuilder.getRandomJoke()
        jokeData.enqueue(object : Callback<Joke>{
            override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                val responseBody = response.body()!!
                binding.tvJokeContainer.text = responseBody.mValue
                Log.d("HTTP RESPONSE", responseBody.mValue)
            }

            override fun onFailure(call: Call<Joke>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to fetch the joke", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
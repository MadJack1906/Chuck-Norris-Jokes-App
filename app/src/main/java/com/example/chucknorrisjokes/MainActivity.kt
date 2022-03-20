package com.example.chucknorrisjokes

import android.app.AppOpsManager
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.chucknorrisjokes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val imgUrl: String = "https://api.chucknorris.io/img/chucknorris_logo_coloured_small@2x.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootView = binding.root
        setContentView(rootView)

        Glide.with(this).load(imgUrl).apply(RequestOptions.overrideOf(900, 500)).into(binding.imgvImage)

        // TODO: Add shared preference to store the joke locally, then learn retrofit!

        binding.btnCopyJoke.setOnClickListener(View.OnClickListener {
            val clipboard: ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData: ClipData = ClipData.newPlainText("Chuck Norris Joke", binding.tvJokeContainer.text.toString())
            clipboard.setPrimaryClip(clipData)
            Toast.makeText(it.context, "Joke Copied Successfully!", Toast.LENGTH_SHORT).show()
        })

        binding.fabRefreshFacts.setOnClickListener( View.OnClickListener {
            Toast.makeText(it.context, binding.tvJokeContainer.getText().toString(), Toast.LENGTH_SHORT).show()
        })

        binding.fabRefreshFacts.setOnLongClickListener {
            Toast.makeText(it.context, "Refresh Joke", Toast.LENGTH_SHORT).show()
            return@setOnLongClickListener true
        }
    }
}
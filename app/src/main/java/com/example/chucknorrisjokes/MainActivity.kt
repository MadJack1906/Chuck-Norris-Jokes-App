package com.example.chucknorrisjokes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.chucknorrisjokes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootView = binding.root
        setContentView(rootView)

        binding.fabRefreshFacts.setOnClickListener( View.OnClickListener {
            Toast.makeText(it.context, "Hello World", Toast.LENGTH_SHORT).show()
        })

        binding.fabRefreshFacts.setOnLongClickListener {
            Toast.makeText(it.context, "Refresh Joke", Toast.LENGTH_SHORT).show()
            return@setOnLongClickListener true
        }
    }
}
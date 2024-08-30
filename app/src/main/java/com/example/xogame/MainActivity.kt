package com.example.xogame

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.xogame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startGameButton.setOnClickListener {
            onButtonClick()
        }
    }


    private fun onButtonClick() {
        val playerOne = binding.playerOneEditText.text.toString()
        val playerTwo = binding.playerTwoEditText.text.toString()
        val intent = Intent(this, Game::class.java)
        intent.putExtra("player_one", playerOne)
        intent.putExtra("player_two", playerTwo)
        startActivity(intent)
    }
}
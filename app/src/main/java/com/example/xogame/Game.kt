package com.example.xogame

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.xogame.databinding.ActivityGameBinding

@SuppressLint("SetTextI18n")
class Game : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var playerOne: String
    private lateinit var playerTwo: String
    private var boardState: ArrayList<String>? = null
    private var counter: Int = 0
    private var playerOneScore: Int = 0
    private var playerTwoScore: Int = 0
    private var playerSymbol: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        playerOne = intent.getStringExtra("player_one")!!
        playerTwo = intent.getStringExtra("player_two")!!
        binding.playerOneName.text = playerOne
        binding.playerTwoName.text = playerTwo
        initializeBoardState()
    }

    private fun initializeBoardState() {
        //first way to clear board after game
        boardState = java.util.ArrayList()
        for (i in 0..8) {
            boardState!!.add("")
        }

        binding.buttonOne.text = ""
        binding.buttonOne.setEnabled(true)
        binding.buttonTwo.text = ""
        binding.buttonTwo.setEnabled(true)
        binding.buttonThree.text = ""
        binding.buttonThree.setEnabled(true)
        binding.buttonFour.text = ""
        binding.buttonFour.setEnabled(true)
        binding.buttonFive.text = ""
        binding.buttonFive.setEnabled(true)
        binding.buttonSix.text = ""
        binding.buttonSix.setEnabled(true)
        binding.buttonSeven.text = ""
        binding.buttonSeven.setEnabled(true)
        binding.buttonEight.text = ""
        binding.buttonEight.setEnabled(true)
        binding.buttonNine.text = ""
        binding.buttonNine.setEnabled(true)


    }

    fun onPlayerClick(view: View) {
        val clickButton = view as Button

        //check player turn
        if (counter % 2 == 0) {
            clickButton.setTextColor(Color.RED)
            clickButton.text = "X"
            playerSymbol = "X"
            counter++
        } else {
            clickButton.setTextColor(Color.YELLOW)
            clickButton.text = "O"
            playerSymbol = "O"
            counter++
        }
        //write player symbol in clicked button
        writePlayerSymbolInState(clickButton.id, playerSymbol)

        //check winner
        if (checkWinner("X")) {
            playerOneScore++
            binding.playerOneScore.text = playerOneScore.toString()
            initializeBoardState()
            // we change counter to change player turn
            counter = 0
        } else if (checkWinner("O")) {
            playerTwoScore++
            binding.playerTwoScore.text = playerTwoScore.toString()
            initializeBoardState()
            counter = 1
        } else if (counter == 9) {
            initializeBoardState()
            counter = 0
        }
    }

    private fun writePlayerSymbolInState(id: Int, writeSymbol: String) {
        when (id) {
            binding.buttonOne.id -> {
                boardState!![0] = writeSymbol
                binding.buttonOne.setEnabled(false)
            }

            binding.buttonTwo.id -> {
                boardState!![1] = writeSymbol
                binding.buttonTwo.setEnabled(false)
            }

            binding.buttonThree.id -> {
                boardState!![2] = writeSymbol
                binding.buttonThree.setEnabled(false)
            }

            binding.buttonFour.id -> {
                boardState!![3] = writeSymbol
                binding.buttonFour.setEnabled(false)
            }

            binding.buttonFive.id -> {
                boardState!![4] = writeSymbol
                binding.buttonFive.setEnabled(false)
            }

            binding.buttonSix.id -> {
                boardState!![5] = writeSymbol
                binding.buttonSix.setEnabled(false)
            }

            binding.buttonSeven.id -> {
                boardState!![6] = writeSymbol
                binding.buttonSeven.setEnabled(false)
            }

            binding.buttonEight.id -> {
                boardState!![7] = writeSymbol
                binding.buttonEight.setEnabled(false)
            }

            binding.buttonNine.id -> {
                boardState!![8] = writeSymbol
                binding.buttonNine.setEnabled(false)
            }
        }
    }

    private fun checkWinner(playerCode: String): Boolean {
        /*
        //rows
        i=0     i=3     i=6
        0       , 1     , 2
        3       , 4     , 5
        6       , 7     , 8
        */

        var i = 0
        while (i < 9) {
            if (boardState!![i] == playerCode && boardState!![i + 1] == playerCode && boardState!![i + 2] == playerCode
            ) {
                return true
            }
            i += 3
        }

        /*
     //Columns
     i=0     i=1     i=2
     0       , 1     , 2
     3       , 4     , 5
     6       , 7     , 8
     */
        for (k in 0..2) {
            if (boardState!![k] == playerCode && boardState!![k + 3] == playerCode && boardState!![k + 6] == playerCode) {
                return true
            }
        }
        //diagonal
        if (boardState!![0] == playerCode && boardState!![4] == playerCode && boardState!![8] == playerCode) {
            return true
        }
        if (boardState!![2] == playerCode && boardState!![4] == playerCode && boardState!![6] == playerCode) {
            return true
        }
        return false
    }
}
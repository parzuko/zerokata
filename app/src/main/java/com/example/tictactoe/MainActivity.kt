package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var theButton = ArrayList<Button>()
    var playerNumber = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        theButton = arrayListOf(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)

        game_info.text = "Start Game!"

        btn1Player.setOnClickListener{
            playerNumber = 1
            Toast.makeText(this,"Play Single Player Mode",Toast.LENGTH_SHORT).show()
            newGame(btnReset)
        }

        btn2player.setOnClickListener{
            playerNumber = 2
            Toast.makeText(this,"Play With A Friend",Toast.LENGTH_SHORT).show()
            newGame(btnReset)
        }
    }

    fun onClickButton (view: View){

        var latest_button = view as Button
        var field_ID = 0

        when (latest_button.id){
            R.id.btn1 -> field_ID = 1
            R.id.btn2 -> field_ID = 2
            R.id.btn3 -> field_ID = 3
            R.id.btn4 -> field_ID = 4
            R.id.btn5 -> field_ID = 5
            R.id.btn6 -> field_ID = 6
            R.id.btn7 -> field_ID = 7
            R.id.btn8 -> field_ID = 8
            R.id.btn9 -> field_ID = 9
        }

        start_game(latest_button, field_ID)
    }

    var mover_1 = ArrayList<Int>()
    var mover_2 = ArrayList<Int>()
    var current_player = 1

    private fun start_game(latest_button: Button, fieldId: Int) {

        if(current_player == 1){
            latest_button.text = "X"
            mover_1.add(fieldId)
            current_player = 2

        }else{
            latest_button.text ="O"
            mover_2.add(fieldId)
            current_player = 1
        }

        when(current_player){
            1 -> game_info.text = "Current Player: X"
            2 -> game_info.text = "Current Player: O"
        }

        latest_button.isEnabled = false

        checkWinner()

        if (playerNumber == 1 && current_player == 2 && checkWinner == 0){

            Handler().postDelayed({autoPlay()},300)
        }

    }

    fun autoPlay() {

        var choosePosition = Random.nextInt(1,10)

        // if random move is same as user's previous
        while (mover_1.contains(choosePosition) || mover_2.contains(choosePosition)) {
            choosePosition = Random.nextInt(1,10)
        }

        start_game(theButton[choosePosition - 1], choosePosition)
    }

    var checkWinner = 0 // 0: no winner, 1:X,2:O

    private fun checkWinner(){

        // For Row 1
        if(mover_1.contains(1) && mover_1.contains(2) && mover_1.contains(3)) {checkWinner = 1}
        if(mover_2.contains(1) && mover_2.contains(2) && mover_2.contains(3)) {checkWinner = 2}

        // For Row 2
        if(mover_1.contains(4) && mover_1.contains(5) && mover_1.contains(6)) {checkWinner = 1}
        if(mover_2.contains(4) && mover_2.contains(5) && mover_2.contains(6)) {checkWinner = 2}

        // For Row 3
        if(mover_1.contains(7) && mover_1.contains(8) && mover_1.contains(9)) {checkWinner = 1}
        if(mover_2.contains(7) && mover_2.contains(8) && mover_2.contains(9)) {checkWinner = 2}

        // Column 1
        if(mover_1.contains(1) && mover_1.contains(4) && mover_1.contains(7)) {checkWinner = 1}
        if(mover_2.contains(1) && mover_2.contains(4) && mover_2.contains(7)) {checkWinner = 2}

        //Column 2
        if(mover_1.contains(2) && mover_1.contains(5) && mover_1.contains(8)) {checkWinner = 1}
        if(mover_2.contains(2) && mover_2.contains(5) && mover_2.contains(8)) {checkWinner = 2}

        // Column 3
        if(mover_1.contains(3) && mover_1.contains(6) && mover_1.contains(9)) {checkWinner = 1}
        if(mover_2.contains(3) && mover_2.contains(6) && mover_2.contains(9)) {checkWinner = 2}

        // Diagonal top left to bottom right
        if(mover_1.contains(1) && mover_1.contains(5) && mover_1.contains(9)) {checkWinner = 1}
        if(mover_2.contains(1) && mover_2.contains(5) && mover_2.contains(9)) {checkWinner = 2}

        // Diagonal 2
        if(mover_1.contains(3) && mover_1.contains(5) && mover_1.contains(7)) {checkWinner = 1}
        if(mover_2.contains(3) && mover_2.contains(5) && mover_2.contains(7)) {checkWinner = 2}

        if(checkWinner != 0){
            when(checkWinner){
                1 -> game_info.text = "Player X Won!!"
                2 -> game_info.text = "Player O Won!!"
            }

            for(i in 0..8){
                theButton[i].isEnabled = false
            }

            btnReset.visibility = View.VISIBLE
        }

        if(checkWinner == 0 && (mover_1.size + mover_2.size == 9)){
            game_info.text = "It's a draw :("
            btnReset.visibility = View.VISIBLE
        }
    }

    fun newGame(view: View){
        mover_1.clear()
        mover_2.clear()
        checkWinner = 0
        current_player = 1
        game_info.text = "Current Player: X"

        for(i in 0..8){
            theButton[i].text = ""
            theButton[i].isEnabled = true
        }

        btnReset.visibility = View.INVISIBLE
    }
}
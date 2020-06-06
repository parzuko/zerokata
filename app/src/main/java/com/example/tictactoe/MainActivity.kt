package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

        latest_button.isEnabled = false

        println("player_1 list: $mover_1")
        println("player_2 list: $mover_2")

    }
}
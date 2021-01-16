package com.example.projectgamescore

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.view.View
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_score_screen.*
import java.text.DecimalFormat
import java.text.NumberFormat

/**
 * Created by Roman Sulymka on січ, 2021
 */


class ScoreActivity : AppCompatActivity() {
    companion object {
        private var team1Score: Int = 0
        private var team2Score: Int = 0
    }
    private lateinit var timer: TextView
    private lateinit var team1: TextView
    private lateinit var team2: TextView
    private lateinit var tvFirsTeamRes: TextView
    private lateinit var tvSecondTeamRes: TextView
    private lateinit var tvTest: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_screen)

        team1 = findViewById(R.id.tvFirstTeam)
        team2 = findViewById(R.id.tvSecondTeam)
        tvFirsTeamRes = findViewById(R.id.tvScoreFirstTeam)
        tvSecondTeamRes = findViewById(R.id.tvScoreSecondTeam)
        tvTest = findViewById(R.id.tvTestRes)


        setupListeners()
        goToMain()
    }

    private fun setupListeners(){
        setTeamName()
        setGoal()
        cancelGame()
        setupTimer()
    }

    private fun setTeamName(){
        val intent: Intent = intent

        val name1: String? = intent.getStringExtra("team1")
        val name2: String? = intent.getStringExtra("team2")
        team1.text = name1
        team2.text = name2
    }

    private fun setGoal(){
        btn_first_team.setOnClickListener {
            tvFirsTeamRes.text = "${++team1Score}"
        }

        btn_second_team.setOnClickListener {
            tvSecondTeamRes.text = "${++team2Score}"
        }
    }

    private fun setupTimer(){
        timer = findViewById(R.id.tvTimer)
        chronometer.onTick(60000)
        chronometer.onFinish()
    }

    private var chronometer = object :   CountDownTimer(30000, 1000) {
        override fun onTick(seconds: Long) {
            timer.text = "${seconds / 1000}"
        }

        override fun onFinish() {
            timer.text = "Time is up !"
            getResult()
        }

    }.start()

    private fun cancelGame(){
        btnCancel.setOnClickListener {
            chronometer.cancel()
            timer.text = "Cancel!"

            blockButtons()
        }
    }

    private fun blockButtons(){
        if (timer.text == "Time is up !" || timer.text == "Cancel!") {
            btn_second_team?.isEnabled = false
            btn_first_team?.isEnabled = false
        }
    }

    private fun goToMain(){
        btnToMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getResult(){
        tvTest.text = "${tvFirsTeamRes.text} : ${tvSecondTeamRes.text}"
        when {
            team1Score > team2Score -> {
                Toast.makeText(this, "${team1.text} win!", Toast.LENGTH_SHORT).show()
            }
            team1Score < team2Score -> {
                Toast.makeText(this, "${team2.text} win", Toast.LENGTH_SHORT).show()
            }
            team1Score == team2Score -> {
                Toast.makeText(this, "DRAW", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

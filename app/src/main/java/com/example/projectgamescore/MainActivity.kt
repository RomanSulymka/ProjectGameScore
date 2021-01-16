package com.example.projectgamescore
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Roman Sulymka on січ, 2021
 */

class MainActivity : AppCompatActivity() {

    private lateinit var firstTeamInput: TextInputEditText
    private lateinit var secondTeamInput: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstTeamInput = findViewById(R.id.et_firstTeam)
        secondTeamInput = findViewById(R.id.et_secondTeam)

        confirmInput()
        goPlay()
    }


    private fun confirmInput() {

        btn_addTeams.setOnClickListener {
                var input = "First team: " + firstTeamInput.text.toString()
                input += "\n"
                input += "Second team: " + secondTeamInput.text.toString()

                Toast.makeText(this, input, Toast.LENGTH_SHORT).show()
            }
    }

    private fun goPlay(){

        btn_goToGame.setOnClickListener {
            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra("team1", firstTeamInput.text.toString())
            intent.putExtra("team2", secondTeamInput.text.toString())

            startActivity(intent)
        }
    }

}
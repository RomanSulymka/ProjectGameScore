package com.example.projectgamescore
import android.content.Intent
import com.google.android.material.textfield.TextInputLayout
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var firstTeamInput: TextInputEditText
    private lateinit var secondTeamInput: TextInputEditText
    public lateinit var teams: ArrayList<Map <Int, String>>
    private var counter = 0;

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
            teamInput(firstTeamInput.text.toString(), secondTeamInput.text.toString())

            var input = "First team: " + firstTeamInput.text.toString()
            input += "\n"
            input += "Second team: " + secondTeamInput.text.toString()

            Toast.makeText(this, input, Toast.LENGTH_SHORT).show()
        }
    }

    private fun teamInput(team1: String, team2: String){
        var array: MutableMap<Int, String> = mutableMapOf()
        array.put(++counter, team1)
        array.put(++counter, team2)

        println(array)
    }

    private fun goPlay(){
        btn_goToGame.setOnClickListener {
            val intent = Intent(this, ScoreActivity::class.java)
            startActivity(intent)
        }
    }
}

/**
        create array
        forEach
        added teams to array

        create map
        forEach
        put key + teams[i]
        add all data to globalArray
 **/
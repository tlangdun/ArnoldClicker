package com.example.arnoldclicker

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        loadState()
        setExit()
        resetGame()
        saveState()
    }

    fun saveState() { //TODO
        var button = findViewById<Button>(R.id.saveButton)
        button.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "State has been saved",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun loadState() {//TODO
        var button = findViewById<Button>(R.id.loadButton)
        button.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "State has been loaded",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun resetGame() {//TODO
        var button = findViewById<Button>(R.id.resetGameButton)
        button.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "Status has been reset",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun setExit() {
        var exitButton = findViewById<Button>(R.id.exitButton)
        exitButton.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}
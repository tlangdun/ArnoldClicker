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
        setupLoadButton()
        setupExitButton()
        setupSaveButton()
    }

    fun setupSaveButton() { //TODO
        var button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "State has been saved",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun setupLoadButton() {//TODO
        var button = findViewById<Button>(R.id.button3)
        button.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "State has been loaded",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun setupExitButton() {
        var exitButton = findViewById<Button>(R.id.exitButton)
        exitButton.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}
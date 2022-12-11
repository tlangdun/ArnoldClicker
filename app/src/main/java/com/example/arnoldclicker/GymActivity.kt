package com.example.arnoldclicker

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class GymActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gym)
        setGymExitButton()
    }

    fun setGymExitButton(){
        var exitButton = findViewById<ImageButton>(R.id.BakeryExitButton)
        exitButton.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}
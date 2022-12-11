package com.example.arnoldclicker

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    var arnoldData = ArnoldData()
    val upgradeRequestCode = 10
    val handler = Handler(Looper.getMainLooper())
    lateinit var timer: Runnable
    var enableGadgets = true
    val secondInMillis = 1000L
    var gTimeout = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupClickButton()
        setupUpgradeButton()
        //setupGadgetsButton()
        //setupGyms()
        initTimer()
        setupBombGadget()
        setupKeyGadget()
        setupSettingsButton()

    }

    private fun setupKeyGadget() {
        val button = findViewById<Button>(R.id.keyGadget)
        button.setOnClickListener {
            if(arnoldData.injectionGadgetActive && enableGadgets){
                var kgrnd =  (0..5).random()
                arnoldData.gainsPerSecond += kgrnd
                var kgrnd2 = (0..10).random()
                arnoldData.gymbroLevel += kgrnd2
                Toast.makeText(applicationContext, "Upgraded by ${kgrnd}\n autoclick and ${kgrnd2}\n click upgrade", Toast.LENGTH_LONG).show()
            }else if(arnoldData.injectionGadgetActive){
                Toast.makeText(applicationContext, "This gadget is on timeout!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupSettingsButton() {
        var button = findViewById<Button>(R.id.settingsButton)
        button.setOnClickListener{
            val intent = Intent(this, SettingsActivity::class.java)
            intent.putExtra("arnolddata", arnoldData)

            startActivity(intent)
        }
    }

    private fun setupBombGadget() {
        val button = findViewById<Button>(R.id.bombGadget)
        button.setOnClickListener {
            if(arnoldData.breakUpGadgetActive && enableGadgets){
                var rnd =  (80..6000).random()
                arnoldData.gainsCounter += rnd
                enableGadgets = false
            }else if(arnoldData.breakUpGadgetActive){
                Toast.makeText(applicationContext, "This gadget is on timeout!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initTimer() {
        timer = Runnable {
            gTimeout += 1
            if(gTimeout > 80)
            {
                enableGadgets = true
                gTimeout = 0
            }
            if(arnoldData.gainsCounter > 8000)
            {
                var rnds = (0..52).random()
                if(rnds == 30 || rnds == 31 || rnds == 32 || rnds == 33 || rnds == 34 || rnds == 35 || rnds == 36 || rnds == 37 || rnds == 38 || rnds == 39 || rnds == 40)
                {
                    refreshCookieView()
                    var button = findViewById<ImageButton>(R.id.cookieButton)
                    var background = findViewById<ConstraintLayout>(R.id.background)
                    arnoldData.startGymBonus(handler , button, background)
                }
            }
            arnoldData.gainsCounter += arnoldData.gainsPerSecond
            refreshCookieView()
            handler.postDelayed(timer, secondInMillis)
        }
        handler.postDelayed(timer, secondInMillis)
    }

    /*
    private fun setupGyms() {
        val button = findViewById<Button>(R.id.upgradeButton4)
        button.setOnClickListener {
            val intent = Intent(this, GymActivity::class.java)
            startActivity(intent)
        }
    }
     */
/*
    private fun setupGadgetsButton() {
        val button = findViewById<Button>(R.id.upgradeButton3)
        button.setOnClickListener {
            val intent = Intent(this, GadgetsActivity::class.java)
            startActivity(intent)
        }
    }

 */

    private fun setupUpgradeButton() {
        val button = findViewById<Button>(R.id.upgradeButton)
        button.setOnClickListener {
            val intent = Intent(this, UpgradesActivity::class.java)
            intent.putExtra("arnolddata", arnoldData)
            startActivityForResult(intent, upgradeRequestCode)
        }
    }

    private fun setupClickButton() {
        val button = findViewById<ImageButton>(R.id.cookieButton)
        button.setOnClickListener {
            arnoldData.gainsCounter += arnoldData.clickValue
            refreshCookieView()
        }
    }

    private fun refreshCookieView() {
        val gainsTextView: TextView = findViewById(R.id.gainsTextArea)
        gainsTextView.text = "\uD83D\uDCAA Gains : ${arnoldData.gainsCounter}"
    }

    fun refreshGadgets() {
        if(arnoldData.breakUpGadgetActive)
        {
            val button = findViewById<Button>(R.id.bombGadget)
            button.visibility = View.VISIBLE
        }else{
            val button = findViewById<Button>(R.id.bombGadget)
            button.visibility = View.INVISIBLE
        }
        if(arnoldData.injectionGadgetActive)
        {
            val button = findViewById<Button>(R.id.keyGadget)
            button.visibility = View.VISIBLE
        }else{
            val button = findViewById<Button>(R.id.keyGadget)
            button.visibility = View.INVISIBLE
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == upgradeRequestCode && resultCode == Activity.RESULT_OK) {
            arnoldData = data!!.getSerializableExtra("cookieData") as ArnoldData
            refreshCookieView()
            var button = findViewById<ImageButton>(R.id.cookieButton)
            var background = findViewById<ConstraintLayout>(R.id.background)
            //arnoldData.startBakeryBonus(handler , button, background)
            refreshGadgets()
        }
    }
}

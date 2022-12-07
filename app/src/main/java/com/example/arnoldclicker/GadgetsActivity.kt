package com.example.arnoldclicker

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class GadgetsActivity : AppCompatActivity() {
    var cookieData = ArnoldData()
    lateinit var breakUpCostTextView: TextView
    lateinit var injectionTextView: TextView
    lateinit var healingTextView: TextView
    lateinit var fitnessTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gadgets)
        setGadgetsExitButton()
        setBuyButtonBreakUp()
        refreshTextViews()
        setBuyButtonSteroid()
        setBuyButtonHealing()
        setBuyButtonFitness()
        breakUpCostTextView = findViewById(R.id.breakupprice)
        injectionTextView = findViewById(R.id.injectionprice)
        healingTextView = findViewById(R.id.healingprice)
        fitnessTextView = findViewById(R.id.fitnessprice)
    }

    fun refreshTextViews(){
        if(cookieData.breakUpGadgetActive)
        {
            breakUpCostTextView.text = "Already purchased"
        }
        if(cookieData.injectionGadgetActive)
        {
            injectionTextView.text = "Already purchased"
        }
        if(cookieData.healingGadgetActive)
        {
            healingTextView.text = "Already purchased"
        }
        if(cookieData.fitnessGadgetActive)
        {
            fitnessTextView.text = "Already purchased"
        }
    }
    fun setGadgetsExitButton(){
        var exitButton = findViewById<Button>(R.id.GadgetsExitButton)
        exitButton.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    fun setBuyButtonBreakUp(){
        var button = findViewById<Button>(R.id.BreakUpButton)
        button.setOnClickListener {
            if(!cookieData.breakUpGadgetActive){
                if(cookieData.gainsCounter >= 70000) {
                    cookieData.breakUpGadgetActive = true
                    cookieData.gainsCounter -= 70000
                    refreshTextViews()
                }else{
                    Toast.makeText(applicationContext, "You cannot buy this gadget", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(applicationContext, "You already own this gadget", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun setBuyButtonSteroid(){
        var button = findViewById<Button>(R.id.SteroidButton)
        button.setOnClickListener {
            if(!cookieData.injectionGadgetActive) {
                if (cookieData.gainsCounter >= 90000) {
                    cookieData.injectionGadgetActive = true
                    cookieData.gainsCounter -= 90000
                    refreshTextViews()
                }else{
                    Toast.makeText(applicationContext, "You cannot buy this gadget", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(applicationContext, "You already own this gadget", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun setBuyButtonHealing(){
        var button = findViewById<Button>(R.id.HealingButton)
        button.setOnClickListener {
            if(!cookieData.injectionGadgetActive) {
                if (cookieData.gainsCounter >= 120000) {
                    cookieData.healingGadgetActive = true
                    cookieData.gainsCounter -= 120000
                    refreshTextViews()
                }else{
                    Toast.makeText(applicationContext, "You cannot buy this gadget", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(applicationContext, "You already own this gadget", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun setBuyButtonFitness(){
        var button = findViewById<Button>(R.id.FitnessButton)
        button.setOnClickListener {
            if(!cookieData.injectionGadgetActive) {
                if (cookieData.gainsCounter >= 200000) {
                    cookieData.fitnessGadgetActive = true
                    cookieData.gainsCounter -= 200000
                    refreshTextViews()
                }else{
                    Toast.makeText(applicationContext, "You cannot buy this gadget", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(applicationContext, "You already own this gadget", Toast.LENGTH_LONG).show()
            }
        }
    }
}
package com.example.arnoldclicker

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class GadgetsActivity : AppCompatActivity() {


     var data = ArnoldData()
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
        if(data.breakUpGadgetActive)
        {
            breakUpCostTextView.text = "Already purchased"
        }
        if(data.injectionGadgetActive)
        {
            injectionTextView.text = "Already purchased"
        }
        if(data.healingGadgetActive)
        {
            healingTextView.text = "Already purchased"
        }
        if(data.fitnessGadgetActive)
        {
            fitnessTextView.text = "Already purchased"
        }
    }
    fun setGadgetsExitButton(){
        var exitButton = findViewById<Button>(R.id.GadgetsExitButton)
        exitButton.setOnClickListener {
            var returnIntent = Intent()
            returnIntent.putExtra("arnolddata", data)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }

    fun setBuyButtonBreakUp(){
        var button = findViewById<Button>(R.id.BreakUpButton)
        button.setOnClickListener {
            if(!data.breakUpGadgetActive){
                if(data.gainsCounter >= 70000) {
                    data.breakUpGadgetActive = true
                    data.gainsCounter -= 70000
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
            if(!data.injectionGadgetActive) {
                if (data.gainsCounter >= 90000) {
                    data.injectionGadgetActive = true
                    data.gainsCounter -= 90000
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
            if(!data.injectionGadgetActive) {
                if (data.gainsCounter >= 120000) {
                    data.healingGadgetActive = true
                    data.gainsCounter -= 120000
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
            if(!data.injectionGadgetActive) {
                if (data.gainsCounter >= 200000) {
                    data.fitnessGadgetActive = true
                    data.gainsCounter -= 200000
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
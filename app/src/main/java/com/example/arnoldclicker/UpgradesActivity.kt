package com.example.arnoldclicker

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class UpgradesActivity : AppCompatActivity(){
    var data = ArnoldData()
    lateinit var proteinTextView: TextView
    lateinit var creatineTextView: TextView
    lateinit var preworkoutTextView: TextView
    lateinit var gymbroTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upgrades)
        data = intent.getSerializableExtra("arnolddata") as ArnoldData
        Log.i("App", data.toString())
        proteinTextView = findViewById(R.id.proteinText)
        creatineTextView = findViewById(R.id.creatineText)
        preworkoutTextView = findViewById(R.id.preWorkoutText)
        gymbroTextView = findViewById(R.id.gymBroText)
        refreshTextViews()
        setExitButton()
        initClickUpgradeButton()
        handleAutoClickerUpgrade()
    }
    @SuppressLint("SetTextI18n")
    fun refreshTextViews(){
        proteinTextView.text = "Protein: ${data.proteinLevel}\n" + "Upgrade cost: ${data.proteinPrice}"
        creatineTextView.text = "Creatine: ${data.creatineLevel}\nUpgrade cost: ${data.creatinePrice}"
        preworkoutTextView.text = "Preworkout: ${data.preworkoutLevel}\nUpgrade cost: ${data.preWorkoutPrice}"
        gymbroTextView.text = "Gym bro: ${data.gymbroLevel}\nUpgrade cost: ${data.gymBroPrice()}"
    }

    fun setExitButton(){
        var exitButton = findViewById<Button>(R.id.upgradeExitButton)
        exitButton.setOnClickListener {
            var returnIntent = Intent()
            returnIntent.putExtra("cookieData", data)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }

    fun initClickUpgradeButton(){
        val clickUpgradeButton = findViewById<Button>(R.id.gymBroUpgradeButton)
        clickUpgradeButton.setOnClickListener{
            if( data.gainsCounter>= data.gymBroPrice()){
                data.gainsCounter -= data.gymBroPrice()
                data.gymbroLevel += 1
                data.calculateClickValue()
                refreshTextViews()
            }else{
                Toast.makeText(applicationContext, "You cannot buy this upgrade!", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun handleAutoClickerUpgrade() {
        findViewById<Button>(R.id.proteinUpgradeButton).setOnClickListener {
            if (data.gainsCounter >= data.proteinPrice) {
                data.updateProtein()
                refreshTextViews()
            } else {
                Toast.makeText(
                    applicationContext,
                    "You cannot buy this upgrade!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        findViewById<Button>(R.id.ceratineUpgradeButton).setOnClickListener {
            if (data.gainsCounter >= data.creatinePrice) {
                data.updateCreatine()
                refreshTextViews()
            } else {
                Toast.makeText(applicationContext, "You cannot buy this upgrade!", Toast.LENGTH_LONG).show()
            }
        }

        findViewById<Button>(R.id.preWorkoutButton).setOnClickListener {
            if (data.gainsCounter >= data.preWorkoutPrice) {
                data.updatePreworkout()
                refreshTextViews()
            } else {
                Toast.makeText(applicationContext, "You cannot buy this upgrade!", Toast.LENGTH_LONG).show()
            }
        }
    }

}
package com.example.arnoldclicker

import android.graphics.Color
import android.os.Handler
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.math.pow

class ArnoldData: java.io.Serializable{
    var gainsCounter: Long = 0
    var clickValue: Long = 1
    var gainsPerSecond: Long = 0
    var clickUpgradeLevel: Long = 1
    var autoClickerUpgradeLevel: Long = 0
    var workersUpgradeLevel: Long = 0
    var gymUpgradeLevel: Long = 0
    private val clickUpgradeStartPrice: Long = 20
    private val priceIncreaseCoef: Double = 2.5
    private val increaseUpgradeValue: Double = 2.0
    private val autoClickerPriceCoef: Double = 5.0 / 4.0
    private val autoclickValue = 1
    private val workerValue = 4
    private val gymValue = 10
    var autoClickerPrice = 500
    var workersPrice = 2000
    var gymPrice = 5000
    var breakUpGadgetActive = false
    var injectionGadgetActive = false
    var healingGadgetActive = false
    var fitnessGadgetActive = false
    private var newGym = false
    private val gymBonusCoef = 6

    fun getKeyPrice(): Long {
        var upgValue = 90000
        return upgValue.toLong()
    }

    fun getBombPrice(): Long {
        var upgValue = 70000
        return upgValue.toLong()
    }


    override fun toString(): String {
        var buffer = "{ cookiesCounter: $gainsCounter, "
        buffer += "clickValue: $clickValue, "
        buffer += "cookiesPerSecond $gainsPerSecond, "
        buffer += "clickUpgradeLevel: $clickUpgradeLevel, "
        buffer += "autoClickerUpgradeLevel: $autoClickerUpgradeLevel }"
        return buffer
    }

    fun getClickUpgPrice(): Long {
        var upgradeValue =
            clickUpgradeStartPrice * priceIncreaseCoef.pow((clickUpgradeLevel - 1).toDouble())
        return upgradeValue.toLong()
    }

    fun calculateClickValue() {
        clickValue *= 2
    }
    fun updateAutoClicker() {
        gainsCounter -= autoClickerPrice
        var tmp = autoClickerPrice.toDouble()
        tmp *= autoClickerPriceCoef
        autoClickerPrice = tmp.toInt()
        autoClickerUpgradeLevel++
        gainsPerSecond += autoclickValue
    }

    fun updateWorkers() {
        gainsCounter -= workersPrice
        var tmp = workersPrice.toDouble()
        tmp *= autoClickerPriceCoef
        workersPrice = tmp.toInt()
        workersUpgradeLevel++
        gainsPerSecond += workerValue
    }

    fun updateGym() {
        gainsCounter -= gymPrice
        var tmp = gymPrice.toDouble()
        tmp *= autoClickerPriceCoef
        gymPrice = tmp.toInt()
        gymUpgradeLevel++
        gainsPerSecond += gymValue
        newGym = true
    }

    fun startGymBonus(handler: Handler, button: ImageButton, background: ConstraintLayout) {
        if (newGym) {
            gainsPerSecond *= gymBonusCoef
            clickValue *= gymBonusCoef
            button.setImageResource(R.drawable.goldencookie)
            background.setBackgroundColor(Color.rgb(233, 154, 36))
            var bonusTimer = Runnable {
                gainsPerSecond /= gymBonusCoef
                clickValue /= gymBonusCoef
                button.setImageResource(R.drawable.arnold)
                background.setBackgroundColor(Color.rgb( 34, 31, 27))
            }
            handler.postDelayed(bonusTimer, 30000)
        }
        newGym = false
    }
}
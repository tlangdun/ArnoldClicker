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
    var gymbroLevel: Long = 0
    var proteinLevel: Long = 0
    var creatineLevel: Long = 0
    var preworkoutLevel: Long = 0
    private val clickUpgradeStartPrice: Long = 20
    private val priceIncreaseCoef: Double = 2.5
    private val increaseUpgradeValue: Double = 2.0
    private val autoClickerPriceCoef: Double = 5.0 / 4.0
    private val autoclickValue = 1
    private val workerValue = 4
    private val gymValue = 10
    var proteinPrice = 100
    var creatinePrice = 100
    var preWorkoutPrice = 100
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
        buffer += "clickUpgradeLevel: $gymbroLevel, "
        buffer += "autoClickerUpgradeLevel: $proteinLevel }"
        return buffer
    }

    fun gymBroPrice(): Long {
        var upgradeValue =
            clickUpgradeStartPrice * priceIncreaseCoef.pow((gymbroLevel - 1).toDouble())
        return upgradeValue.toLong()
    }

    fun calculateClickValue() {
        clickValue *= 2
    }
    fun updateProtein() {
        gainsCounter -= proteinPrice
        var tmp = proteinPrice.toDouble()
        tmp *= autoClickerPriceCoef
        proteinPrice = tmp.toInt()
        proteinLevel++
        gainsPerSecond += autoclickValue
    }

    fun updateCreatine() {
        gainsCounter -= creatinePrice
        var tmp = creatinePrice.toDouble()
        tmp *= autoClickerPriceCoef
        creatinePrice = tmp.toInt()
        creatineLevel++
        gainsPerSecond += workerValue
    }

    fun updatePreworkout() {
        gainsCounter -= preWorkoutPrice
        var tmp = preWorkoutPrice.toDouble()
        tmp *= autoClickerPriceCoef
        preWorkoutPrice = tmp.toInt()
        preworkoutLevel++
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
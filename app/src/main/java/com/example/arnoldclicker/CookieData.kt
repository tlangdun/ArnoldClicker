package com.example.arnoldclicker

class CookieData: java.io.Serializable{
    var cookiesCounter: Long = 0
    var clickValue: Long = 1
    var cookiesPerSecond: Long = 0
    var clickUpgradeLevel: Long = 1
    var autoClickerUpgradeLevel: Long = 0
    var workersUpgradeLevel: Long = 0
    var bakeriesUpgradeLevel: Long = 0
    private val clickUpgradeStartPrice: Long = 20
    private val priceIncreaseCoef: Double = 2.5
    private val increaseUpgradeValue: Double = 2.0
    private val autoClickerPriceCoef: Double = 5.0 / 4.0
    private val autoclickValue = 1
    private val workerValue = 4
    private val bakeriesValue = 10
    var autoClickerPrice = 500
    var workersPrice = 2000
    var bakeriesPrice = 5000
    var BombGadgetActive = false
    var KeyGadgetActive = false
    private var NewBakery = false
    private val BakeryBonusCoef = 6
}
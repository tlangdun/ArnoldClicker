package com.example.arnoldclicker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson


class SettingsActivity : AppCompatActivity() {


    var data = ArnoldData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        data = intent.getSerializableExtra("arnolddata") as ArnoldData
        Log.i("App", data.toString())

        loadState()
        setExit()
        saveState()
    }

    fun saveState() {
        var button = findViewById<Button>(R.id.saveButton)
        button.setOnClickListener {

            var mPrefs = getPreferences(MODE_PRIVATE)
            val prefsEditor = mPrefs.edit()
            val gson = Gson()
            val json = gson.toJson(data)
            prefsEditor.putString("data", json)
            prefsEditor.apply()


            Toast.makeText(
                applicationContext,
                "State has been saved",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun loadState() {
        var button = findViewById<Button>(R.id.loadButton)
        button.setOnClickListener {
            var mPrefs = getPreferences(MODE_PRIVATE)
            val gson = Gson()
            val json = mPrefs.getString("data", "")

            val obj: ArnoldData = gson.fromJson(json, ArnoldData::class.java)
            println(obj.gainsCounter)
            data = obj;
            Toast.makeText(
                applicationContext,
                "State has been loaded",
                Toast.LENGTH_LONG
            ).show()
        }
    }


    fun setExit() {
        var exitButton = findViewById<Button>(R.id.exitButton)
        exitButton.setOnClickListener {
            var returnIntent = Intent()
            returnIntent.putExtra("arnolddata", data)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }
}
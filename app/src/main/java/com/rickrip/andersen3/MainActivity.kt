package com.rickrip.andersen3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    companion object {
        const val LOG_TAG = "'"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) //disable night theme

        val buttonTaskOne = findViewById<Button>(R.id.button_task_1)
        val buttonTaskTwo = findViewById<Button>(R.id.button_task_2)

        buttonTaskOne.setOnClickListener { view ->
            switchToTask(1)
        }

        buttonTaskTwo.setOnClickListener { view ->
            switchToTask(2)
        }

    }

    private fun myStartActivity(context: Context, cls: Class<*>) {
        val intent = Intent(context, cls)
        startActivity(intent)
        finish()
    }

    private fun switchToTask(i: Int) {

        when (i) {
            1 -> {
                myStartActivity(this, Task1::class.java)
            }
            2 -> {
                myStartActivity(this, Task2::class.java)
            }
            else ->{
                myStartActivity(this, Task1::class.java)
            }
        }

    }

}
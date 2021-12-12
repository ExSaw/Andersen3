package com.rickrip.andersen3

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.InputStream
import java.net.URL
import java.net.URLConnection
import kotlin.concurrent.thread

class Task2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task2)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) //disable night theme

        val myEditText = findViewById<EditText>(R.id.editText)
        val myImageView = findViewById<ImageView>(R.id.imageView)
        val buttonFab = findViewById<FloatingActionButton>(R.id.button_fab)

        buttonFab.setOnClickListener{view->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        myEditText.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            when {
                ((keyCode == KeyEvent.KEYCODE_ENTER) && (event.action == KeyEvent.ACTION_DOWN)) -> {

                    // Toast.makeText(this,"Loading",Toast.LENGTH_SHORT).show()
                    val uiHandler = Handler(Looper.getMainLooper())

                    thread(start = true) {
                        Log.i(MainActivity.LOG_TAG, "Cur thread: ${Thread.currentThread().name}")

                        val bitmap: Bitmap? = loadImageFromServer(myEditText.text.toString())

                        uiHandler.post {
                            Log.i(
                                MainActivity.LOG_TAG,
                                "Cur thread in the YI handler: ${Thread.currentThread().name}"
                            )
                            if(bitmap!=null){
                                myImageView.setImageBitmap(bitmap)
                            }else{
                                Toast.makeText(this, "Something Wrong!", Toast.LENGTH_SHORT).show()
                                myImageView.setImageResource(R.drawable.resource_default)
                            }

                        }

                    }

                    return@OnKeyListener true
                }
                else -> false
            }
        })

    }

    fun loadImageFromServer(str: String): Bitmap? {
        return try {

            val connection: URLConnection = URL(str).openConnection()
            connection.connect()
            val inputStream: InputStream = connection.getInputStream()
            val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
            bitmap

        } catch (e: Exception) {
            Log.i(MainActivity.LOG_TAG, "Exception $e")
            null
        }

    }


}
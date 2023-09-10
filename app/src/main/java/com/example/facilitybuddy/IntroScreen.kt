package com.example.facilitybuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class IntroScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_screen)

        val startButton: Button =findViewById(R.id.btn_getStarted)

        startButton.setOnClickListener {

            val intent= Intent(this, LoginScreen::class.java)
            startActivity(intent)
        }
    } }
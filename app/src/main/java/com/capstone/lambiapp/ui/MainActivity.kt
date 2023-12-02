package com.capstone.lambiapp.ui

import android.graphics.BitmapFactory
import android.os.Bundle
import android.service.autofill.TextValueSanitizer
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.capstone.lambiapp.R
import org.michaelevans.colorart.library.ColorArt


class MainActivity : AppCompatActivity() {
    private lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv = findViewById(R.id.tvmain)
        var bitmap = BitmapFactory.decodeResource(resources,R.drawable.loginlogo)
        var colorArt = ColorArt(bitmap)
        
        tv.text = colorArt.primaryColor.toString()
        
    }
}
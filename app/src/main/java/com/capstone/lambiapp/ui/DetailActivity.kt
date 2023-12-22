package com.capstone.lambiapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.lambiapp.R
import com.capstone.lambiapp.data.response.DataItem
import com.capstone.lambiapp.databinding.ActivityDetailBinding

import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recommendation: DataItem? = intent.getParcelableExtra("recommendation")

        binding.textViewMessage.text = recommendation?.message
        val sliderView = findViewById<SliderView>(R.id.imageSlider)
        val adapter = SliderAdapter()

        // Add top and bottom images to the slider
        recommendation?.let {
            adapter.addItem(it.top?.image)
            adapter.addItem(it.bottom?.image)
        }

        sliderView.setSliderAdapter(adapter)
    }
}
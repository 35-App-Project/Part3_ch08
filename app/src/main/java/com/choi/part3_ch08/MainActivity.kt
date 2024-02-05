package com.choi.part3_ch08

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.choi.part3_ch08.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
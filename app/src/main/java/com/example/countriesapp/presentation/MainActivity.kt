package com.example.countriesapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.countriesapp.R
import com.example.countriesapp.presentation.screens.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpMainFragment()
    }


    private fun setUpMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, MainFragment())
            .commit()
    }
}
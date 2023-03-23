package com.example.countriesapp

import android.app.Application
import com.example.countriesapp.di.DaggerApplicationComponent


class CountriesApp: Application() {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}
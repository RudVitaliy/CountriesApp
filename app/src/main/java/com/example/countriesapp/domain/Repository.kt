package com.example.countriesapp.domain

import androidx.lifecycle.LiveData
import com.example.countriesapp.domain.entities.Country

interface Repository {

    fun getListOfCountries(): LiveData<List<Country>>

    suspend fun addCountry(country: Country)

    suspend fun deleteCountry(country: Country)

    suspend fun loadData(countryName: String?): Country?

}
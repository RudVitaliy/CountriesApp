package com.example.countriesapp.domain.usecases

import com.example.countriesapp.domain.Repository
import com.example.countriesapp.domain.entities.Country
import javax.inject.Inject

class AddCountryUseCase @Inject constructor(private val repository: Repository) {

    suspend fun addCountry(country: Country) {
        repository.addCountry(country)
    }

}
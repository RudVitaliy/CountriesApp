package com.example.countriesapp.domain.usecases

import com.example.countriesapp.domain.Repository
import com.example.countriesapp.domain.entities.Country
import javax.inject.Inject

class DeleteCountryUseCase @Inject constructor(private val repository: Repository) {

    suspend fun deleteCountry(country: Country) {
        repository.deleteCountry(country)
    }

}
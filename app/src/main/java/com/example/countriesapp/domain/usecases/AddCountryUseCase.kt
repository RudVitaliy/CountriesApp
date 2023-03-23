package com.example.countriesapp.domain.usecases

import com.example.countriesapp.data.repository.RepositoryImpl
import com.example.countriesapp.domain.entities.Country
import javax.inject.Inject

class AddCountryUseCase @Inject constructor(private val repositoryImpl: RepositoryImpl) {

    suspend fun addCountry(country: Country) {
        repositoryImpl.addCountry(country)
    }

}
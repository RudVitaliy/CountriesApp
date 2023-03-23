package com.example.countriesapp.domain.usecases

import com.example.countriesapp.data.repository.RepositoryImpl
import com.example.countriesapp.domain.entities.Country
import javax.inject.Inject

class DeleteCountryUseCase @Inject constructor(private val repositoryImpl: RepositoryImpl) {

    suspend fun deleteCountry(country: Country) {
        repositoryImpl.deleteCountry(country)
    }

}
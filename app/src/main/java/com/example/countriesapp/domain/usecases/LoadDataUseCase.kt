package com.example.countriesapp.domain.usecases

import com.example.countriesapp.domain.Repository
import com.example.countriesapp.domain.entities.Country
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(private val repository: Repository) {

    suspend fun loadData(countryName: String?): Country? {
        return repository.loadData(countryName)
    }

}
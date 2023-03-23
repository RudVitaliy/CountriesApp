package com.example.countriesapp.domain.usecases

import com.example.countriesapp.data.repository.RepositoryImpl
import com.example.countriesapp.domain.entities.Country
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(private val repositoryImpl: RepositoryImpl) {

    suspend fun loadData(countryName: String?): Country? {
        return repositoryImpl.loadData(countryName)
    }

}
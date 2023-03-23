package com.example.countriesapp.domain.usecases

import androidx.lifecycle.LiveData
import com.example.countriesapp.data.repository.RepositoryImpl
import com.example.countriesapp.domain.entities.Country
import javax.inject.Inject

class GetListOfCountriesUseCase @Inject constructor(private val repositoryImpl: RepositoryImpl) {

    fun getListOfCountries(): LiveData<List<Country>> {
        return repositoryImpl.getListOfCountries()
    }

}
package com.example.countriesapp.domain.usecases

import androidx.lifecycle.LiveData
import com.example.countriesapp.domain.Repository
import com.example.countriesapp.domain.entities.Country
import javax.inject.Inject

class GetListOfCountriesUseCase @Inject constructor(private val repository: Repository) {

    fun getListOfCountries(): LiveData<List<Country>> {
        return repository.getListOfCountries()
    }

}
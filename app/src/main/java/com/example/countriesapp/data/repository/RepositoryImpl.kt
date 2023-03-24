package com.example.countriesapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.countriesapp.data.database.CountriesDao
import com.example.countriesapp.data.mapper.CountryMapper
import com.example.countriesapp.data.networking.RestCountriesApi
import com.example.countriesapp.domain.Repository
import com.example.countriesapp.domain.entities.Country
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val dao: CountriesDao,
    private val mapper: CountryMapper,
    private val restCountriesApi: RestCountriesApi
): Repository {



    override fun getListOfCountries(): LiveData<List<Country>> = Transformations.map(
        dao.getList()
    ) {
        mapper.mapListDbModelToListEntity(it)
    }

    override suspend fun loadData(countryName: String?): Country? {
        var countries: List<Country>? = null
        try {
            countries = countryName?.let { restCountriesApi.getCountryByName(it) }
        } catch (_: Exception) {
        }
        return countries?.get(0)
    }

    override suspend fun addCountry(country: Country) {
        dao.addCountry(mapper.mapEntityToDbModel(country))
    }

    override suspend fun deleteCountry(country: Country) {
        dao.deleteCountry(country.id)
    }
}
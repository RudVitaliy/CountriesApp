package com.example.countriesapp.data.mapper

import com.example.countriesapp.data.database.CountryDbModel
import com.example.countriesapp.domain.entities.Country
import javax.inject.Inject

class CountryMapper @Inject constructor(){

    private fun mapDbModelToEntity(dbModel: CountryDbModel) = Country(
        id = dbModel.id,
        name = dbModel.name,
        capital = dbModel.capital,
        population = dbModel.population,
        area = dbModel.area,
        flags = dbModel.flags
    )

    fun mapEntityToDbModel(country: Country) = CountryDbModel(
        id = country.id,
        population = country.population,
        area = country.area,
        name = country.name,
        flags = country.flags,
        capital = country.capital
    )

    fun mapListDbModelToListEntity(list: List<CountryDbModel>) = list.map {
        mapDbModelToEntity(it)
    }


}
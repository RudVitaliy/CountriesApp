package com.example.countriesapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface CountriesDao {

    @Query("SELECT * FROM counties_list")
    fun getList(): LiveData<List<CountryDbModel>>

    @Insert(onConflict = REPLACE)
    suspend fun addCountry(country: CountryDbModel)

    @Query("SELECT * FROM counties_list WHERE ID = (SELECT MAX(ID)  FROM counties_list)")
    suspend fun getLastAddedCountry(): CountryDbModel

    @Query("DELETE FROM counties_list WHERE id=:countryId")
    suspend fun deleteCountry(countryId: Int)

}
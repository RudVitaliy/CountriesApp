package com.example.countriesapp.di

import android.app.Application
import com.example.countriesapp.data.database.AppDatabase
import com.example.countriesapp.data.database.CountriesDao
import com.example.countriesapp.data.networking.RestCountriesApi
import com.example.countriesapp.data.networking.restCountriesApi
import com.example.countriesapp.data.repository.RepositoryImpl
import com.example.countriesapp.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindRepository(impl: RepositoryImpl): Repository

    companion object {

        @ApplicationScope
        @Provides
        fun provideDao(
            application: Application
        ): CountriesDao {
            return AppDatabase.getInstance(application).getCountriesDao()
        }

        @ApplicationScope
        @Provides
        fun provideRestCountriesApi(): RestCountriesApi {
            return restCountriesApi
        }
    }
}

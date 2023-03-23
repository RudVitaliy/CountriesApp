package com.example.countriesapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countriesapp.presentation.Error
import com.example.countriesapp.domain.entities.Country
import com.example.countriesapp.domain.usecases.AddCountryUseCase
import com.example.countriesapp.domain.usecases.DeleteCountryUseCase
import com.example.countriesapp.domain.usecases.GetListOfCountriesUseCase
import com.example.countriesapp.domain.usecases.LoadDataUseCase
import com.example.countriesapp.presentation.CountryResult
import com.example.countriesapp.presentation.Progress
import com.example.countriesapp.presentation.State
import kotlinx.coroutines.*
import java.text.NumberFormat
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getListOfCountriesUseCase: GetListOfCountriesUseCase,
    private val loadDataUseCase: LoadDataUseCase,
    private val addCountryUseCase: AddCountryUseCase,
    private val deleteCountryUseCase: DeleteCountryUseCase
): ViewModel() {

    private val scope = CoroutineScope(Dispatchers.Default)

    val list: LiveData<List<Country>> = getListOfCountriesUseCase.getListOfCountries()

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
    get() = _state


    fun loadData(countryName: String?) {
        _state.value = Progress
        if (countryName.isNullOrBlank()) {
            _state.value = Error
            return
        }
        scope.launch {
            val result = withContext(Dispatchers.Default) {
                loadDataUseCase.loadData(countryName)
            }
            if (result != null) {
                _state.postValue(
                    CountryResult(
                        name = result.name.common,
                        area = formatNumber(result.area),
                        population = formatNumber(result.population),
                        pngOfFlag = result.flags.png,
                        capital = result.capital[0]
                    )
                )
                addCountryUseCase.addCountry(
                    Country(
                        name = result.name,
                        area = result.area,
                        population = result.population,
                        flags = result.flags,
                        capital = result.capital
                    )
                )
            } else {
                _state.postValue(
                    Error
                )
            }
        }
    }

    private fun formatNumber(number: Long): String {
        return NumberFormat.getInstance().format(number)
    }


    fun deleteCountry(country: Country) {
        scope.launch {
            deleteCountryUseCase.deleteCountry(country)
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }

}
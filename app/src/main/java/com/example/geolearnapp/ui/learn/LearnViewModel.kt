package com.example.geolearnapp.ui.learn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geolearnapp.data.database.entity.Country
import com.example.geolearnapp.data.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LearnViewModel @Inject constructor(
    private val countryRepository: CountryRepository
) : ViewModel() {

    private val _state = MutableStateFlow(emptyList<Country>())
    val state: StateFlow<List<Country>>
        get() = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {

            var countriesFromDB = countryRepository.getCountriesFromDB()

            if (countriesFromDB.isEmpty()) {

                val countriesFromApi = countryRepository.getCountries()
                _state.value = countriesFromApi
                countryRepository.insertCountries(countriesFromApi)
            } else{

                countriesFromDB = countryRepository.getCountriesFromDB()
                _state.value = countriesFromDB
            }
        }
    }
}
package com.example.geolearnapp.ui.learn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geolearnapp.data.database.entity.Country
import com.example.geolearnapp.domain.repository.CountryRepository
import com.example.geolearnapp.domain.use_case.CountryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LearnViewModel @Inject constructor(
    private val countryUseCases: CountryUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(emptyList<Country>())
    val state: StateFlow<List<Country>>
        get() = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {

            var countriesFromDB = countryUseCases.getCountriesFromDB()

            if (countriesFromDB.isEmpty()) {

                val countriesFromApi = countryUseCases.getCountries()
                _state.value = countriesFromApi
                countryUseCases.insertCountries(countriesFromApi)
            } else{

                countriesFromDB = countryUseCases.getCountriesFromDB()
                _state.value = countriesFromDB
            }
        }
    }
}
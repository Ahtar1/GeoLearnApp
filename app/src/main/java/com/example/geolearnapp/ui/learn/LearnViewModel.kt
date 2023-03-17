package com.example.geolearnapp.ui.learn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geolearnapp.data.api.model.Data
import com.example.geolearnapp.data.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LearnViewModel @Inject constructor(
    private val countryRepository: CountryRepository
) : ViewModel(){

    private val _state = MutableStateFlow(emptyList<Data>())
    val state: StateFlow<List<Data>>
        get() = _state

    init {
        viewModelScope.launch {
            val countries = countryRepository.getCountries()
            _state.value = countries
        }
    }
}
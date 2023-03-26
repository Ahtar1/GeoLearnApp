package com.example.geolearnapp.ui.quiz.written

import androidx.lifecycle.ViewModel
import com.example.geolearnapp.data.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WrittenViewModel @Inject constructor(
    val countryRepository: CountryRepository
): ViewModel() {

    init {

    }

}
package com.example.geolearnapp.ui.quiz.written

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.geolearnapp.data.database.entity.Country
import com.example.geolearnapp.data.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class WrittenViewModel @Inject constructor(
    val countryRepository: CountryRepository
): ViewModel() {

    private val _countriesState = MutableStateFlow(listOf<Country>())
    val countriesState = _countriesState.asStateFlow()

    private val _chosenCountryState = MutableStateFlow(Country("", "", "", ""))
    val chosenCountryState = _chosenCountryState.asStateFlow()

    private val _isAnswerCorrectState = MutableStateFlow<Boolean?>(null)
    val isAnswerCorrectState = _isAnswerCorrectState.asStateFlow()

    private val _wrongAnswerState = MutableStateFlow(0)
    val wrongAnswerState = _wrongAnswerState.asStateFlow()

    private val _isGameFinishedState = MutableStateFlow(false)
    val isGameFinishedState = _isGameFinishedState.asStateFlow()

    private val _scoreState = MutableStateFlow(0)
    val scoreState = _scoreState.asStateFlow()

    private val _highScoreState = MutableStateFlow(0)
    val highScoreState = _highScoreState.asStateFlow()


    init {
        getCountriesFromDB()
    }

    fun getCountriesFromDB(){
        viewModelScope.launch(Dispatchers.IO) {
            _countriesState.value = countryRepository.getCountriesFromDB()
            _highScoreState.value = countryRepository.getHighScore("written")
            getChosenCountry()
        }

    }

    fun getChosenCountry(){
        val randomCountry= _countriesState.value.random()

        _chosenCountryState.value = randomCountry
    }

    fun checkAnswer(answer: String){
        _isAnswerCorrectState.value = answer.equals(_chosenCountryState.value.capital, ignoreCase = true)
        if (_isAnswerCorrectState.value == false){
            _wrongAnswerState.value += 1
        } else{
            _scoreState.value += 1
        }
        if (_wrongAnswerState.value == 3){
            if (_scoreState.value > _highScoreState.value){
                _highScoreState.value = _scoreState.value
                viewModelScope.launch(Dispatchers.IO) {
                    countryRepository.insertHighScore("written", _scoreState.value)
                }
            }
            _isGameFinishedState.value = true
        }
    }

    fun nextQuestion(){
        _isAnswerCorrectState.value = null
        getChosenCountry()
    }

    fun onPlayAgain() {
        _scoreState.value = 0
        _wrongAnswerState.value = 0
        _isGameFinishedState.value = false
        nextQuestion()
    }

    fun onGoToMenu(navHostController: NavHostController) {
        _scoreState.value = 0
        _wrongAnswerState.value = 0
        _isGameFinishedState.value = false
        navHostController.navigate("quiz")
    }

}
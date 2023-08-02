package com.example.geolearnapp.ui.quiz.truefalse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.geolearnapp.data.database.entity.Country
import com.example.geolearnapp.domain.repository.CountryRepository
import com.example.geolearnapp.domain.use_case.CountryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrueFalseViewModel @Inject constructor(
    val countryUseCases: CountryUseCases
) : ViewModel() {

    private val _isQuestionTrueState = MutableStateFlow<Boolean?>(null)
    val isQuestionTrueState = _isQuestionTrueState.asStateFlow()

    private val _isButtonsActiveState = MutableStateFlow<Boolean>(true)
    val isButtonsActiveState = _isButtonsActiveState.asStateFlow()

    private val _isAnswerCorrectState = MutableStateFlow<Boolean?>(null)
    val isAnswerCorrectState = _isAnswerCorrectState.asStateFlow()

    private val _countryState = MutableStateFlow(Country("", "", "", ""))
    val countryState = _countryState.asStateFlow()

    private val _scoreState = MutableStateFlow(0)
    val scoreState = _scoreState.asStateFlow()

    private val _wrongAnswerState = MutableStateFlow(0)
    val wrongAnswerState = _wrongAnswerState.asStateFlow()

    private val _isGameFinishedState = MutableStateFlow(false)
    val isGameFinishedState = _isGameFinishedState.asStateFlow()

    private val _highScoreState = MutableStateFlow(0)
    val highScoreState = _highScoreState.asStateFlow()

    private val _trueCapitalState = MutableStateFlow("")
    val trueCapitalState = _trueCapitalState.asStateFlow()

    private val _countriesState = MutableStateFlow(listOf<Country>())
    val countriesState = _countriesState.asStateFlow()


    init {
        getCountries()
    }

    fun getCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            _countriesState.value = countryUseCases.getCountriesFromDB()
            _highScoreState.value = countryUseCases.getHighScore("truefalse")
            getCountry()
        }
    }

    fun getCountry() {

        val randomNumber = (0..1).random()
        println("randomNumber: $randomNumber")
        if (randomNumber == 0) {
            _isQuestionTrueState.value = true
            _countryState.value = _countriesState.value.random()

            while (_countryState.value.capital == "") {
                _countryState.value = countriesState.value.random()
            }

            _trueCapitalState.value = _countryState.value.capital
        } else {
            var randomCountryForCountry = countriesState.value.random()
            var randomCountryForCapital = countriesState.value.random()

            while (randomCountryForCountry.capital == "") {
                randomCountryForCountry = countriesState.value.random()
            }
            while (randomCountryForCapital.capital == "") {
                randomCountryForCapital = countriesState.value.random()
            }

            _trueCapitalState.value = randomCountryForCountry.capital

            _isQuestionTrueState.value =
                randomCountryForCountry.capital == randomCountryForCapital.capital

            _countryState.value = Country(
                name = randomCountryForCountry.name,
                capital = randomCountryForCapital.capital,
                iso2 = "",
                iso3 = ""
            )


        }

    }

    fun checkAnswer(answer: Boolean) {
        println(answer)
        _isAnswerCorrectState.value = answer == _isQuestionTrueState.value
        if (answer == _isQuestionTrueState.value) {
            _scoreState.value = _scoreState.value + 1
        } else {
            _wrongAnswerState.value = _wrongAnswerState.value + 1
            if (_wrongAnswerState.value == 3) {
                _isGameFinishedState.value = true
                viewModelScope.launch(Dispatchers.IO) {

                    //val highScore = countryRepository.getHighScore("truefalse")
                    if (_scoreState.value > _highScoreState.value) {
                        countryUseCases.insertHighScore("truefalse", _scoreState.value)
                        _highScoreState.value = _scoreState.value
                    }
                }
            }
        }
        _isButtonsActiveState.value = false
    }

    fun nextQuestion() {
        _isAnswerCorrectState.value = null
        _isQuestionTrueState.value = null
        _isButtonsActiveState.value = true
        _trueCapitalState.value = "AA"
        getCountry()
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
package com.example.geolearnapp.ui.quiz.multiplechoices

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import javax.inject.Inject

@HiltViewModel
class MultipleChoicesViewModel @Inject constructor(
    val countryRepository: CountryRepository
): ViewModel() {

    private val _countriesState = MutableStateFlow(listOf<Country>())
    val countriesState = _countriesState.asStateFlow()

    private val _chosenCapitalState = MutableStateFlow("")
    val chosenCapitalState = _chosenCapitalState.asStateFlow()

    private val _optionsState = MutableStateFlow(listOf<String>())
    val optionsState = _optionsState.asStateFlow()

    private val _isAnswerCorrectState = MutableStateFlow<Boolean?>(null)
    val isAnswerCorrectState = _isAnswerCorrectState.asStateFlow()

    private val _scoreState = MutableStateFlow(0)
    val scoreState = _scoreState.asStateFlow()

    private val _highScoreState = MutableStateFlow(0)
    val highScoreState = _highScoreState.asStateFlow()

    private val _wrongAnswerState = MutableStateFlow(0)
    val wrongAnswerState = _wrongAnswerState.asStateFlow()

    private val _isGameFinishedState = MutableStateFlow(false)
    val isGameFinishedState = _isGameFinishedState.asStateFlow()

    private val _correctAnswerState = MutableStateFlow("")
    val correctAnswerState = _correctAnswerState.asStateFlow()

    private val _isButtonsActiveState = MutableStateFlow<Boolean>(true)
    val isButtonsActiveState = _isButtonsActiveState.asStateFlow()

    val _selectedAnswerState = MutableStateFlow(0)
    val selectedAnswerState = _selectedAnswerState.asStateFlow()

    init {
        getCountries()
    }

    fun getCountries(){
        viewModelScope.launch(Dispatchers.IO) {
            _countriesState.value= countryRepository.getCountriesFromDB()
            _highScoreState.value = countryRepository.getHighScore("multiplechoices")
            getChosenCapitalAndOptions()
        }
    }

    fun getChosenCapitalAndOptions(){
        var randomCountry = _countriesState.value.random()
        while (randomCountry.capital == ""){
            randomCountry = _countriesState.value.random()
        }
        _chosenCapitalState.value = randomCountry.capital
        _correctAnswerState.value = randomCountry.name
        while (_optionsState.value.size < 3){
            val country = _countriesState.value.random()
            if (country.capital != _chosenCapitalState.value){
                _optionsState.value = _optionsState.value + country.name
            }
        }
        _optionsState.value = _optionsState.value + randomCountry.name
        _optionsState.value = _optionsState.value.shuffled()
    }

    fun checkAnswer(answer: Int){
        _isButtonsActiveState.value = false
        _selectedAnswerState.value = answer

        _isAnswerCorrectState.value = _optionsState.value[answer] == _correctAnswerState.value
        if (_isAnswerCorrectState.value == true){
            _scoreState.value = _scoreState.value + 1
        } else {
            _wrongAnswerState.value = _wrongAnswerState.value + 1

            if (_wrongAnswerState.value == 3){
                _isGameFinishedState.value = true
                viewModelScope.launch(Dispatchers.IO) {

                    val highScore = countryRepository.getHighScore("multiplechoices")
                    if (_scoreState.value > highScore) {
                        countryRepository.insertHighScore("multiplechoices", _scoreState.value)
                        _highScoreState.value = _scoreState.value
                    }
                }
            }
        }
    }



    fun nextQuestion(){
        _isAnswerCorrectState.value = null
        _isButtonsActiveState.value = true
        _optionsState.value = listOf()
        getChosenCapitalAndOptions()
    }

    fun onPlayAgain() {
        _scoreState.value = 0
        _isButtonsActiveState.value = true
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
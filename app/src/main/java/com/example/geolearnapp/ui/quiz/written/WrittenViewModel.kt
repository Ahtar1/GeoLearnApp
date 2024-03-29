package com.example.geolearnapp.ui.quiz.written

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
class WrittenViewModel @Inject constructor(
    val countryUseCases: CountryUseCases
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
            _countriesState.value = countryUseCases.getCountriesFromDB()
            _highScoreState.value = countryUseCases.getHighScore("written")
            getChosenCountry()
        }

    }

    fun getChosenCountry(){
        val randomCountry= _countriesState.value.random()
        if (randomCountry.capital != ""){
            _chosenCountryState.value = randomCountry
        } else{
            getChosenCountry()
        }

    }

    fun checkAnswer(answer: String){

        _isAnswerCorrectState.value = answer.equals(_chosenCountryState.value.capital, ignoreCase = true)
        if (answer=="don't know"){
            _isAnswerCorrectState.value = null
        }
        if (_isAnswerCorrectState.value != true){
            _wrongAnswerState.value += 1
        } else{
            _scoreState.value += 1
        }
        if (_wrongAnswerState.value == 3){
            if (_scoreState.value > _highScoreState.value){
                _highScoreState.value = _scoreState.value
                viewModelScope.launch(Dispatchers.IO) {
                    countryUseCases.insertHighScore("written", _scoreState.value)
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
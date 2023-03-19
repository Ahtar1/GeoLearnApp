package com.example.geolearnapp.ui.quiz.truefalse

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
class TrueFalseViewModel @Inject constructor(
    private val countryRepository: CountryRepository
) : ViewModel() {

    private val _isQuestionTrueState = MutableStateFlow<Boolean?>(null)
    val isQuestionTrueState = _isQuestionTrueState.asStateFlow()

    private val _isButtonsActiveState = MutableStateFlow<Boolean>(true)
    val isButtonsActiveState = _isButtonsActiveState.asStateFlow()

    private val _congraState = MutableStateFlow<Boolean?>(null)
    val congraState = _congraState.asStateFlow()

    private val _countryState = MutableStateFlow(Country("","","",""))
    val countryState= _countryState.asStateFlow()

    private val _scoreState = MutableStateFlow(0)
    val scoreState= _scoreState.asStateFlow()

    private val _wrongAnswerState = MutableStateFlow(0)
    val wrongAnswerState= _wrongAnswerState.asStateFlow()

    private var _isGameFinishedState = MutableStateFlow(false)
    val isGameFinishedState = _isGameFinishedState.asStateFlow()


    init {
        getCountry()
    }

    fun getCountry(){

        viewModelScope.launch(Dispatchers.IO) {
            val countries= countryRepository.getCountriesFromDB().map {
                if (it.capital == "") {
                    Country(name = it.name, capital = "None", iso2 = "", iso3 = "")
                } else {
                    it
                }
            }

            val randomNumber = (0..1).random()
            println("randomNumber: $randomNumber")
            if(randomNumber==0){
                _isQuestionTrueState.value = true
                _countryState.value= countries.random()
            }else{
                val randomCountry = countries.random()
                val randomCapital = countries.random()

                _isQuestionTrueState.value = randomCountry.capital == randomCapital.capital

                _countryState.value= Country(name = randomCountry.name, capital = randomCapital.capital, iso2 = "", iso3 = "")
            }
        }
    }

    fun checkAnswer(answer: Boolean) {
        println(answer)
        _congraState.value= answer==_isQuestionTrueState.value
        if (answer==_isQuestionTrueState.value){
            _scoreState.value= _scoreState.value+1
        } else {
            _wrongAnswerState.value= _wrongAnswerState.value+1
            if (_wrongAnswerState.value==3){
                _isGameFinishedState.value= true
            }
        }
        _isButtonsActiveState.value= false
    }

    fun nextQuestion(){
        _congraState.value= null
        _isQuestionTrueState.value= null
        _isButtonsActiveState.value= true
        getCountry()
    }

    fun onPlayAgain(){
        _scoreState.value= 0
        _wrongAnswerState.value= 0
        _isGameFinishedState.value= false
        nextQuestion()
    }

    fun onGoToMenu(navHostController: NavHostController){
        _scoreState.value= 0
        _wrongAnswerState.value= 0
        _isGameFinishedState.value= false
        navHostController.navigate("quiz")
    }
}
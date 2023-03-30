package com.example.geolearnapp.ui.quiz.matching

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.geolearnapp.data.database.entity.Country
import com.example.geolearnapp.data.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.fixedRateTimer
import kotlin.math.round
import kotlin.math.roundToInt

@HiltViewModel
class MatchingViewModel @Inject constructor(
    val countryRepository: CountryRepository
): ViewModel() {

    private val _countriesState = MutableStateFlow(listOf<Country>())
    val countriesState = _countriesState.asStateFlow()

    private val _chosenCountriesState = MutableStateFlow(listOf<Country>())
    val chosenCountriesState = _chosenCountriesState.asStateFlow()

    private val _chosenCountriesSeparatedState = MutableStateFlow(listOf<String>())
    val chosenCountriesSeparatedState = _chosenCountriesSeparatedState.asStateFlow()

    private val _clickedButtonState = MutableStateFlow(-1)
    val clickedButtonState = _clickedButtonState.asStateFlow()

    private val _correctAnswersState = MutableStateFlow(listOf<Int>())
    val correctAnswersState = _correctAnswersState.asStateFlow()

    private val _wrongAnswersState = MutableStateFlow(listOf<Int>())
    val wrongAnswersState = _wrongAnswersState.asStateFlow()

    private val _timeState = MutableStateFlow(0.0)
    val timeState = _timeState.asStateFlow()

    private val _isGameFinishedState = MutableStateFlow(false)
    val isGameFinishedState = _isGameFinishedState.asStateFlow()

    private val _highScoreState = MutableStateFlow(0)
    val highScoreState = _highScoreState.asStateFlow()

    private val _highScoreDecimalState = MutableStateFlow(0)
    val highScoreDecimalState = _highScoreDecimalState.asStateFlow()

    private lateinit var timer: Timer

    init {
        getCountriesFromDB()
        println("init")
    }

    private fun getCountriesFromDB(){
        viewModelScope.launch(Dispatchers.IO) {
            _countriesState.value = countryRepository.getCountriesFromDB()
            _highScoreState.value = countryRepository.getHighScore("matching")
            _highScoreDecimalState.value = countryRepository.getHighScore("matchingDecimal")
            println("highScoredecimal from db: ${_highScoreDecimalState.value}")
            println(_countriesState.value[0].name)
            getCountries()
        }
    }

    fun getCountries(){
        println("get countries")
        while (_chosenCountriesState.value.size < 6){
            val randomCountry = _countriesState.value.random()
            if (!_chosenCountriesState.value.contains(Country(name = randomCountry.name, capital = randomCountry.capital, iso2 = "", iso3 = "" )) && randomCountry.capital != ""){
                _chosenCountriesState.value = _chosenCountriesState.value + Country(name = randomCountry.name, capital = randomCountry.capital, iso2 = "", iso3 = "")
            }
        }
        _chosenCountriesSeparatedState.value = _chosenCountriesState.value.map { it.name } + _chosenCountriesState.value.map { it.capital }
        println(_chosenCountriesSeparatedState.value[0])
        _chosenCountriesSeparatedState.value = _chosenCountriesSeparatedState.value.shuffled()
        println(_chosenCountriesSeparatedState.value.size)

    }

    fun onCountryClicked(answer: Int){
        if (_timeState.value == 0.0){
            timer= fixedRateTimer("timer", false, 0L, 100L) {
                _timeState.value = _timeState.value + 0.1
            }
        }

        if (_clickedButtonState.value==answer){
            _clickedButtonState.value = -1
        } else {
            if (_clickedButtonState.value==-1){
                _clickedButtonState.value = answer
                println("first clicked: $answer")
            } else{
                println("second clicked: $answer")

                if(
                    _chosenCountriesState.value.contains(Country(name = _chosenCountriesSeparatedState.value[_clickedButtonState.value], capital = _chosenCountriesSeparatedState.value[answer], iso2 = "", iso3 = ""))
                    ||
                    _chosenCountriesState.value.contains(Country(name = _chosenCountriesSeparatedState.value[answer], capital = _chosenCountriesSeparatedState.value[_clickedButtonState.value], iso2 = "", iso3 = ""))
                )
                {
                    _correctAnswersState.value = _correctAnswersState.value + answer + _clickedButtonState.value
                    _clickedButtonState.value = -1
                    println("answer: $answer")
                } else{
                    val lastClicked=_clickedButtonState.value
                    _clickedButtonState.value = -1
                    println("wrong answer")
                    _wrongAnswersState.value = _wrongAnswersState.value + answer + lastClicked
                    println(_wrongAnswersState.value)
                    _timeState.value+=1
                    viewModelScope.launch(Dispatchers.IO) {
                        delay(1000)
                        _wrongAnswersState.value = listOf()
                    }
                }
            }
        }

        if (_correctAnswersState.value.size == 12){
            timer.cancel()

            if (_timeState.value < _highScoreState.value+(_highScoreDecimalState.value/10)){
                viewModelScope.launch(Dispatchers.IO) {
                    _highScoreState.value = _timeState.value.toInt()
                    _highScoreDecimalState.value = ((_timeState.value-_timeState.value.toInt())*10).roundToInt()
                    println(_timeState.value.toString() + " " + _timeState.value.toInt())

                    println("highScoredecimal from new high: ${_highScoreDecimalState.value}")
                    countryRepository.insertHighScore("matching", _highScoreState.value)
                    countryRepository.insertHighScore("matchingDecimal", _highScoreDecimalState.value)
                    _isGameFinishedState.value = true
                }
            } else if (_highScoreState.value == 0){
                viewModelScope.launch(Dispatchers.IO) {
                    _highScoreState.value = _timeState.value.toInt()
                    _highScoreDecimalState.value = ((_timeState.value-_timeState.value.toInt())*10).roundToInt()
                    println(_timeState.value.toString() + " " + _timeState.value.toInt())
                    println("highScoredecimal from first high: ${_highScoreDecimalState.value}")
                    countryRepository.insertHighScore("matching", _highScoreState.value)
                    countryRepository.insertHighScore("matchingDecimal", _highScoreDecimalState.value)
                    _isGameFinishedState.value = true
                }
            } else{
                _isGameFinishedState.value = true
            }

        }
    }

    fun onPlayAgain(){
        _chosenCountriesState.value = emptyList()
        _chosenCountriesSeparatedState.value = emptyList()
        _clickedButtonState.value = -1
        _correctAnswersState.value = listOf()
        _wrongAnswersState.value = listOf()
        _timeState.value = 0.0
        _isGameFinishedState.value = false


        getCountries()
    }

    fun onGoToMenu(navHostController: NavHostController){
        println("go to menu")

        _chosenCountriesState.value = listOf()
        println("go to menu 2")
        _chosenCountriesSeparatedState.value = listOf()
        _clickedButtonState.value = -1
        _correctAnswersState.value = listOf()
        _wrongAnswersState.value = listOf()
        _timeState.value = 0.0
        _isGameFinishedState.value = false

        navHostController.navigate("quiz")
        navHostController.clearBackStack("matching")
        println("go to menu 3")
    }
}
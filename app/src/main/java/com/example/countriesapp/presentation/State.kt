package com.example.countriesapp.presentation

sealed class State

object Error : State()
object Progress : State()
class CountryResult(
    val area: String,
    val population: String,
    val name: String,
    val pngOfFlag: String,
    val capital: String
    ) : State()
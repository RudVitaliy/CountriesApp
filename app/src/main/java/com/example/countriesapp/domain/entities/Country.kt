package com.example.countriesapp.domain.entities

data class Country(
    var id: Int = 0,
    val name: Name,
    val capital: List<String>,
    val population: Long,
    val area: Long,
    val flags: Flag
)

data class Flag(
    val png: String
)

data class Name(
    val common: String
)

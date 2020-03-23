package com.vivek.rapidapi.data

import java.io.Serializable

data class CountryInfo(
    val name: String = "",
    val capital: String = "",
    val region: String = "",
    val subRegion: String = "",
    val population: Int,
    val alpha3Code: String = "",
    val alpha2Code: String = "",
    val languages: ArrayList<String>
) : Serializable


package com.example.entity

data class TeamsResponse(
    val coin: Coin,
    val count: Int,
    val filters: Filters,
    val season: Season,
    val teams: List<Team>,
    val errorCode: Int,
    val message: String
)
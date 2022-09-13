package com.example.entity


data class CoinListResponse(
    var coins: List<Coin>,
    val error: Int
)
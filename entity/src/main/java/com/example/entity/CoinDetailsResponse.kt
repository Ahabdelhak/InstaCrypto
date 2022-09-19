package com.example.entity

data class CoinDetailsResponse(
    val description: Description,
    val hashing_algorithm: String,
    val id: String,
    val image: Image,
    val market_data: MarketData,
    val name: String,
    val symbol: String
)
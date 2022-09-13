package com.example.entity

data class Coin(
    val id: String?,
    val symbol: String?,
    val name: String?,
    val platforms: Map<String, String>?
)
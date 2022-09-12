package com.example.feature.data.model

import java.io.Serializable

data class Crypto(
    val id: Int,
    val title: String,
    val price: String,
    val CryptoImageId: Int = 0
) : Serializable

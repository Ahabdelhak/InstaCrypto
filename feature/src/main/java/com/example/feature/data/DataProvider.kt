package com.example.feature.data

import com.example.feature.R
import com.example.feature.data.model.Crypto

object DataProvider {
    val crypto =
        Crypto(
            id = 1,
            title = "Bitcoin",
            price = "$13131.0",
            CryptoImageId = R.drawable.ic_launcher_background
        )

    val cryptoList = listOf(
        crypto,
        Crypto(
            id = 2,
            title = "Ethereum",
            price = "$13131.0",
            CryptoImageId = R.drawable.ic_launcher_background
        ),
        Crypto(
            id = 3,
            title = "BNB",
            price = "$13131.0",
            CryptoImageId = R.drawable.ic_launcher_background
        ),
        Crypto(
            id = 4,
            title = "DeFi",
            price = "$13131.0",
            CryptoImageId = R.drawable.ic_launcher_background
        ),
        Crypto(
            id = 5,
            title = "Binance",
            price = "$13131.0",
            CryptoImageId = R.drawable.ic_launcher_background
        ),
        Crypto(
            id = 6,
            title = "Bitcoin",
            price = "$13131.0",
            CryptoImageId = R.drawable.ic_launcher_background
        ),
        Crypto(
            id = 7,
            title = "DeFi Coin",
            price = "$13131.0",
            CryptoImageId = R.drawable.ic_launcher_background
        ),
        Crypto(
            id = 8,
            title = "Binance",
            price = "$13131.0",
            CryptoImageId = R.drawable.ic_launcher_background
        ),
        Crypto(
            id = 9,
            title = "Bitcoin",
            price = "$13131.0",
            CryptoImageId = R.drawable.ic_launcher_background
        ),
        Crypto(
            id = 10,
            title = "BNB",
            price = "$13131.0",
            CryptoImageId = R.drawable.ic_launcher_background
        ),
        Crypto(
            id = 11,
            title = "USD Coin",
            price = "$13131.0",
            CryptoImageId = R.drawable.ic_launcher_background
        ),
        Crypto(
            id = 12,
            title = "Binance",
            price = "$13131.0",
            CryptoImageId = R.drawable.ic_launcher_background
        ),
    )
}
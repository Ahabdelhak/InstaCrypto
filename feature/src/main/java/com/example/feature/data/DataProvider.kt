/*
 * Copyright 2022 AHMED ABDELHAK. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
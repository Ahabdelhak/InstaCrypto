package com.example.data.util

import com.example.entity.*

/**
 * Dummy data generator for tests
 */
class TestDataGenerator {

    companion object {


        fun generateCoinListResponse(): CoinListResponse {
            return CoinListResponse()
        }

        fun generateCoinDetailsResponse(): CoinDetailsResponse {
            return CoinDetailsResponse(
                description = Description("en"),"N/A",
                "1", image = (Image("","","")),
                market_data = MarketData(0.0, current_price = CurrentPrice(0.0)),
                "Bitcoin","B"
            )
        }
    }

}
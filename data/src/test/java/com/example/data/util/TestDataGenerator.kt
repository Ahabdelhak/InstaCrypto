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
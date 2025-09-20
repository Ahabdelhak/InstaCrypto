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

package com.example.remote.source

import com.example.data.repository.RemoteDataSource
import com.example.entity.CoinDetailsResponse
import com.example.entity.CoinListResponse
import com.example.remote.api.ApiService
import com.example.remote.api.ErrorMessage
import javax.inject.Inject
import com.google.gson.Gson


class RemoteDataSourceImp @Inject constructor(
    private val apiService: ApiService) : RemoteDataSource {

    //coin list response
    override suspend fun getCoinList(): CoinListResponse {
        val response = apiService.coinList()
        if (response.code() != 200) throw Exception(response.message())
        val networkData = response.body()
        //if (networkData?.error == 404) throw Exception("Error")
        return networkData!!
    }

    //get coin details response
    override suspend fun getCoinDetails(id:String): CoinDetailsResponse {
        val response = apiService.coinDetailsById(id)
        if (response.code() != 200) throw Exception(getErrorMessage(response.errorBody()!!.string()))
        val networkData = response.body()
        //if (networkData?.errorCode == 404) throw Exception("Error")
        return networkData!!
    }

    private fun getErrorMessage(errorBody: String): String? {
        val gson = Gson()
        val errorResponse: ErrorMessage = gson.fromJson(
            errorBody,
            ErrorMessage::class.java)
        return errorResponse.message
    }
}

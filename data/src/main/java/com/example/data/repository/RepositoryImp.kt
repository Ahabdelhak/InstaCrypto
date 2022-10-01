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

package com.example.data.repository

import com.example.common.Resource
import com.example.domain.repository.Repository
import com.example.entity.CoinDetailsResponse
import com.example.entity.CoinListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Implementation class of [Repository]
 */
class RepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : Repository {



    override suspend fun getCoinList(): Flow<Resource<CoinListResponse>> {
        return flow {
            try {
                // Get data from RemoteDataSource
                val data = remoteDataSource.getCoinList()
                // Emit data
                emit(Resource.Success(data))
            } catch (ex : Exception) {
                // If remote request fails
                emit(Resource.Error(ex.message ?:"Error"))
            }
        }
    }

    override suspend fun getCoinDetails(id:String): Flow<Resource<CoinDetailsResponse>> {
        return flow {
            try {
                // Get data from RemoteDataSource
                val data = remoteDataSource.getCoinDetails(id)
                // Emit data
                emit(Resource.Success(data))
            } catch (ex : Exception) {
                // If remote request fails
                emit(Resource.Error(ex.message ?:"Error"))
            }
        }
    }

}
package com.example.koinz.repository

import com.example.koinz.network.ResponseResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepo {

    suspend fun <T> callApi(
        api: suspend () -> T
    ): ResponseResource<T> {
        return withContext(Dispatchers.IO) {
            try {
                ResponseResource.Success(api.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        ResponseResource.Failure(
                            false,
                            throwable.code(),
                            throwable.response()!!.errorBody()
                        )
                    }
                    else -> {
                        ResponseResource.Failure(
                            true,
                            null,
                            null
                        )
                    }
                }
            }
        }
    }
}
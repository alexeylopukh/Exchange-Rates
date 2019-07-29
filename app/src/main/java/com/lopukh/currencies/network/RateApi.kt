package com.lopukh.currencies.network

import com.lopukh.currencies.data.Rate
import retrofit2.Response
import retrofit2.http.GET

interface RateApi{
    @GET("Rates?Periodicity=0")
    suspend fun getRateList(): Response<ArrayList<Rate>>

}
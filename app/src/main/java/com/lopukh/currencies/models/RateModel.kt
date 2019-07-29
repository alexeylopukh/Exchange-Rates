package com.lopukh.currencies.models

import com.lopukh.currencies.data.Rate
import com.lopukh.currencies.network.RateService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class RateModel {

    suspend fun getResonse(): Response<ArrayList<Rate>>{
        val rateService = RateService.instance().rateService
        return withContext(Dispatchers.IO) {
            rateService.getRateList()
        }
    }
}
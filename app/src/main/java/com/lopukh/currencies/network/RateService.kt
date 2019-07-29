package com.lopukh.currencies.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RateService private constructor(){

    private val BASE_URL = "http://www.nbrb.by/API/ExRates/"
    var rateService: RateApi

    init {
        rateService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RateApi::class.java)
    }

    companion object{
        private var rateService: RateService? = null

        fun instance (): RateService{
            if (rateService == null){
                rateService = RateService()
            }
            return rateService!!
        }
    }

}
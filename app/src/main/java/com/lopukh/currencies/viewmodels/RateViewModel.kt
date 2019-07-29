package com.lopukh.currencies.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.lopukh.currencies.data.Rate
import com.lopukh.currencies.models.RateModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RateViewModel : ViewModel() {

    var repoModel = RateModel()
    var rateList = ObservableField<ArrayList<Rate>>()
    var errorCode = ObservableField<Int>()

    fun refresh(){
        GlobalScope.launch(Dispatchers.Main) {
            val response = repoModel.getResonse()
            if (response.isSuccessful){
                rateList.set(sort(response.body()!!))
            } else
            {
                errorCode.set(response.code())
            }
        }
    }

    fun sort(inputList: ArrayList<Rate>): ArrayList<Rate>{
        val result = ArrayList<Rate>()
        for (i in 0 until inputList.size){
                if (inputList[i].curAbbreviation == "USD" ||
                    inputList[i].curAbbreviation == "EUR" ||
                    inputList[i].curAbbreviation == "RUB"){
                    result.add(inputList[i])
                }
        }
        for (i in 0 until result.size){
            inputList.remove(result[i])
        }
        result.addAll(inputList)
        return result
    }


}
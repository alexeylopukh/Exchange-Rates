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
                rateList.set(response.body())
            } else
            {
                errorCode.set(response.code())
            }
        }
    }
}
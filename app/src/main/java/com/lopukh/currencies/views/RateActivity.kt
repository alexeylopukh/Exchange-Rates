package com.lopukh.currencies.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lopukh.currencies.R
import com.lopukh.currencies.viewmodels.RateViewModel
import com.lopukh.currencies.adapters.RateAdapter
import com.lopukh.currencies.data.Rate
import com.lopukh.currencies.databinding.ActivityRateBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RateActivity : AppCompatActivity() {

    lateinit var binding: ActivityRateBinding
    var repoViewModel = RateViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rate)
        binding.rateViewmodel = ViewModelProviders.of(this).get(RateViewModel::class.java)
        binding.executePendingBindings()
        binding.rateRv.layoutManager = LinearLayoutManager(this)
        binding.rateRv.adapter = RateAdapter(
            if (repoViewModel.rateList.get() != null){
                repoViewModel.rateList.get()!!
            } else listOf()
        )
        initCallbacks()
        repoViewModel.refresh()
    }

    private fun initCallbacks() {
        repoViewModel.rateList.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                val newRateList: ArrayList<Rate>? = repoViewModel.rateList.get()
                if (newRateList != null) {
                    val adapter: RateAdapter = binding.rateRv.adapter as RateAdapter
                    adapter.rateList = newRateList
                }
            }
        })

        repoViewModel.errorCode.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                val errorCode: Int? = repoViewModel.errorCode.get()
                if (errorCode != null){
                    Toast.makeText(baseContext, "Ошибка $errorCode", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}

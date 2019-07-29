package com.lopukh.currencies.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lopukh.currencies.data.Rate
import com.lopukh.currencies.databinding.RateListItemBinding

class RateAdapter(rateList: List<Rate>) : RecyclerView.Adapter<RateAdapter.ViewHolder>() {

    var rateList: List<Rate> = rateList
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RateListItemBinding = RateListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return rateList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.curAbbreviation.text = rateList[position].curAbbreviation
        holder.binding.curName.text = rateList[position].curName
        holder.binding.curOfficialRate.text = rateList[position].curOfficialRate.toString()
        holder.binding.curScale.text = rateList[position].curScale.toString()
    }

    class ViewHolder(val binding: RateListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}
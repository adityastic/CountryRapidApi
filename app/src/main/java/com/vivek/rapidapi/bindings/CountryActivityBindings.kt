package com.vivek.rapidapi.bindings

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vivek.rapidapi.adapters.CountryAdapter

@BindingAdapter("setCountriesAdapter")
fun setCountriesRecyclerAdapter(recyclerView: RecyclerView, adapter: CountryAdapter?) {
    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    recyclerView.adapter = adapter
}
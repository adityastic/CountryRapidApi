package com.vivek.rapidapi.bindings

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vivek.rapidapi.adapters.CountryAdapter
import com.vivek.rapidapi.adapters.LanguageAdapter

@BindingAdapter("setLanguageAdapter")
fun setLanguagesRecyclerAdapter(recyclerView: RecyclerView, languages: ArrayList<String>) {
    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    recyclerView.adapter = LanguageAdapter(languages)
}
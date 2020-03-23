package com.vivek.rapidapi.viewmodels

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vivek.rapidapi.adapters.CountryAdapter
import com.vivek.rapidapi.data.CountryInfo
import com.vivek.rapidapi.databases.CountryService

class CountryViewModel(
    private val countryService: CountryService
) : ViewModel() {

    var items = listOf<CountryInfo>()
    var displayList: MutableLiveData<MutableList<CountryInfo>> = MutableLiveData()
    var adapter: ObservableField<CountryAdapter> = ObservableField()
    var isEmpty: ObservableBoolean = ObservableBoolean(true)

    init {
        displayList.value = mutableListOf()
    }

    fun getCountries() = countryService.getCountries()

    fun refreshAdapter() {
        isEmpty.set(items.isEmpty())
        adapter.get()?.notifyDataSetChanged()
    }

    fun refreshList(query: String) {
        items.let {
            displayList.value?.clear()
            if (query.trim().isNotBlank()) {
                for (item in it) {
                    if (item.region.contains(query, true) ||
                        item.capital.contains(query, true) ||
                        item.name.contains(query, true)
                    )
                        displayList.value?.add(item)
                }
            } else
                displayList.value?.addAll(it)

            refreshAdapter()
        }
    }
}

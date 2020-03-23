package com.vivek.rapidapi.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vivek.rapidapi.R
import com.vivek.rapidapi.adapters.CountryAdapter
import com.vivek.rapidapi.data.CountryInfo
import com.vivek.rapidapi.databinding.CountryActivityBinding
import com.vivek.rapidapi.viewmodels.CountryViewModel
import kotlinx.android.synthetic.main.country_activity.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.reflect.Type


class CountryActivity : AppCompatActivity() {

    private val countryViewModel by viewModel<CountryViewModel>()

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityBinding =
            DataBindingUtil.setContentView<CountryActivityBinding>(this, R.layout.country_activity)
        activityBinding.viewModel = countryViewModel

        setItemsObserver()
        setSearchBar()
    }

    private fun setItemsObserver() {
        countryViewModel.getCountries().observe(this, Observer {
            countryViewModel.items = it
            countryViewModel.displayList.value?.addAll(it)
            countryViewModel.displayList.value?.let { cInfoList ->
                countryViewModel.adapter.set(CountryAdapter(cInfoList, this))
            }
            countryViewModel.refreshAdapter()
        })

        countryViewModel.displayList.observe(this, Observer {
            countryViewModel.refreshAdapter()
        })
    }

    private fun setSearchBar() {
        searchText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    countryViewModel.refreshList(s.toString())
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }
}



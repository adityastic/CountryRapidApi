package com.vivek.rapidapi.databases

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vivek.rapidapi.data.CountryInfo
import org.koin.core.KoinComponent
import java.lang.reflect.Type

class CountryService(
    context: Context
) : KoinComponent {
    private val countries = MutableLiveData<List<CountryInfo>>()

    init {
        val queue = Volley.newRequestQueue(context)
        val url = "https://restcountries-v1.p.rapidapi.com/all"
        val postRequest: StringRequest = object : StringRequest(
            Method.GET, url,
            Response.Listener { response ->

                val collectionType: Type = object :
                    TypeToken<List<CountryInfo?>?>() {}.type

                countries.value = Gson().fromJson(response, collectionType)
            },
            Response.ErrorListener { error ->
                Log.d("ERROR", "error => $error")
            }
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params: MutableMap<String, String> =
                    HashMap()
                params["x-rapidapi-host"] = "restcountries-v1.p.rapidapi.com"
                params["x-rapidapi-key"] = "c438a72cf7mshc9afa3fed95b35ap101c3ajsn2da158741db3"
                return params
            }
        }
        queue.add(postRequest)
    }

    fun getCountries(): LiveData<List<CountryInfo>> = countries
}

package com.vivek.rapidapi.di

import com.vivek.rapidapi.databases.CountryService
import com.vivek.rapidapi.viewmodels.CountryViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val allModules = module {
    viewModel { CountryViewModel(get()) }
    single { CountryService(androidApplication()) }
}

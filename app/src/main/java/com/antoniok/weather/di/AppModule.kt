package com.antoniok.weather.di

import com.antoniok.weather.ui.root.RootViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { RootViewModel(get(), get(), get()) }
}

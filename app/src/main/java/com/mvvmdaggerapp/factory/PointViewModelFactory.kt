package com.mvvmdaggerapp.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mvvmdaggerapp.viewmodels.PointViewModel
import javax.inject.Inject
import javax.inject.Provider


class PointViewModelFactory @Inject constructor(private val viewModelProvider: Provider<PointViewModel>):ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModelProvider.get() as T
    }

}
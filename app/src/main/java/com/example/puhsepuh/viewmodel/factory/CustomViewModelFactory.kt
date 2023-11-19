package com.example.puhsepuh.viewmodel.factory


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.puhsepuh.App
import com.example.puhsepuh.viewmodel.viewmodel.AddViewModel
import com.example.puhsepuh.viewmodel.viewmodel.EditViewModel
import com.example.puhsepuh.viewmodel.viewmodel.HomeViewModel

class CustomViewModelFactory(private val application: App): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(application) as T
        } else if (modelClass.isAssignableFrom(AddViewModel::class.java)) {
            return AddViewModel(application) as T
        } else if (modelClass.isAssignableFrom(EditViewModel::class.java)) {
            return EditViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
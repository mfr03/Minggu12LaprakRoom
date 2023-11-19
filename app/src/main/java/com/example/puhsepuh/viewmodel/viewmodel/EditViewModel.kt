package com.example.puhsepuh.viewmodel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.puhsepuh.App
import com.example.puhsepuh.model.ObatData

class EditViewModel(private val application: App) : ViewModel() {
    fun getObat(id: Int): LiveData<ObatData> {
        return application.getObat(id)
    }
}
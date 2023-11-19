package com.example.puhsepuh.viewmodel.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.puhsepuh.App
import com.example.puhsepuh.model.ObatData

class HomeViewModel(private val app: App) : ViewModel() {

    fun getAllData(): LiveData<List<ObatData>> {
        return app.getAllData()
    }

    fun deleteObat(obat: ObatData) {
        app.delete(obat)
    }

}
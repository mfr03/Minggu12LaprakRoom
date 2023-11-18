package com.example.puhsepuh.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.puhsepuh.model.ObatData

class HomeViewModel : ViewModel() {
    private val _data = MutableLiveData<ObatData>()
    val data get() = _data

    fun fetchData() {
        _data.value = ObatData(
            "Obat 1",
            "Penyakit 1",
            2,
            2,
            2,
            false,
            listOf("Pagi", "Siang", "Malam")
        )
    }
}
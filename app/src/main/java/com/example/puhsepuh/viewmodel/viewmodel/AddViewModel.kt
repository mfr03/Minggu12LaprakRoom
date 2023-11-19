package com.example.puhsepuh.viewmodel.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.puhsepuh.App
import com.example.puhsepuh.model.ObatData

class AddViewModel(private val application: App) : ViewModel() {

    fun addObat(obat: ObatData) {
        application.insert(obat)
    }

}
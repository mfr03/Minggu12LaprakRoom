package com.example.puhsepuh.model

data class ObatData(
    val namaObat: String,
    val gejalaPenyakit: String,
    val dosisMakan: Int,
    val dosisHari: Int,
    val waktuDosis: Int,
    val isSetelahMakan: Boolean,
    val waktuNotifikasi: List<String>,
)

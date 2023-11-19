package com.example.puhsepuh.model

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "obat_table")

data class ObatData(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int = 0,

    @ColumnInfo(name = "nama_obat")
    val namaObat: String,

    @ColumnInfo(name = "gejala_penyakit")
    val gejalaPenyakit: String,

    @ColumnInfo(name = "dosis_makan")
    val dosisMakan: Int,

    @ColumnInfo(name = "dosis_hari")
    val dosisHari: Int,

    @ColumnInfo(name = "dosis_lama_pengobatan")
    val dosisLamaPengobatan: Int,

    @ColumnInfo(name = "setelah_makan")
    val isSetelahMakan: Boolean?,

    // figure out how to add a list of string, later cause fuck this shit
    @ColumnInfo(name = "waktu_notifikasi")
    val waktuNotifikasi: String,
)

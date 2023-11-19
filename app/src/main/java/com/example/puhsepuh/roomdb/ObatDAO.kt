package com.example.puhsepuh.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.puhsepuh.model.ObatData

@Dao
interface ObatDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(obat: ObatData)

    @Update
    fun update(obat: ObatData)

    @Delete
    fun delete(obat: ObatData)

    @get:Query("SELECT * FROM obat_table ORDER BY id ASC")
    val allData: LiveData<List<ObatData>>

    @Query("SELECT * FROM obat_table WHERE id=:id")
    fun getObat(id: Int): LiveData<ObatData>

}
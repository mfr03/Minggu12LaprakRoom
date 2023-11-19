package com.example.puhsepuh.roomdb


import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.puhsepuh.model.ObatData

@Database(entities = [ObatData::class], version = 1, exportSchema = false)
abstract class ObatRoomDB : RoomDatabase() {
    abstract fun obatDao(): ObatDAO?
    companion object {
        @Volatile
        private var INSTANCE: ObatRoomDB? = null
        fun getDatabase(context: Context): ObatRoomDB? {
            synchronized(ObatRoomDB::class.java) {
                if (INSTANCE == null) {
                    INSTANCE = databaseBuilder(context.applicationContext,
                        ObatRoomDB::class.java, "obat_database")
                        .build()
                }
            }
            return INSTANCE
        }
    }
}
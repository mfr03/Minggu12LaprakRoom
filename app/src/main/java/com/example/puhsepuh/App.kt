package com.example.puhsepuh

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.puhsepuh.model.ObatData
import com.example.puhsepuh.roomdb.ObatDAO
import com.example.puhsepuh.roomdb.ObatRoomDB
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class App: Application() {

    val database by lazy {
        ObatRoomDB.getDatabase(this)
    }

    private lateinit var mObatDAO: ObatDAO
    private lateinit var executorService: ExecutorService

    override fun onCreate() {
        super.onCreate()
        mObatDAO = database!!.obatDao()!!
        executorService = Executors.newSingleThreadExecutor()

    }
    fun insert(obat: ObatData) {
        executorService.execute { mObatDAO.insert(obat) }
    }

    fun update(obat: ObatData) {
        executorService.execute { mObatDAO.update(obat) }
    }

    fun delete(obat: ObatData) {
        executorService.execute { mObatDAO.delete(obat) }
    }

    fun getAllData(): LiveData<List<ObatData>> {
        return mObatDAO.allData
    }

    fun getObat(id: Int): LiveData<ObatData> {
        return mObatDAO.getObat(id)
    }
}
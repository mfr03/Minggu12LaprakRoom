package com.example.puhsepuh

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.puhsepuh.databinding.ActivityMainBinding
import com.example.puhsepuh.notifications.NotificationService

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val app by lazy {
        application as App
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            val navController = findNavController(R.id.nav_host_fragment)
            bottomNavigationView.setupWithNavController(navController)

        }
        NotificationService.createNotificationChannel(this@MainActivity)
        NotificationService.scheduleNotification(this@MainActivity, 14,35)
//        val allData: LiveData<List<ObatData>> = app.getAllData()
//        allData.observeForever { obat ->
//            obat?.let {
//                for(data in it) {
//                    Log.d("MainActivity", "onCreate: ${data.id}")
//                    Log.d("MainActivity", "onCreate: ${data.namaObat}")
//                }
//            }
//        }
    }

}
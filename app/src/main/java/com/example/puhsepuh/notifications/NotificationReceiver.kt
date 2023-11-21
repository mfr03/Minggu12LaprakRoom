package com.example.puhsepuh.notifications


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log


class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "com.example.puhsepuh.NOTIFY_ACTION") {
            context?.let {
                Log.d("NotificationReceiver", "onReceive: ")
                NotificationService.showNotification(it)
            }
        }
    }
}
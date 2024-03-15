package com.awst.androidservicetrain

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.concurrent.Executors

internal class ProgressService : Service() {

    private companion object {
        const val LOG_TAG = "ProgressService"
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Запускает новый поток
        Executors.newSingleThreadExecutor().execute {
            for (i in 0..<100) {
                Log.d(LOG_TAG, "Progress: ${i}%")
                Thread.sleep(2000)
            }
            stopSelf()
        }

        return START_NOT_STICKY
    }

}
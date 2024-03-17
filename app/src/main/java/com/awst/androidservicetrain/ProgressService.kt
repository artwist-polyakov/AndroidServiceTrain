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

    override fun onCreate() {
        super.onCreate()
        Log.d(LOG_TAG, "onCreate()")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(LOG_TAG, "onStartCommand | startId: $startId")

        Executors.newSingleThreadExecutor().execute {
            for (i in 0..<100) {
                Log.d(LOG_TAG, "Progress: ${i}% | startId: $startId")
                // Изменили константу, чтобы прогресс менялся быстрее.
                Thread.sleep(200)
            }
            stopSelf(startId)
        }

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "onDestroy")
    }

}
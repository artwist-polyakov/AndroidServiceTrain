package com.awst.androidservicetrain

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log

internal class MusicService : Service() {

    private var mediaPlayer: MediaPlayer? = null


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(LOG_TAG, "onStartCommand | flags: $flags, startId: $startId")

        val songUrl = intent?.getStringExtra("song_url")
        if (songUrl != null) {
            Log.d(LOG_TAG, "onStartCommand -> song url exists")

            mediaPlayer?.setDataSource(songUrl)
            mediaPlayer?.prepareAsync()

            mediaPlayer?.setOnPreparedListener {
                it?.start()
            }

            mediaPlayer?.setOnCompletionListener {
                stopSelf()
            }
        }

        Log.d(LOG_TAG, "onStartCommand -> before return")

        return Service.START_NOT_STICKY
    }

    /*
    Наследование от Service заставляет нас переопределить метод onBind.
    Поговорим о нём, когда будем реализовывать bound-сервис.
    Сейчас же мы просто вернём из метода null.
     */
    override fun onBind(intent: Intent?): IBinder? = null

    // Инициализация ресурсов
    override fun onCreate() {
        super.onCreate()

        Log.d(LOG_TAG, "onCreate")
        mediaPlayer = MediaPlayer()
    }

    // Освобождение ресурсов
    override fun onDestroy() {
        Log.d(LOG_TAG, "onDestroy")

        mediaPlayer?.setOnPreparedListener(null)
        mediaPlayer?.setOnCompletionListener(null)
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private companion object {
        const val LOG_TAG = "MusicService"
    }
}
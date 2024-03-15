package com.awst.androidservicetrain

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log

internal class MusicService : Service() {

    private var mediaPlayer: MediaPlayer? = null

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
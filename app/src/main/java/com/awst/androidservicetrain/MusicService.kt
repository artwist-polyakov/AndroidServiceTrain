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

        return Service.START_REDELIVER_INTENT
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
        createNotificationChannel()
        mediaPlayer = MediaPlayer()
    }

    private fun createNotificationChannel() {
        // Создание каналов доступно только с Android 8.0
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return
        }

        val channel = NotificationChannel(
            /* id= */ NOTIFICATION_CHANNEL_ID,
            /* name= */ "Music service",
            /* importance= */ NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.description = "Service for playing music"

        // Регистрируем канал уведомлений
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
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
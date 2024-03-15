package com.awst.androidservicetrain

import android.app.Service
import android.content.Intent
import android.os.IBinder

internal class MusicService : Service() {
    /*
    Наследование от Service заставляет нас переопределить метод onBind.
    Поговорим о нём, когда будем реализовывать bound-сервис.
    Сейчас же мы просто вернём из метода null.
     */
    override fun onBind(intent: Intent?): IBinder? = null

}
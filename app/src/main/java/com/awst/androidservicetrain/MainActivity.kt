package com.awst.androidservicetrain

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.startMusicServiceButton).setOnClickListener {
            // Создаём intent для запуска сервиса
            val intent = Intent(this, MusicService::class.java).apply {
                putExtra("song_url", SONG_URL)
            }

            // Стартуем сервис
            startService(intent)
        }

        findViewById<Button>(R.id.startProgressServiceButton).setOnClickListener {
            // Создаём intent для запуска сервиса
            val intent = Intent(this, ProgressService::class.java)
            // Стартуем сервис
            startService(intent)
        }

    }

    private companion object {
        const val SONG_URL = "https://rus.hitmotop.com/get/music/20191128/Lenka_-_Blue_Skies_Revoke_Remix_67448680.mp3"
    }

}
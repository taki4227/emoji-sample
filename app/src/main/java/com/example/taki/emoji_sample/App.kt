package com.example.taki.emoji_sample

import android.app.Application
import android.util.Log
import androidx.core.provider.FontRequest
import androidx.emoji.text.EmojiCompat
import androidx.emoji.text.FontRequestEmojiCompatConfig

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        setupEmoji()
    }

    private fun setupEmoji() {
        val fontRequest = FontRequest(
            "com.google.android.gms.fonts",
            "com.google.android.gms",
            "Noto Color Emoji Compat",
            R.array.com_google_android_gms_fonts_certs
        )
        val config = FontRequestEmojiCompatConfig(applicationContext, fontRequest)
            .setReplaceAll(true)
            .registerInitCallback(object : EmojiCompat.InitCallback() {
                override fun onInitialized() {
                    Log.i("smash", "EmojiCompat initialized")
                }

                override fun onFailed(throwable: Throwable?) {
                    Log.e("smash", "EmojiCompat initialization failed", throwable)
                }
            })
        EmojiCompat.init(config)
    }
}
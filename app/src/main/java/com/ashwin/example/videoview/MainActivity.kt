package com.ashwin.example.videoview

import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mediaController = MediaController(this)
        mediaController.setAnchorView(my_videoview)
        my_videoview.setMediaController(mediaController)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && BuildConfig.DEBUG) {
            WebView.setWebContentsDebuggingEnabled(true)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            my_webview.settings.safeBrowsingEnabled = false
        }
        my_webview.settings.javaScriptEnabled = true
        my_webview.settings.allowFileAccess = true
        my_webview.settings.domStorageEnabled = true
        my_webview.settings.allowUniversalAccessFromFileURLs = true

        my_webview.addJavascriptInterface(JavascriptInterface(), "LingVisSDK")

        my_webview.loadUrl("file:///android_asset/subtitles.html")

        my_webview.setBackgroundColor(Color.TRANSPARENT)

        val uri: Uri = Uri.parse("https://sprakkraft.org/rc/articles/sv/sprak-start/lesson01/dialog.mp4")
        my_videoview.setVideoURI(uri)

        my_videoview.requestFocus()

        my_videoview.start()

        my_videoview.setOnPreparedListener {
            Log.w("video-view", "Video prepared")
            mediaController.show(1000)
        }
    }

    private inner class JavascriptInterface
    {
        @android.webkit.JavascriptInterface
        fun getTime(): Int {
            return my_videoview.currentPosition
        }
    }
}

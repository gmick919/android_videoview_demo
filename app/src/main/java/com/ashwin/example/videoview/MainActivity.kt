package com.ashwin.example.videoview

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mediaController = MediaController(this)
        mediaController.setAnchorView(my_videoview)
        my_videoview.setMediaController(mediaController)

        val path = "android.resource://" + packageName + "/" + R.raw.tintin
        val uri: Uri = Uri.parse(path)
        my_videoview.setVideoURI(uri)

        my_videoview.requestFocus()

        my_videoview.start()

        my_videoview.setOnPreparedListener {
            Log.w("video-view", "Video prepared")
            mediaController.show(1000)
        }
    }
}

package com.nbs.dicodinglive.detailvideo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.ui.platform.ComposeView
import com.nbs.dicodinglive.R
import com.nbs.dicodinglive.video.VideoPlayer

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.apply {
            title = "Detail Video"
            setDisplayHomeAsUpEnabled(true)
        }

        findViewById<ComposeView>(R.id.compose_view).setContent {
            VideoPlayer(lifecycle = this.lifecycle, videoId = "CRTIrK3Kku8")
        }
    }
}
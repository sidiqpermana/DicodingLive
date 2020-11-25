package com.nbs.dicodinglive.video

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.stringResource
import com.nbs.dicodinglive.R
import com.nbs.dicodinglive.main.Page

class VideoPlayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Page(title = stringResource(id = R.string.title_activity_video_player)){
                VideoPlayer(
                    lifecycle = this.lifecycle,
                    videoId = "CRTIrK3Kku8"
                )
            }
        }
    }
}
package com.nbs.dicodinglive.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.stringResource
import com.nbs.dicodinglive.R
import com.nbs.dicodinglive.detailvideo.DetailActivity
import com.nbs.dicodinglive.video.VideoPlayerActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Page(
                title = stringResource(id = R.string.title_calculate_radius),
                body = {
                    EllipseCalculation(
                        openVideoPlayer = {
                            startActivity(Intent(this, VideoPlayerActivity::class.java))
                        },
                        openDetailVideo = {
                            startActivity(Intent(this, DetailActivity::class.java))
                        }
                    )
                }
            )
        }
    }
}


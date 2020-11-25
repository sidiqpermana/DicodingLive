package com.nbs.dicodinglive.video

import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.ui.tooling.preview.Preview
import com.nbs.dicodinglive.R
import com.nbs.dicodinglive.ui.DicodingLiveTheme
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


@Composable
fun VideoPlayer(lifecycle: Lifecycle?, videoId: String){
    Column{
        YoutubePlayer(modifier = Modifier.fillMaxWidth(), lifecycle = lifecycle, videoId = videoId)
        Divider(color = Color.Transparent, modifier = Modifier.preferredHeight(16.dp))
        Text(text = stringResource(id = R.string.video_description),
            modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp))
    }
}

@Composable
fun YoutubePlayer(
    modifier: Modifier,
    lifecycle: Lifecycle?,
    videoId: String
){
    AndroidView(viewBlock = {
        YouTubePlayerView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }, modifier = modifier, update = {
        lifecycle?.addObserver(it)

        it.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })
    })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    DicodingLiveTheme {
        VideoPlayer(lifecycle = null, videoId = "CRTIrK3Kku8")
    }
}
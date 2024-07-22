package com.jerry.assessment.activity

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.media3.exoplayer.ExoPlayer
import com.jerry.assessment.compose.player.VideoFullScreen
import com.jerry.assessment.contants.KEY_EPISODE
import com.jerry.assessment.data.Episode
import com.jerry.assessment.ui.theme.AssessmentprojectTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class VideoPlayerActivity: ComponentActivity() {

    @Inject
    lateinit var exoPlayer: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent

        val episode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(KEY_EPISODE, Episode::class.java)
        } else {
            intent.getParcelableExtra(KEY_EPISODE)
        }

        setContent {
            AssessmentprojectTheme {
                episode?.let {
                    VideoFullScreen(
                        exoPlayer = exoPlayer,
                        episode = it,
                        onCancel = {
                            this.finish()
                        }
                    )
                }
            }
        }
    }

}
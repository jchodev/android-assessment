package com.jerry.assessment.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jerry.assessment.compose.AssessmentApp
import com.jerry.assessment.contants.KEY_EPISODE
import com.jerry.assessment.ui.theme.AssessmentprojectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssessmentprojectTheme {
                AssessmentApp(
                    onPlayVideoClick = {
                        val intent = Intent(this, VideoPlayerActivity::class.java)
                        intent.putExtra(KEY_EPISODE, it)
                        startActivity(intent)
                    }
                )
            }
        }
    }

}


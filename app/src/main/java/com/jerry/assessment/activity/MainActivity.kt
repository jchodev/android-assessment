package com.jerry.assessment.activity

import android.Manifest
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.MoreExecutors
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

        //
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 123)
            }
        }
    }


}


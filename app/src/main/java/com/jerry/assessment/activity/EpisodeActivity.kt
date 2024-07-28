package com.jerry.assessment.activity

import android.content.ComponentName
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.util.Log
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import com.jerry.assessment.service.PlaybackService
import com.jerry.assessment.ui.theme.AssessmentprojectTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class EpisodeActivity: ComponentActivity() {

//    @Inject
//    lateinit var player: ExoPlayer

    var duration: Int = 0
    private var controller: MediaController? = null
    private var mediaControllerFuture: ListenableFuture<MediaController>? = null

    // Elapsed Time
    private var stopwatchStartTime: Long = 0
    private var elapsedTime: Long = 0
    private var isStopwatchRunning: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssessmentprojectTheme {
                Column {
                    Text("this is hihi")

                    controller?.let {
                        if (it.playbackState == Player.STATE_IDLE || it.playbackState == Player.STATE_ENDED) {
                            Button(onClick = { playMedia() }) {
                                Text("can play")
                            }
                        } else if (it.playbackState == Player.STATE_READY || it.playbackState == Player.STATE_BUFFERING) {
                            Button(onClick = { updateUIWithPlayback() }) {
                                Text("can not  play ?")
                            }
                        }
                    }
                }
            }
        }

        initializeMediaController()
        setupUIControls()

        //playMedia()
    }

    private fun initializeMediaController() {
        val sessionToken = SessionToken(this, ComponentName(this, PlaybackService::class.java))
        mediaControllerFuture = MediaController.Builder(this, sessionToken).buildAsync()
        mediaControllerFuture?.apply {
            addListener(Runnable {
                controller = get()
                updateUIWithMediaController(controller!!)
                // Ensure media is played appropriately based on state
                log("INITIAL STATE = ${controller!!.playbackState}")
                handlePlaybackBasedOnState()
            }, MoreExecutors.directExecutor())
        }
    }

    private fun updateUIWithMediaController(controller: MediaController) {
        controller.addListener(object : Player.Listener {

            override fun onPositionDiscontinuity(
                oldPosition: Player.PositionInfo, newPosition: Player.PositionInfo, reason: Int
            ) {
                val currentposition = controller.currentPosition.toInt() / 1000
//                binding.seekbar.progress = currentposition
//                binding.time.text = getTimeString(currentposition)
//                binding.duration.text = getTimeString(controller.duration.toInt() / 1000)
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                when (playbackState) {
                    Player.STATE_BUFFERING -> {
                        showBuffering()
                        pauseStopwatch()
                    }
                    Player.STATE_READY -> {
                        hideBuffering()
                        updatePlayPauseButton(controller.playWhenReady)
                        if (controller.playWhenReady){
                            startStopwatch()
                        }
                    }

                    Player.STATE_ENDED -> handlePlaybackEnded()
                    Player.STATE_IDLE -> {
                        pauseStopwatch()
                    }
                }
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                updatePlayPauseButton(isPlaying)

                if (isPlaying) {
                    startStopwatch()
                } else {
                    pauseStopwatch()
                }

                duration = controller.duration.toInt() / 1000
//                binding.seekbar.max = duration
//                binding.time.text = "0:00"
//                binding.duration.text = getTimeString(duration)
            }
        })

        updatePlayPauseButton(controller.playWhenReady)


        val handler = Handler(Looper.getMainLooper())
        handler.post(object : Runnable {
            override fun run() {
                val currentposition = controller.currentPosition.toInt() / 1000
//                binding.seekbar.progress = currentposition
//                binding.time.text = getTimeString(currentposition)
//                binding.duration.text = getTimeString(duration)
                handler.postDelayed(this, 1000)
            }
        })
    }

    private fun handlePlaybackBasedOnState() {
        if (controller!!.playbackState == Player.STATE_IDLE || controller!!.playbackState == Player.STATE_ENDED) {
            playMedia()
        } else if (controller!!.playbackState == Player.STATE_READY || controller!!.playbackState == Player.STATE_BUFFERING) {
            updateUIWithPlayback()
        }
    }

    private fun playMedia() {
        val mediaItem = MediaItem.Builder()
            .setMediaId("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-4.mp3")
            .setMediaMetadata(
                MediaMetadata.Builder()
                    //.setFolderType(MediaMetadata.FOLDER_TYPE_ALBUMS)
                    .setArtworkUri(Uri.parse("https://i.pinimg.com/736x/4b/02/1f/4b021f002b90ab163ef41aaaaa17c7a4.jpg"))
                    .setAlbumTitle("SoundHelix")
                    .setDisplayTitle("Song 1")
                    .build()
            ).build()

        controller!!.setMediaItem(mediaItem)
        controller!!.prepare()
        controller!!.play()
    }

    private fun updateUIWithPlayback() {
        hideBuffering()
        updatePlayPauseButton(controller!!.playWhenReady)
        if (controller!!.playWhenReady) {
            val currentposition = controller!!.currentPosition.toInt() / 1000
//            binding.seekbar.progress = currentposition
//            binding.time.text = getTimeString(currentposition)
//            binding.duration.text = getTimeString(controller.duration.toInt() / 1000)
        }
    }


    private fun setupUIControls() {
//        binding.btnPlayPause.setOnClickListener {
//            if (controller.playWhenReady) {
//                controller.pause()
//            } else {
//                controller.play()
//            }
//        }
//
//        binding.seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
//            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                if (fromUser) controller.seekTo(progress.toLong() * 1000)
//                binding.time.text = getTimeString(progress)
//                binding.duration.text = getTimeString(duration)
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//                pauseStopwatch()
//            }
//
//            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//                if (controller.playWhenReady) {
//                    startStopwatch()
//                }
//            }
//        })
    }

    private fun startStopwatch() {
        if (!isStopwatchRunning) {
            stopwatchStartTime = SystemClock.elapsedRealtime() - elapsedTime
            isStopwatchRunning = true
        }
    }

    private fun pauseStopwatch() {
        if (isStopwatchRunning) {
            elapsedTime = SystemClock.elapsedRealtime() - stopwatchStartTime
            isStopwatchRunning = false
        }
    }

    private fun reportListenTime() {
        if (isStopwatchRunning) {
            elapsedTime = SystemClock.elapsedRealtime() - stopwatchStartTime
        }
        // Convert milliseconds to seconds
        val listenedTimeInSeconds = elapsedTime / 1000

        Toast.makeText(this@EpisodeActivity, "Total listened time: $listenedTimeInSeconds seconds", Toast.LENGTH_SHORT).show()
        log("Total listened time: $listenedTimeInSeconds seconds")
    }

    private fun updatePlayPauseButton(isPlaying: Boolean) {
        //binding.btnPlayPause.setImageResource(if (isPlaying) R.drawable.pause else R.drawable.play)
    }

    private fun getTimeString(seconds: Int): String {
        val mins = seconds / 60
        val secs = seconds % 60
        return String.format("%02d:%02d", mins, secs)
    }

    private fun showBuffering() {
        //binding.progressBar.show()
    }

    private fun hideBuffering() {
        //binding.progressBar.hide()
    }

    private fun handlePlaybackEnded() {
//        binding.btnPlayPause.setImageResource(R.drawable.play)
//        binding.seekbar.progress = 0
//        binding.time.text = getTimeString(duration)
//        binding.duration.text = getTimeString(duration)
//        binding.progressBar.visibility = View.GONE
        pauseStopwatch()
        reportListenTime()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaControllerFuture?.let {
            MediaController.releaseFuture(it)
        }
    }

    private fun log(message: String) {
        Log.e("=====[DebzMediaPlayer]=====", message)
    }
}
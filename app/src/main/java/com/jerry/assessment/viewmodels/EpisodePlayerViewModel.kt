package com.jerry.assessment.viewmodels

import android.app.Notification
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.OptIn
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaController
import androidx.media3.ui.PlayerNotificationManager
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import com.jerry.assessment.data.Episode
import com.jerry.assessment.service.PlaybackService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EpisodePlayerViewModel @Inject constructor(
    private val exoPlayer: ExoPlayer,
    @ApplicationContext private val context: Context,
    private val mediaControllerFuture: ListenableFuture<MediaController>,
) : ViewModel() {


    private lateinit var controller: MediaController

    //for display , because it have to support
    //player bar was playing Episode 1 but user browser Episode 2
    private val _selectedEpisode = MutableStateFlow<Episode?>(null)
    val selectedEpisode = _selectedEpisode.asStateFlow()

    private val _episodePlayerState = MutableStateFlow(EpisodePlayerState())
    val episodePlayerState = _episodePlayerState.asStateFlow()

    private var timerJob: Job? = null

    init {
        mediaControllerFuture.apply {
            addListener(Runnable {
                controller = get()
                Timber.d("INITIAL STATE = ${controller.playbackState}")
            }, MoreExecutors.directExecutor())
        }

        //add interface
        exoPlayer.addListener(
            object : Player.Listener {

                override fun onPlayerError(error: PlaybackException) {
                    _episodePlayerState.value = episodePlayerState.value.copy(
                        errorMessage = error.localizedMessage ?: error.message ?: "Unknown Play Error"
                    )
                }

                override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                    Timber.d("onMediaItemTransition: ${mediaItem?.mediaMetadata?.title}")
                    super.onMediaItemTransition(mediaItem, reason)

                }

                override fun onPlaybackStateChanged(playbackState: Int) {
                    /*
                      int STATE_IDLE = 1;

                      /**
                       * The player is not able to immediately play the media, but is doing work toward being able to do
                       * so. This state typically occurs when the player needs to buffer more data before playback can
                       * start.
                       */
                      int STATE_BUFFERING = 2;

                      /**
                       * The player is able to immediately play from its current position. The player will be playing if
                       * {@link #getPlayWhenReady()} is true, and paused otherwise.
                       */
                      int STATE_READY = 3;

                      /** The player has finished playing the media. */
                      int STATE_ENDED = 4;
                     */
                    Timber.d("EpisodePlayerViewModel::onPlaybackStateChanged::playbackState::${playbackState}")
                    _episodePlayerState.value = episodePlayerState.value.copy(
                        playbackState = playbackState,
                    )
                    if (playbackState == Player.STATE_READY) {
                        Timber.d("exoPlayer.duration::"+ exoPlayer.duration)
                        _episodePlayerState.value = episodePlayerState.value.copy(
                            //isPlaying = true,
                            duration = exoPlayer.duration
                        )
                    }

                }

                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    _episodePlayerState.value = episodePlayerState.value.copy(
                        isPlaying = isPlaying
                    )
                    Timber.d("state is playing :${episodePlayerState.value.isPlaying} and status is ${episodePlayerState.value.playbackState}")

                    if (isPlaying){
                        startTimerJob()
                    } else {
                        stopTimerJob()
                    }
                }

            }
        )

    }

//    private fun playMedia() {
//        val mediaItem = MediaItem.Builder()
//            .setMediaId("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-4.mp3")
//            .setMediaMetadata(
//                MediaMetadata.Builder()
//                    .setFolderType(MediaMetadata.FOLDER_TYPE_ALBUMS)
//                    .setArtworkUri(Uri.parse("https://i.pinimg.com/736x/4b/02/1f/4b021f002b90ab163ef41aaaaa17c7a4.jpg"))
//                    .setAlbumTitle("SoundHelix")
//                    .setDisplayTitle("Song 1")
//                    .build()
//            ).build()
//
//        controller.setMediaItem(mediaItem)
//        controller.prepare()
//        controller.play()
//    }

    private fun startTimerJob() {
        timerJob?.cancel() // Cancel any existing timer job
        timerJob = viewModelScope.launch {
            while (episodePlayerState.value.isPlaying) {
                Timber.d("exoPlayer.currentPosition::"+ exoPlayer.currentPosition)
                _episodePlayerState.value = episodePlayerState.value.copy(
                    currentPosition = exoPlayer.currentPosition
                )
                delay(ONE_SECOND)
            }
        }
    }

    fun stopTimerJob() {
        timerJob?.cancel()
        timerJob = null
    }

    fun setSelectedEpisode(episode: Episode){
        _selectedEpisode.value = episode
    }

    fun playPause(episode: Episode){
        if (episodePlayerState.value.playbackState == Player.STATE_IDLE
            || episodePlayerState.value.episode?.id != episode.id
        ){

            clearEpisode()
            _episodePlayerState.value = episodePlayerState.value.copy(
                episode = episode,
            )

            playEpisode(episode)
        } else if (episodePlayerState.value.isPlaying) {
            exoPlayer.pause()
        } else if (!episodePlayerState.value.isPlaying) {
            exoPlayer.play()
        }
    }

    fun clearError(){
        _episodePlayerState.value = _episodePlayerState.value.copy(
            errorMessage = null
        )
    }

    fun clearEpisode(){
        if (exoPlayer.isPlaying){
            exoPlayer.stop()
        }
        //clear the state
        _episodePlayerState.value = episodePlayerState.value.copy(
            errorMessage = null,
            currentPosition = 0,
            episode = null,
        )
    }

    @OptIn(UnstableApi::class)
    private fun playEpisode(episode: Episode) {
        //will be move to init {} level soon
        val mediaItem = MediaItem.Builder()
            .setMediaId(episode.url)
            //.setUri(episode.url)
            //.setMediaId(episode.id.toString())
            .setMediaMetadata(
                MediaMetadata.Builder()
                    .setArtworkUri(Uri.parse(episode.podcast.imageUrl))
                    .setTitle(episode.title)
                    .build()
            )
            .build()

        //TBC, it is not good to start service at viewmodel ?
        startPlayerService()

        controller.setMediaItem(mediaItem)
        controller.prepare()
        controller.play()

    }

    //@OptIn(UnstableApi::class)
//    fun startNotificationPlayer(){
//        // Build a PendingIntent that can be used to launch the UI.
//        val sessionActivityPendingIntent =
//            context.packageManager?.getLaunchIntentForPackage(context.packageName)
//                ?.let { sessionIntent ->
//                    PendingIntent.getActivity(
//                        context,
//                        SESSION_INTENT_REQUEST_CODE,
//                        sessionIntent,
//                        PendingIntent.FLAG_IMMUTABLE
//                    )
//                }
//
//        // Create a new MediaSession.
//        mediaSession = MediaSession.Builder(context, exoPlayer)
//            .setSessionActivity(sessionActivityPendingIntent!!).build()
//
//        notificationManager =
//            MediaNotificationManager(
//                context,
//                mediaSession.token,
//                exoPlayer,
//                PlayerNotificationListener()
//            )
//
//        notificationManager.showNotificationForPlayer(exoPlayer)
//    }

    /**
     * Listen for notification events.
     */
    @UnstableApi
    private inner class PlayerNotificationListener :
        PlayerNotificationManager.NotificationListener {

        override fun onNotificationPosted(
            notificationId: Int,
            notification: Notification,
            ongoing: Boolean
        ) {

        }

        override fun onNotificationCancelled(notificationId: Int, dismissedByUser: Boolean) {

        }
    }

    override fun onCleared() {
        super.onCleared()
        stopTimerJob()
        stopPlayerService()
    }

    private fun startPlayerService() {
        //TBC: need to check service started or not
        val intent = Intent(context, PlaybackService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent)
        } else {
            context.startService(intent)
        }
    }

    private fun stopPlayerService(){
        context.stopService(Intent(context, PlaybackService::class.java))
    }

    companion object {
        private const val ONE_SECOND  = 1000L
    }
}

data class EpisodePlayerState(

    val episode: Episode? = null,

    val isPlaying: Boolean = false,

    val playbackState: Int = Player.STATE_IDLE,
    val currentPosition: Long = 0L,
    val duration: Long = 0L,

    val errorMessage: String? = null
)

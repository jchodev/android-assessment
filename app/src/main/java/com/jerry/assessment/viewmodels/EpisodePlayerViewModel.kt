package com.jerry.assessment.viewmodels

import androidx.annotation.OptIn
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.jerry.assessment.data.Episode
import dagger.hilt.android.lifecycle.HiltViewModel
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
) : ViewModel() {

    private val _selectedEpisode = MutableStateFlow<Episode?>(null)
    val selectedEpisode = _selectedEpisode.asStateFlow()

    private val _episodePlayerState = MutableStateFlow(EpisodePlayerState())
    val episodePlayerState = _episodePlayerState.asStateFlow()

    private var timerJob: Job? = null

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

            playEpisode(episode.url)
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
    private fun playEpisode(episodeUrl: String) {
        exoPlayer.addListener(
            object : Player.Listener {

                override fun onPlayerError(error: PlaybackException) {
                    _episodePlayerState.value = episodePlayerState.value.copy(
                        errorMessage = error.localizedMessage ?: error.message ?: "Unknown Play Error"
                    )
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
        val mediaItem = MediaItem.fromUri(episodeUrl)

        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()
    }

    override fun onCleared() {
        super.onCleared()
        stopTimerJob()
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

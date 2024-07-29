package com.jerry.assessment.viewmodels.player


import android.content.Context
import android.net.Uri

import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaController
import app.cash.turbine.test

import com.google.common.util.concurrent.ListenableFuture
import com.jerry.assessment.MockStubs

import com.jerry.assessment.data.toEpisode
import com.jerry.assessment.viewmodels.EpisodePlayerViewModel

import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just

import io.mockk.mockk
import io.mockk.mockkClass
import io.mockk.mockkStatic
import io.mockk.slot
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher

import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import timber.log.Timber

@OptIn(ExperimentalCoroutinesApi::class)
class EpisodePlayerViewModelTest {

    @MockK
    private lateinit var mediaControllerFuture: ListenableFuture<MediaController>

    @MockK
    private lateinit var exoPlayer: ExoPlayer

    @MockK
    private lateinit var context: Context

    private lateinit var viewModel: EpisodePlayerViewModel

    private lateinit var mockMediaController: MediaController

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        MockKAnnotations.init(this)
        mockkStatic(Timber::class)
        mockkStatic(Uri::class)

        mockMediaController = mockkClass(MediaController::class)
        //for init{}
        every { mediaControllerFuture.addListener(any(), any()) }just Runs
        every { mediaControllerFuture.addListener(any(), any()) } answers {
            val runnable = firstArg<Runnable>()
            runnable.run()
        }
        every { mediaControllerFuture.get() } returns mockMediaController
        every { exoPlayer.addListener(any()) } just Runs
        every { mockMediaController.playbackState } returns  Player.STATE_IDLE

        viewModel = EpisodePlayerViewModel(
            exoPlayer = exoPlayer,
            mediaControllerFuture = mediaControllerFuture,
            context = context
        )

    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    private val mockDuration = 2L
    private val mockCurrentPosition = 1L

    private fun mockBasicReturn(){
        every { exoPlayer.setMediaItem(any()) } just Runs
        every { exoPlayer.prepare() } just Runs
        every { exoPlayer.isPlaying } returns true
        every { exoPlayer.play() } just Runs
        every { exoPlayer.stop() } just Runs
        every { exoPlayer.addListener(any()) } just Runs
        every { Uri.parse(any()) } returns mockk<Uri>()
        every { exoPlayer.duration } returns mockDuration
        every { exoPlayer.currentPosition } returns mockCurrentPosition

        every { mockMediaController.setMediaItem(any()) } just Runs
        every { mockMediaController.prepare() } just Runs
        every { mockMediaController.play() } just Runs

        every { context.startService(any()) } returns mockk()
    }


    @Test
    fun `test EpisodePlayerViewModel playPause starts new episode`() = runTest {
        mockBasicReturn()

        //initial
        val episode = MockStubs.mockEpisodeDTOS[0].toEpisode()
        viewModel.playPause(episode)

        // Change the state to ready
        val listener = slot<Player.Listener>()
        verify { exoPlayer.addListener(capture(listener)) }
        listener.captured.onPlaybackStateChanged(Player.STATE_READY)


        viewModel.episodePlayerState.test {
            val item = awaitItem()
            Assertions.assertEquals(episode, item.episode)
            Assertions.assertEquals(Player.STATE_READY, item.playbackState)
        }
    }



    @Test
    fun `test EpisodePlayerViewModel playPause play again because it was pause already`() = runTest {
        mockBasicReturn()
        every { exoPlayer.isPlaying } returns true
        every { exoPlayer.pause() } just Runs

        val episode = MockStubs.mockEpisodeDTOS[0].toEpisode()
        //start first
        viewModel.playPause(episode)

        // Change the state to ready and play
        val listener = slot<Player.Listener>()
        verify { exoPlayer.addListener(capture(listener)) }
        listener.captured.onPlaybackStateChanged(Player.STATE_READY)

        //pause
        viewModel.playPause(episode)
        listener.captured.onIsPlayingChanged(false)

        //verify is running
        viewModel.playPause(episode)
        verify { exoPlayer.play() }
    }

    @Test
    fun `test EpisodePlayerViewModel when start time when trigger play`() = runTest {
        mockBasicReturn()
        every { exoPlayer.currentPosition } returnsMany listOf(0L, 1000L, 2000L)

        //initial
        val episode = MockStubs.mockEpisodeDTOS[0].toEpisode()
        viewModel.playPause(episode)

        // Change the state to ready
        val listener = slot<Player.Listener>()
        verify { exoPlayer.addListener(capture(listener)) }
        listener.captured.onPlaybackStateChanged(Player.STATE_READY)
        listener.captured.onIsPlayingChanged(true) //click start timer


        // Then
        advanceTimeBy(1000)
        println( "1:"+viewModel.episodePlayerState.value.currentPosition)
        Assertions.assertEquals(1000L, viewModel.episodePlayerState.value.currentPosition)

        // Then
        advanceTimeBy(1000)
        println( "2:"+viewModel.episodePlayerState.value.currentPosition)
        Assertions.assertEquals(2000L, viewModel.episodePlayerState.value.currentPosition)

        listener.captured.onIsPlayingChanged(false) //stop
    }

}
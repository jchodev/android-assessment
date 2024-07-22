package com.jerry.assessment.viewmodels.player

import android.net.Uri
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import app.cash.turbine.test
import com.google.common.base.Verify.verify
import com.jerry.assessment.MockStubs
import com.jerry.assessment.data.Episode
import com.jerry.assessment.data.repository.PodcastRepository
import com.jerry.assessment.data.toEpisode
import com.jerry.assessment.viewmodels.EpisodePlayerViewModel
import com.jerry.assessment.viewmodels.PodcastListViewModel
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
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import timber.log.Timber

@OptIn(ExperimentalCoroutinesApi::class)
class EpisodePlayerViewModelTest {

    private lateinit var viewModel: EpisodePlayerViewModel
    private lateinit var exoPlayer: ExoPlayer
    private lateinit var testDispatcher: TestDispatcher

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        mockkStatic(Timber::class)
        mockkStatic(Uri::class)
        exoPlayer = mockkClass(ExoPlayer::class)
        viewModel = EpisodePlayerViewModel(exoPlayer = exoPlayer)
        MockKAnnotations.init(this)
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
            assertEquals(episode, item.episode)
            assertEquals(Player.STATE_READY, item.playbackState)
        }
    }

    @Test
    fun `test EpisodePlayerViewModel playPause pause because it is playing`() = runTest {
        mockBasicReturn()
        every { exoPlayer.pause() } just Runs

        val episode = MockStubs.mockEpisodeDTOS[0].toEpisode()
        // first
        viewModel.playPause(episode)

        // Change the state to ready
        val listener = slot<Player.Listener>()
        verify { exoPlayer.addListener(capture(listener)) }
        listener.captured.onPlaybackStateChanged(Player.STATE_READY)


        //pause it
        viewModel.playPause(episode)
        listener.captured.onIsPlayingChanged(false)
        every { exoPlayer.isPlaying } returns false

        //verify is not playing
        viewModel.episodePlayerState.test {
            val item = awaitItem()
            assertEquals(episode, item.episode)
            assertEquals(false, item.isPlaying)
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
package com.jerry.assessment.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.jerry.assessment.compose.navhost.AssessmentNavHost

import com.jerry.assessment.compose.player.EpisodePlayerBar
import com.jerry.assessment.data.Episode
import com.jerry.assessment.viewmodels.EpisodePlayerViewModel

@Composable
fun AssessmentApp(
    episodePlayerViewModel: EpisodePlayerViewModel = hiltViewModel(),
    onPlayVideoClick: (Episode) -> Unit,
) {
    val navController = rememberNavController()
    val episodePlayerState = episodePlayerViewModel.episodePlayerState.collectAsStateWithLifecycle().value

    Scaffold(
        bottomBar = {
            episodePlayerState.episode?.let {
                EpisodePlayerBar(
                    episodePlayerState = episodePlayerState,
                    onPlayPauseClick = {
                        episodePlayerViewModel.playPause(it)
                    },
                    onBarClick = {
                        val currentDestination = navController.currentDestination
                        currentDestination?.let { destination ->
                            if (destination.route == null || !destination.route!!.contains(Episode::class.java.name)) {
                                navController.navigate(it)
                            } else if (destination.route != null || destination.route!!.contains(Episode::class.java.name)) {
                                //override the current page episode content
                                episodePlayerViewModel.setSelectedEpisode(it)
                            }
                        }
                    }
                )
            }
        }
    ){
        AssessmentNavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            episodePlayerViewModel = episodePlayerViewModel,
            onPlayVideoClick = onPlayVideoClick
        )
    }

}


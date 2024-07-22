package com.jerry.assessment.compose.navhost

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jerry.assessment.compose.PodcastList
import com.jerry.assessment.compose.player.EpisodePlayerScreen
import com.jerry.assessment.compose.podcastdetail.PodcastDetailScreen
import com.jerry.assessment.compose.podcastlist.PodcastListScreen
import com.jerry.assessment.data.Episode
import com.jerry.assessment.data.EpisodeType
import com.jerry.assessment.data.Podcast
import com.jerry.assessment.data.PodcastType
import com.jerry.assessment.viewmodels.EpisodePlayerViewModel
import kotlin.reflect.typeOf

@Composable
fun AssessmentNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    episodePlayerViewModel: EpisodePlayerViewModel,
    onPlayVideoClick: (Episode) -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = PodcastList
    ) {

        composable<PodcastList> {
            PodcastListScreen(
                onPodcastClick = {
                    navController.navigate(it)
                }
            )
        }
        composable<Podcast> { backStackEntry->
            val podcast: Podcast = backStackEntry.toRoute()

            PodcastDetailScreen(
                selectedPodcast = podcast,
                onBackClick = { navController.navigateUp() },
                onEpisodeClick = {
                    navController.navigate(it)
                }
            )
        }

        composable<Episode>(
            typeMap = mapOf(
                typeOf<Podcast>() to PodcastType,
                typeOf<Episode>() to EpisodeType,
            )
        ) {backStackEntry->
            val episode: Episode = backStackEntry.toRoute()
            EpisodePlayerScreen(
                episode = episode,
                viewModel = episodePlayerViewModel,
                onBackClick = { navController.navigateUp() },
                onPlayVideoClick = onPlayVideoClick,
            )
        }
    }
}
package com.jerry.assessment.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jerry.assessment.data.Episode
import com.jerry.assessment.data.Podcast
import com.jerry.assessment.data.repository.EpisodeRepository
import com.jerry.assessment.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PodcastDetailViewModel @Inject constructor(
    private val episodeRepository: EpisodeRepository,
) : ViewModel() {

    private val _podcastDetailState = MutableStateFlow(PodcastDetailState())
    val podcastDetailState = _podcastDetailState.asStateFlow()

    fun getPodcastsByPodcast(podcast: Podcast) {
        viewModelScope.launch {
            _podcastDetailState.value = podcastDetailState.value.copy(
                errorMessage = null,
                isLoading = true
            )
            episodeRepository.getPodcastsByPodcastId(podcast.id).collectLatest {result ->
                when (result) {
                    is Resource.Success -> {
                        _podcastDetailState.value = podcastDetailState.value.copy(
                            data =  result.data
                        )
                    }
                    is Resource.Error -> {
                        _podcastDetailState.value = podcastDetailState.value.copy(
                            errorMessage = result.message
                        )
                    }
                }
            }
            _podcastDetailState.value = podcastDetailState.value.copy(
                isLoading = false
            )
        }
    }

    fun clearError(){
        _podcastDetailState.value = _podcastDetailState.value.copy(
            errorMessage = null
        )
    }
}

data class PodcastDetailState(
    val isLoading: Boolean = false,
    val data: List<Episode> = emptyList(),
    val errorMessage: String? = null
)
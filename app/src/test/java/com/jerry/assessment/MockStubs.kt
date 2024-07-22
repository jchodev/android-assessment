package com.jerry.assessment

import com.jerry.assessment.data.Episode
import com.jerry.assessment.data.Podcast
import com.jerry.assessment.data.remote.dto.EpisodeDTO
import com.jerry.assessment.data.remote.dto.PodcastDTO
import com.jerry.assessment.data.toEpisode
import com.jerry.assessment.data.toPodcast
import com.jerry.assessment.utilities.Resource

class MockStubs {

    companion object {
        val mockPodcastDTOS = (1..40).map { i ->
            PodcastDTO(
                id = i.toLong(),
                author = "author$i",
                image = "image$i",
                description = "description$i",
                link = "link$i",
                title = "title$i"
            )
        }

        val mockEpisodeDTOS = (1..20).map { i ->
            EpisodeDTO(
                description = "description$i",
                duration = i.toLong(),
                id = i.toLong(),
                mimeType = if (i % 2 == 0) "video/mp4" else "audio/mpeg",
                published = System.currentTimeMillis() / 1000,
                type = "type$i",
                title = "title$i",
                url = "url$i"
            )
        }

        val mockServerErrorCode = 400
        val mockExceptionStr = "Some Error"
        val mockNormalException = Exception(mockExceptionStr)

        val mockSuccessPodcastListResourceDTO : Resource<List<Podcast>> = Resource.Success(data = mockPodcastDTOS.map { it.toPodcast() })
        val mockErrorPodcastListResourceDTO : Resource<List<Podcast>> = Resource.Error(message = mockExceptionStr)

        val mockSuccessEpisodeListResourceDTO : Resource<List<Episode>> = Resource.Success(data = mockEpisodeDTOS.map { it.toEpisode() })
        val mockErrorEpisodeListResourceDTO : Resource<List<Episode>> = Resource.Error(message = mockExceptionStr)

    }
}


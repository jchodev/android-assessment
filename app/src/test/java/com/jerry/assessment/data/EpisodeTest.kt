package com.jerry.assessment.data

import com.jerry.assessment.MockStubs
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class EpisodeTest {

    @Test
    fun `test EpisodeDTO to Episode`(){
        val mockEpisode = MockStubs.mockEpisodeDTOS[0]

        val episode = mockEpisode.toEpisode()
        Assertions.assertEquals(
            mockEpisode.description, episode.description
        )
        Assertions.assertEquals(
            mockEpisode.duration, episode.duration
        )
        Assertions.assertEquals(
            mockEpisode.id, episode.id
        )
        Assertions.assertEquals(
            mockEpisode.mimeType, episode.mimeType
        )
        Assertions.assertEquals(
            mockEpisode.title, episode.title
        )
        Assertions.assertEquals(
            mockEpisode.url, episode.url
        )
        Assertions.assertEquals(
            mockEpisode.published, episode.published
        )
    }
}
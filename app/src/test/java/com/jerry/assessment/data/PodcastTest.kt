package com.jerry.assessment.data

import com.jerry.assessment.MockStubs
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PodcastTest {

    @Test
    fun `test PodcastDTO to Podcast`(){
        val mockPodcast = MockStubs.mockPodcastDTOS[0]

        val podcast = mockPodcast.toPodcast()
        Assertions.assertEquals(
            mockPodcast.author, podcast.author
        )
        Assertions.assertEquals(
            mockPodcast.description, podcast.description
        )
        Assertions.assertEquals(
            mockPodcast.id, podcast.id
        )
        Assertions.assertEquals(
            mockPodcast.link, podcast.link
        )
        Assertions.assertTrue(
            podcast.link.contains(
               mockPodcast.link ?: ""
           )
        )
        Assertions.assertEquals(
            mockPodcast.title, podcast.title
        )
    }
}
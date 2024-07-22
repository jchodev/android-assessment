package com.jerry.assessment.di

import com.jerry.assessment.network.EpisodeApi
import com.jerry.assessment.network.PodcastApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideEpisodeApi(retrofit: Retrofit): EpisodeApi {
        return retrofit.create(EpisodeApi::class.java)
    }

    @Provides
    @Singleton
    fun providePodcastApi(retrofit: Retrofit): PodcastApi {
        return retrofit.create(PodcastApi::class.java)
    }
}
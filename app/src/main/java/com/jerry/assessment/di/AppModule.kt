package com.jerry.assessment.di

import android.app.NotificationManager
import android.content.ComponentName
import android.content.Context
import androidx.media3.session.MediaSession
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import com.jerry.assessment.BuildConfig
import com.jerry.assessment.contants.TIME_OUT
import com.jerry.assessment.manager.MediaNotificationManager
import com.jerry.assessment.service.PlaybackService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideOKHttpClientLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
            if (!BuildConfig.DEBUG)
                level = HttpLoggingInterceptor.Level.NONE
        }
    }

    @Singleton
    @Provides
    fun provideOKHttpClientInterceptor(): Interceptor {
        return object: Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {

                val original = chain.request()

                val newRequest = original.newBuilder()
                    .addHeader("Content-Type","application/json")
                    .build()

                return chain.proceed(newRequest)
            }
        }
    }

    @Singleton
    @Provides
    fun provideOKHttpClient(logInterceptor: HttpLoggingInterceptor, interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(logInterceptor)
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            //it avoid/ protect throwing exception for invalid JSON when parsing
            //we can handle the error from our code level
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build()
    }

    @Singleton
    @Provides
    fun provideExoPlayer(@ApplicationContext context: Context): ExoPlayer {
        return ExoPlayer.Builder(context).build()
    }

    @Singleton
    @Provides
    fun provideMediaSession(@ApplicationContext context: Context, player: ExoPlayer): MediaSession {
        return MediaSession.Builder(context, player).build()
    }

    @Singleton
    @Provides
    fun provideSessionToken(@ApplicationContext context: Context): SessionToken {
        return SessionToken(context, ComponentName(context, PlaybackService::class.java))
        //return SessionToken(context, ComponentName(context, SimplePlaybackService::class.java))
    }


    @Singleton
    @Provides
    fun provideMediaControllerFuture(@ApplicationContext context: Context, sessionToken: SessionToken): ListenableFuture<MediaController> {
        return MediaController.Builder(context, sessionToken).buildAsync()
    }

    @Singleton
    @Provides
    fun provideNotificationManager(@ApplicationContext context: Context, player: ExoPlayer): NotificationManager {
        return context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

//
//
//    @Singleton
//    @Provides
//    fun provideMediaNotificationManager(
//        @ApplicationContext context: Context,
//        player: ExoPlayer,
//        mediaSession: MediaSession
//    ): MediaNotificationManager {
//        return MediaNotificationManager(
//            context = context,
//            sessionToken = mediaSession.token,
//            player = player,
//
//        )
//    }
}
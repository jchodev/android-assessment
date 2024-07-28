package com.jerry.assessment.manager

import android.content.Context
import androidx.media3.common.Player
import androidx.media3.session.SessionToken
import androidx.media3.ui.PlayerNotificationManager
import android.app.PendingIntent

import android.graphics.Bitmap
import android.net.Uri
import androidx.annotation.OptIn
import androidx.core.graphics.drawable.toBitmap
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.MediaController
import coil.imageLoader
import coil.request.ImageRequest
import com.google.common.util.concurrent.ListenableFuture
import com.jerry.assessment.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(UnstableApi::class)
class MediaNotificationManager(
    private val context: Context,
    sessionToken: SessionToken,
    private val player: Player,
    notificationListener: PlayerNotificationManager.NotificationListener
) {
    private val NOW_PLAYING_NOTIFICATION_ID = 1
    private val NOW_PLAYING_CHANNEL_ID = "NOW_PLAYING_CHANNEL_ID"
    private val NOTIFICATION_LARGE_ICON_SIZE = 144 // px

    private val serviceJob = SupervisorJob()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)
    private val notificationManager: PlayerNotificationManager

    init {

        val mediaController = MediaController.Builder(context, sessionToken).buildAsync()

        notificationManager = PlayerNotificationManager.Builder(
            context,
            NOW_PLAYING_NOTIFICATION_ID,
            NOW_PLAYING_CHANNEL_ID
        )
            .setChannelNameResourceId(R.string.media_notification_channel)
            .setChannelDescriptionResourceId(R.string.media_notification_channel_description)
            .setMediaDescriptionAdapter(DescriptionAdapter(mediaController))
            .setNotificationListener(notificationListener)
            .setSmallIconResourceId(R.drawable.ic_launcher_foreground)
            .build()
            .apply {
                setPlayer(player)
                setUseRewindAction(false)
                setUseFastForwardAction(false)
                setUseRewindActionInCompactView(false)
                setUseFastForwardActionInCompactView(false)
                setUseRewindActionInCompactView(false)
                setUseFastForwardActionInCompactView(false)

                setUsePreviousAction(false) //"|<"
                setUsePreviousActionInCompactView(false) //"|<"

                setUseNextAction(false) // ">|"
                setUseNextActionInCompactView(false) //">|"

                //setUsePlayPauseActions //  "▶" (Play) and "⏸" (Pause)
                //setUseRewindAction & setUseRewindActionInCompactView: "⏪"
                //setUseFastForwardAction & setUseFastForwardActionInCompactView: "⏩"
                //setUseStopAction: "⏹"
                //setUseChronometer: Displays the elapsed time of the current track
            }

    }

    /**
     * Hides the notification.
     */
    fun hideNotification() {
        notificationManager.setPlayer(null)
    }

    /**
     * Shows the notification for the given player.
     * @param player The player instance for which the notification is shown.
     */
    fun showNotificationForPlayer(player: Player) {
        notificationManager.setPlayer(player)
    }

    private inner class DescriptionAdapter(private val controller: ListenableFuture<MediaController>) :
        PlayerNotificationManager.MediaDescriptionAdapter {

        var currentIconUri: Uri? = null
        var currentBitmap: Bitmap? = null

        override fun createCurrentContentIntent(player: Player): PendingIntent? =
            controller.get().sessionActivity

        override fun getCurrentContentText(player: Player) =
            ""

        override fun getCurrentContentTitle(player: Player) =
            controller.get().mediaMetadata.title.toString()

        override fun getCurrentLargeIcon(
            player: Player,
            callback: PlayerNotificationManager.BitmapCallback
        ): Bitmap? {
            val iconUri = controller.get().mediaMetadata.artworkUri
            return if (currentIconUri != iconUri || currentBitmap == null) {

                // Cache the bitmap for the current song so that successive calls to
                // `getCurrentLargeIcon` don't cause the bitmap to be recreated.
                currentIconUri = iconUri
                serviceScope.launch {
                    currentBitmap = iconUri?.let {
                        resolveUriAsBitmap(it)
                    }
                    currentBitmap?.let { callback.onBitmap(it) }
                }
                null
            } else {
                currentBitmap
            }
        }

        private suspend fun resolveUriAsBitmap(uri: Uri): Bitmap? {
            return withContext(Dispatchers.IO) {
                // Block on downloading artwork.
                //using coil
                val request = ImageRequest.Builder(context)
                    .data(uri)
                    .build()
                val drawable = context.imageLoader.execute(request).drawable
                drawable?.toBitmap(NOTIFICATION_LARGE_ICON_SIZE, NOTIFICATION_LARGE_ICON_SIZE)
            }
        }
    }
}
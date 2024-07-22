package com.jerry.assessment.data

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.jerry.assessment.data.remote.dto.EpisodeDTO
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
@Parcelize
data class Episode(
    val description: String = "",
    val duration: Long = 0,

    val id: Long = 0,
    val mimeType: String = "",
    val published: Long = 0,

    val title: String = "",
    val type: String = "",
    val url: String = "",

    val podcast: Podcast = Podcast(),
): Parcelable



fun EpisodeDTO.toEpisode():Episode {
    return Episode(
        description = description ?: "",
        duration = duration ?: 0,
        id = id ?: 0,
        mimeType = mimeType ?: "",
        title = title ?: "",
        type = type ?: "",
        url = url ?: "",
        published = published ?: 0,
    )
}

//https://developer.android.com/guide/navigation/design/kotlin-dsl#custom-types
val EpisodeType = object : NavType<Episode>(
    isNullableAllowed = false
) {
    override fun put(bundle: Bundle, key: String, value: Episode) {
        bundle.putParcelable(key, value)
    }
    override fun get(bundle: Bundle, key: String): Episode? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, Episode::class.java)
        } else {
            @Suppress("DEPRECATION")
            bundle.getParcelable(key)
        }
    }

    override fun serializeAsValue(value: Episode): String {
        // Serialized values must always be Uri encoded
        return Uri.encode(Json.encodeToString(value))
    }

    override fun parseValue(value: String): Episode {
        // Navigation takes care of decoding the string
        // before passing it to parseValue()
        return Json.decodeFromString<Episode>(value)
    }

}
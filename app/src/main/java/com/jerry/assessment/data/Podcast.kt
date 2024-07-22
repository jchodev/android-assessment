package com.jerry.assessment.data

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.jerry.assessment.contants.IMAGE_URL
import com.jerry.assessment.data.remote.dto.PodcastDTO
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
@Parcelize
data class Podcast(
    val author: String = "",
    val description: String = "",
    val id: Long = 0,
    val imageUrl: String = "",
    val link: String = "",
    val title: String = "",
): Parcelable

fun PodcastDTO.toPodcast(): Podcast {
    return Podcast(
        author = author,
        description = description,
        id = id,
        imageUrl = image?.let { IMAGE_URL+it } ?: "",
        link = link ?: "",
        title = title
    )
}

//https://developer.android.com/guide/navigation/design/kotlin-dsl#custom-types
val PodcastType = object : NavType<Podcast>(
    isNullableAllowed = false
) {
    override fun put(bundle: Bundle, key: String, value: Podcast) {
        bundle.putParcelable(key, value)
    }
    override fun get(bundle: Bundle, key: String): Podcast? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, Podcast::class.java)
        } else {
            @Suppress("DEPRECATION")
            bundle.getParcelable(key)
        }
    }

    override fun serializeAsValue(value: Podcast): String {
        // Serialized values must always be Uri encoded
        return Uri.encode(Json.encodeToString(value))
    }

    override fun parseValue(value: String): Podcast {
        // Navigation takes care of decoding the string
        // before passing it to parseValue()
        return Json.decodeFromString<Podcast>(value)
    }

}
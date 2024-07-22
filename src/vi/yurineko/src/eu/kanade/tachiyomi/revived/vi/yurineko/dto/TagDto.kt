package eu.kanade.tachiyomi.revived.vi.yurineko.dto

import kotlinx.serialization.Serializable

@Serializable
data class TagDto(
    val id: Int,
    val name: String,
    val url: String,
    val origin: String? = null,
)

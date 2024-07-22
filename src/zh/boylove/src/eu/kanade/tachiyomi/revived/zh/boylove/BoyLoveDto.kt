package eu.kanade.tachiyomi.revived.zh.boylove

import eu.kanade.tachiyomi.source.model.SChapter
import eu.kanade.tachiyomi.source.model.SManga
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.long

@Serializable
class MangaDto(
    val id: Int,
    private val title: String,
    private val update_time: JsonPrimitive? = null,
    private val image: String,
    private val auther: String,
    private val desc: String?,
    private val mhstatus: Int,
    private val keyword: String,
) {
    fun toSManga() = SManga.create().apply {
        url = id.toString()
        title = this@MangaDto.title
        author = auther
        genre = keyword.replace(",", ", ")
        status = when (mhstatus) {
            0 -> SManga.ONGOING
            1 -> SManga.COMPLETED
            else -> SManga.UNKNOWN
        }
        thumbnail_url = image.toImageUrl()
        val rawUpdateTime = update_time
        if (rawUpdateTime == null) {
            description = desc?.trim()
            return@apply
        }
        val updateTime = when {
            rawUpdateTime.isString -> rawUpdateTime.content
            else -> dateFormat.format(Date(rawUpdateTime.long * 1000))
        }
        description = "更新时间：$updateTime\n\n${desc?.trim()}"
        initialized = true
    }
}

fun String.toImageUrl() =
    if (startsWith("http")) {
        this
    } else {
        "https://blcnimghost2.cc$this"
    }

@Serializable
class ChapterDto(
    private val id: Int,
    private val title: String,
) {
    fun toSChapter() = SChapter.create().apply {
        url = "/home/book/capter/id/$id"
        name = title.trim()
    }
}

private val dateFormat by lazy { SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH) }

@Serializable
class ListPageDto<T>(val lastPage: Boolean, val list: List<T> = emptyList())

@Serializable
class ResultDto<T>(val result: T)

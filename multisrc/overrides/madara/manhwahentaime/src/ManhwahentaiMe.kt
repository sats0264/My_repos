package eu.kanade.tachiyomi.revived.en.manhwahentaime

import eu.kanade.tachiyomi.multisrc.madara.Madara

class ManhwahentaiMe : Madara("Manhwahentai.me", "https://manhwahentai.me", "en") {

    override val useNewChapterEndpoint: Boolean = true

    override val mangaSubString = "webtoon"
}

package ai.purpose.soostoneassignment.core.database.model

import androidx.room.TypeConverter

import java.net.MalformedURLException
import java.net.URL

class UrlConverter {

    @TypeConverter
    fun fromUrl(url: URL?): String? {
        return url?.toString()
    }

    @TypeConverter
    fun toUrl(urlString: String?): URL? {
        return try {
            URL(urlString)
        } catch (e: MalformedURLException) {
            null
        }
    }
}

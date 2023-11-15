package service

import model.Url
import org.springframework.stereotype.Service
import repository.UrlRepository

@Service
class UrlService(private val urlRepository: UrlRepository, private val conversion: BaseConversion) {

    fun convertToShortUrl(request: String): String {
        val url = Url(longUrl = request)
        val entity = urlRepository.save(url)
        return conversion.encode(entity.id)
    }

    fun getOriginalUrl(shortUrl: String): String {
        val id = conversion.decode(shortUrl)
        val entity = urlRepository.findById(id)
        return entity.get().longUrl
    }
}
package service

import model.Url
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import repository.UrlRepository
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class UrlServiceTest {

    @Mock
    lateinit var mockUrlRepository: UrlRepository

    @Mock
    lateinit var mockBaseConversion: BaseConversion

    @InjectMocks
    lateinit var urlService: UrlService

    @Test
    fun convertToShortUrlTest() {
        val url = Url(0,"https://github.com/AnteMarin/UrlShortener-API")


        `when`(mockUrlRepository.save(any(Url::class.java))).thenReturn(url)
        `when`(mockBaseConversion.encode(url.id)).thenReturn("f")

        val urlRequest = "https://github.com/AnteMarin/UrlShortener-API"

        assertEquals("f", urlService.convertToShortUrl(urlRequest))
    }

    @Test
    fun getOriginalUrlTest() {
        `when`(mockBaseConversion.decode("h")).thenReturn(7.toLong())

        val url = Url(7,"https://github.com/AnteMarin/UrlShortener-API")


        `when`(mockUrlRepository.findById(7.toLong())).thenReturn(Optional.of(url))
        assertEquals("https://github.com/AnteMarin/UrlShortener-API", urlService.getOriginalUrl("h"))
    }
}

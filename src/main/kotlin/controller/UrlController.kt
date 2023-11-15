package controller

import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.*
import service.UrlService
import springfox.documentation.annotations.Cacheable
import java.net.URI

@RestController
@RequestMapping("/api/v1")
class UrlController(private val urlService: UrlService) {

    @ApiOperation(value = "Convert new url", notes = "Converts long url to short url")
    @PostMapping("create-short")
    @Async
    fun convertToShortUrl(@RequestBody request: String): String {
        return urlService.convertToShortUrl(request)
    }

    @ApiOperation(value = "Redirect", notes = "Finds original url from short url and redirects")
    @GetMapping("{shortUrl}")
    @Cacheable("originalUrls")
    fun getOriginalUrl(@PathVariable shortUrl: String): ResponseEntity<Void> {
        val url = urlService.getOriginalUrl(shortUrl)
        return ResponseEntity.status(HttpStatus.FOUND)
            .location(URI.create(url))
            .build()
    }
}
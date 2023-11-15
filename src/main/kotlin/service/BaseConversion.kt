package service

import org.springframework.stereotype.Service
import kotlin.math.pow

@Service
class BaseConversion {
    private val allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    private val allowedCharacters = allowedString.toCharArray()
    private val base = allowedCharacters.size
    private val indexMap = allowedString.indices.associateBy { allowedString[it] }

    fun encode(input: Long): String {
        val encodedString = StringBuilder()

        if(input == 0L) {
            return allowedCharacters[0].toString()
        }

        var tempInput = input
        while (tempInput > 0) {
            encodedString.append(allowedCharacters[(tempInput % base).toInt()])
            tempInput /= base
        }

        return encodedString.toString()
    }

    fun decode(input: String): Long {
        val characters = input.toCharArray()
        val length = characters.size

        var decoded = 0L
        var counter = 1
        for (i in 0 until length) {
            decoded += indexMap[characters[i]]!! * base.toDouble().pow((length - counter).toDouble()).toLong()
            counter++
        }
        return decoded
    }
}

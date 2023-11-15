package service

import org.springframework.stereotype.Service

@Service
class BaseConversion {
    private val allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    private val allowedCharacters = allowedString.toCharArray()
    private val base = allowedCharacters.size

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

        return encodedString.reverse().toString()
    }

    fun decode(input: String): Long {
        val characters = input.toCharArray()
        val length = characters.size

        var decoded = 0L
        var counter = 1
        for (i in 0 until length) {
            decoded += allowedString.indexOf(characters[i]) * Math.pow(base.toDouble(), (length - counter).toDouble()).toLong()
            counter++
        }
        return decoded
    }
}
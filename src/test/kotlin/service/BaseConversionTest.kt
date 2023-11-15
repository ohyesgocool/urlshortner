package service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BaseConversionTest {

    private val baseConversion = BaseConversion()

    @Test
    fun encode_lessThan62() {
        assertEquals("k", baseConversion.encode(10))
    }

    @Test
    fun encode_moreThan62() {
        assertEquals("bq", baseConversion.encode(78))
    }

    @Test
    fun decode_singleCharacter() {
        assertEquals(11, baseConversion.decode("l"))
    }
}
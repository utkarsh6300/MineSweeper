import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import util.RandomNumberGenerator
import kotlin.test.Test

class RandomNumberGeneratorTest {
    private lateinit var randomNumberGenerator: RandomNumberGenerator
    @BeforeEach
    fun setup(){
        randomNumberGenerator =RandomNumberGenerator()
    }
    @Test
    fun `number generated should be in range`(){
        val number= randomNumberGenerator.randomInteger(2,7)
        assertTrue(number in 2..7,"random number should be between 2 and 7")
    }
}
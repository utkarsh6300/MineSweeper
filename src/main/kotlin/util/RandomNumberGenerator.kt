package util
class RandomNumberGenerator {
    fun randomInteger(from: Int, to: Int): Int {
        return (from..to).random();
    }
}

    fun main() {
        var a = -1
        var y = 5

        val originalA = a
        val originalY = y

        // swap

        a = a + y
        y = a - y
        a = a - y

        println(a == originalY)
        println(y == originalA)
    }


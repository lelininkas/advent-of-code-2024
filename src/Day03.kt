

fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161)

    val input = readInput("Day03")
    part1(input).println()
//    part2(input).println()
}

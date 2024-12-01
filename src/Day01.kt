import kotlin.math.absoluteValue

fun main() {
    fun part1(input: List<List<Int>>): Int {
        val sorted = input.map { it.sorted() }
        return sorted[0].zip(sorted[1])  {a, b -> (a - b).absoluteValue}.sum()
    }

    fun part2(input: List<List<Int>>): Int {
        return input[0].sumOf { a -> a * input[1].count { b -> b == a } }
    }

    fun toListOfLists(input: List<String>): List<List<Int>> {
        val values = input.map { it.trim().split("""\s+""".toRegex()).map(String::toInt) }
        return List(values[0].size) { index -> values.map { it[index] } }
    }

    fun getTestInput(): List<List<Int>> = toListOfLists(readInput("Day01_test"))
    fun getInput(): List<List<Int>> = toListOfLists(readInput("Day01"))

    val testInput = getTestInput()
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    val input = getInput()
    part1(input).println()
    part2(input).println()
}
